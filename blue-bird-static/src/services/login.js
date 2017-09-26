import { request, config } from '../utils'

const { api } = config
const { userLogin } = api

export async function login (data) {
  console.log('login',data,userLogin)
  return request({
    url: userLogin,
    method: 'post',
    data,
  })
}
