import { routerRedux } from 'dva/router'
import { login } from '../../services/login'

export default {
  namespace: 'login',

  state: {},

  effects: {
    * login ({
      payload,
    }, { put, call, select }) {
      console.log('login', payload)
      const data = yield call(login, payload)
      const { locationQuery } = yield select(_ => _.app)
      if (data.success) {
        console.log('login', locationQuery,data)
        const { from } = locationQuery
        yield put({ type: 'app/query' })
        if (from && from !== '/login') {
          yield put(routerRedux.push(from))
        } else {
          yield put(routerRedux.push('/app'))
        }
      } else {
        throw data
      }
    },
  },

}
