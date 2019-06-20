import axiosInstance from "../config/axiosConfig";

export const projectActions = {
    create,
    update,
    findAll,
    findPage,
    findOne,
    remove,
};

async function create(project) {
    await axiosInstance.post('project/create', project).then(res => {
        console.log(res);
    })
}

async function update(project) {
    await axiosInstance.put('project/update', project).then(res => {
        console.log(res);
    });
}

async function findAll() {
    let projects;
    await axiosInstance.get('project/findAll', {
        handlerEnabled: true,
    }).then(res => {
        projects = res.data;
        return projects;
    });
    return await projects;
}

async function findPage(page, size) {
    let result;
    await axiosInstance.get('project/findPage', {
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

async function findOne(projectId) {
    let project;
    await axiosInstance.get('project/findOne', {
        handlerEnabled: true,
        params: {
            projectId: projectId
        }
    }).then(res => {
        project = res.data;
        return project;
    });
    return await project;
}

function remove(projectId) {
    axiosInstance.delete('project/delete', {
        handlerEnabled: true,
        params: {
            projectId: projectId,
        }
    })
}