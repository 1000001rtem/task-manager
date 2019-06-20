import * as React from "react";
import TaskContent from "../../fragment/task/TaskContent";
import {taskActions} from "../../../action/task.actions";
import {projectActions} from "../../../action/project.actions";

class TaskContentContainer extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tasks: [],
            projects: [],
            page: {
                currentPageNumber: 0,
                totalItems: null,
                numItemsPerPage: null,
            }
        };
        this.handlePageChange = this.handlePageChange.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
    }

    componentWillMount() {
        taskActions.findPage(1, 10)
            .then(res => {
                this.setState({
                    tasks: res.content,
                    page: {
                        currentPageNumber: res.number,
                        totalItems: res.totalElements,
                        numItemsPerPage: res.size,
                    }
                });
            });
        projectActions.findAll().then(res => {
            this.setState({projects: res});
        });
    }

    removeHandle(taskId) {
        taskActions.remove(taskId);
        window.location.reload();
    }

    handlePageChange(page) {
        taskActions.findPage(page, 10).then(res => {
            this.setState({
                tasks: res.content,
                page: {
                    currentPageNumber: res.number,
                    totalItems: res.totalElements,
                    numItemsPerPage: res.size,
                }
            });
        });
        projectActions.findAll().then(res => {
            this.setState({projects: res});
        });
    }

    render() {
        return (
            <TaskContent tasks={this.state.tasks}
                         removeHandle={this.removeHandle}
                         projects={this.state.projects}
                         page={this.state.page}
                         handlePageChange={this.handlePageChange}/>
        )
    }

}

export default TaskContentContainer;