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

const { Header, Bread, Footer, Sider, styles } = Layout

function App({children, dispatch, app, loading, location}) {
  return (
      <div className={styles.container}>
        <div className={styles.content}>
          {children}
        </div>
      </div>
  );
}

App.propTypes = {
  children: PropTypes.element.isRequired,
  location: PropTypes.object,
  dispatch: PropTypes.func,
  app: PropTypes.object,
  loading: PropTypes.object,
};

export default connect(({app, loading}) => ({app, loading}))(App)
