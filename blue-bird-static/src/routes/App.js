import React from 'react'
import NProgress from 'nprogress'
import PropTypes from 'prop-types'
import pathToRegexp from 'path-to-regexp'
import { connect } from 'dva'
import { Layout, Loader } from 'components'
import { classnames, config } from 'utils'
import { Helmet } from 'react-helmet'
import { withRouter } from 'dva/router'
import '../themes/index.less'
import './App.less'
import Error from './error'
import { Link } from 'react-router-dom'

const { prefix, openPages } = config
const { Header, Bread, Footer, Sider, styles } = Layout

const App = ({ children, dispatch, app, loading, location })=> {
  const { isLogin } = app
  let { pathname } = location

  if (openPages && openPages.includes(pathname)) {
    return (<div>
      <Loader fullScreen spinning={loading.effects['app/query']} />
      {children}
    </div>)
  }
  return (
    <div>

      <Link to='/home#'>
        home
      </Link>
      <Link to='/error#'>
        error
      </Link>
      <Loader fullScreen spinning={loading.effects['app/query']} />
      <div className={styles.content}>
        {isLogin ? children : <Error />}
      </div>
    </div>
  )
}

App.propTypes = {
  children: PropTypes.element.isRequired,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  app: PropTypes.object,
  loading: PropTypes.object,
}

export default withRouter(connect(({ app, loading }) => ({ app, loading }))(App))
