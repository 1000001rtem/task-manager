import axiosInstance from "../config/axiosConfig";


export const userActions = {
    findAll,
    remove,
};

async function findAll() {
    if (localStorage.getItem('role' !== 'ADMIN')) return;
    let users;
    await axiosInstance.get('user/findAll', {
        handlerEnabled: true,
    }).then(res => {
        users = res.data;
        return users;
    });
    return await users;
}

function remove(userId) {
    axiosInstance.delete("user/delete", {
        handlerEnabled: true,
        params: {
            userId: userId,
        }
    })
}