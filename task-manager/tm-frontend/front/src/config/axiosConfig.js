import * as axios from "axios";

let handlerEnabled = true;

const axiosInstance = axios.create({
    baseURL: 'http://localhost:7777/api/'
});

axiosInstance.interceptors.request.use(
    request => requestHandler(request)
);

const requestHandler = (request) => {
    if (isHandlerEnabled(request)) {
        request.headers['Authorization'] = localStorage.getItem('token');
    }
    return request;
};

const isHandlerEnabled = (config = {}) => {
    return !(config.hasOwnProperty('handlerEnabled') && !config.handlerEnabled)
};

export default axiosInstance;




