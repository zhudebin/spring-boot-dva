import {routerRedux} from 'dva/router'
import {parse} from 'qs'
import config from 'config'
import {query, logout} from 'services/app'
import queryString from 'query-string'

const {prefix} = config

export default {

  namespace: 'app',

  state: {
    user: {},
    isLogin: false,
  },

  subscriptions: {
    setupHistory({dispatch, history}) {
      history.listen((location) => {
        dispatch({
          type: 'updateState',
          payload: {
            locationPathname: location.pathname,
            locationQuery: queryString.parse(location.search),
          },
        })
      })
    },
    setup({dispatch, history}) {  // eslint-disable-line
      dispatch({type: 'query'})
    },
  },

  effects: {
    * query({
              payload,
            }, {call, put, select}) {
      console.log('app', payload)
      const {success, user} = yield call(query, payload)
      const {locationPathname} = yield select(_ => _.app)
      console.log('app ', success, user,locationPathname)
      if (success && user) {
        // const { list } = yield call(menusService.query)
        const { permissions } = user
        console.log('app permissions', permissions)
        const isLogin = true;
        yield put({
          type: 'updateState',
          payload: {
            isLogin,
            user,
          },
        })
        if (location.pathname === '/login') {
          yield put(routerRedux.push({
            pathname: '/home',
          }))
        }
      } else if (config.openPages && config.openPages.indexOf(locationPathname) < 0) {
        yield put(routerRedux.push({
          pathname: '/login',
          search: queryString.stringify({
            from: locationPathname,
          }),
        }))
      }
    },
  },

  reducers: {
    updateState(state, {payload}) {
      return {
        ...state,
        ...payload,
      }
    },
  },
}
