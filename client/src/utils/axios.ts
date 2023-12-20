import axios, { type Method } from "axios"
// import { useAccountStore } from "@/stores/account"

axios.defaults.baseURL = "/api"

axios.defaults.headers.post["Content-Type"] = "application/json;charset=UTF-8"
axios.defaults.timeout = 10000

// const store = useAccountStore()

// axios.interceptors.request.use(
//   config => {
//     const token = store.token
//     config.headers.authorization = token
//     return config
//   },
//   error => {
//     return Promise.reject(error)
//   }
// )

axios.interceptors.response.use(
  response => {
    return response
  },
  error => {
    // const defaultNotify = {
    //   message: "未知错误",
    //   icon: "warning",
    //   color: "warning",
    //   position: "top",
    //   timeout: 1500,
    // }
    // if (error.code === "ECONNABORTED" || error.message.indexOf("timeout") !== -1 || error.message === "Network Error") {
    //   defaultNotify.message = "网络异常"
    //   Notify(defaultNotify.color, defaultNotify.message)
    //   return Promise.reject(error)
    // }
    // switch (error.response.status) {
    //   case 403:
    //     defaultNotify.message = "拒绝访问(403)"
    //     Notify(defaultNotify.color, defaultNotify.message)
    //     break
    //   case 404:
    //     defaultNotify.message = "资源不存在(404)"
    //     Notify(defaultNotify.color, defaultNotify.message)
    //     break
    //   case 408:
    //     defaultNotify.message = "请求超时(404)"
    //     Notify(defaultNotify.color, defaultNotify.message)
    //     break
    //   case 500:
    //     defaultNotify.message = "服务器错误(500)"
    //     Notify(defaultNotify.color, defaultNotify.message)
    //     break
    //   case 501:
    //     defaultNotify.message = "服务未实现(501)"
    //     Notify(defaultNotify.color, defaultNotify.message)
    //     break
    //   case 502:
    //     defaultNotify.message = "网络错误(502)"
    //     Notify(defaultNotify.color, defaultNotify.message)
    //     break
    //   case 503:
    //     defaultNotify.message = "服务不可用(503)"
    //     Notify(defaultNotify.color, defaultNotify.message)
    //     break
    //   case 504:
    //     defaultNotify.message = "网络超时(504)"
    //     Notify(defaultNotify.color, defaultNotify.message)
    //     break
    //   case 505:
    //     defaultNotify.message = "HTTP版本不受支持(505)"
    //     Notify(defaultNotify.color, defaultNotify.message)
    //     break
    //   default:
    //     break
    // }
    return Promise.reject(error)
  }
)

export default function Axios(url: string, data: any, method: Method) {
  return new Promise((resolve, reject) => {
    if (method === "get") {
      axios({
        method,
        url,
        params: data,
      })
        .then(res => {
          resolve(res.data.data)
        })
        .catch(err => {
          reject(err)
        })
    } else {
      axios({
        method,
        url,
        data,
      })
        .then(res => {
          resolve(res.data.data)
        })
        .catch(err => {
          reject(err)
        })
    }
  })
}