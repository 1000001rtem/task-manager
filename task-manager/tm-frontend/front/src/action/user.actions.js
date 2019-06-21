import axiosInstance from "../config/axiosConfig";

export const userActions = {
    login,
    registration,
    logout,
};

async function login(login, password) {
    await axiosInstance.post('auth', {
        handlerEnabled: false,
        login: login,
        password: password
    }).then(res => {
        setLocalStorage(res);
    }).catch(function (error) {

    })
}

async function registration(login, password) {
    await axiosInstance.post('registration', {
        handlerEnabled: false,
        login: login,
        password: password
    }).then(res => {
        console.log(res);
    });
}

async function setLocalStorage(res) {
    localStorage.setItem('userId', res.data.userId);
    localStorage.setItem('token', 'Bearer ' + res.data.token);
    localStorage.setItem('login', res.data.login);
    localStorage.setItem('role', res.data.role);
}

function logout() {
    localStorage.clear();
}