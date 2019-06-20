import * as React from "react";
import {taskActions} from "../../../action/task.actions";
import TaskEditContent from "../../fragment/task/TaskEditContent";

class TaskEditContentContainer extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.state = {
            task: [],
        };
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(task) {
        task.userId = localStorage.getItem("userId");
        taskActions.update(task).then(this.props.history.push('/task'));
        window.location.reload();
    }

    componentDidMount() {
        taskActions.findOne(this.props.match.params.taskId).then(res => {
            this.setState({task: res});
        });
    }

    render() {
        return (
            <TaskEditContent task={this.state.task} onSubmit={this.onSubmit}/>
        )
    }
}

export default TaskEditContentContainer;