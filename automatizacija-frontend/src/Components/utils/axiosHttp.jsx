import axios from "axios";

const axiosHttp = axios.create();

axiosHttp.interceptors.request.use(
  (config) => {
    const token =  sessionStorage.getItem("jwtToken");
    return {
      ...config,
      headers: {
        ...(token !== null && { Authorization: `Bearer ${token}` }),
        ...config.headers,
      },
    };
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axiosHttp;