import axiosInstance from "../config/axiosConfig";

export const taskActions = {
    create,
    update,
    findAll,
    findPage,
    findOne,
    remove,
};

async function create(task) {
    await axiosInstance.post('task/create', task).then(res => {
        console.log(res);
    })
}

async function update(task) {
    await axiosInstance.put('task/update', task).then(res => {
        console.log(res);
    })
}

async function findAll() {
    let tasks;
    await axiosInstance.get('task/findAll', {
        handlerEnabled: true,
    }).then(res => {
        tasks = res.data;
        return tasks;
    });
    return await tasks;
}

async function findPage(page, size) {
    let result;
    await axiosInstance.get('task/findPage', {
        handlerEnabled: true,
        params: {
            page: page,
            size: size,
        }
    }).then(res => {
        result = res.data;
        return result;
    });
    return await result;
}

async function findOne(taskId) {
    let task;
    await axiosInstance.get('task/findOne', {
        params: {
            taskId: taskId,
        }
    }).then(res => {
        task = res.data;
        return task;
    });
    return await task;
}

function remove(taskId) {
    axiosInstance.delete('task/delete', {
        handlerEnabled: true,
        params: {
            taskId: taskId,
        }
    }).then(res => {
        console.log(res);
    })
}

