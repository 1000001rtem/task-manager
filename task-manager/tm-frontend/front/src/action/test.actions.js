import axiosInstance from "../config/axiosConfig";


export const testActions = {
    ping,
};

async function ping() {
    let data = null;
    await axiosInstance.get('ping').then(res => {
        data = res.data;
        return data;
    });
    return await data;
}