import * as React from "react";
import {taskActions} from "../../../action/task.actions";
import TaskCreateContent from "../../fragment/task/TaskCreateContent";

class TaskCreateContentContainer extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(task) {
        task.userId = localStorage.getItem("userId");
        console.log(task);
        taskActions.create(task).then(this.props.history.push('/task'));
        window.location.reload();
    }

    render() {
        return (
            <TaskCreateContent onSubmit={this.onSubmit}/>
        )
    }

}

export default TaskCreateContentContainer;