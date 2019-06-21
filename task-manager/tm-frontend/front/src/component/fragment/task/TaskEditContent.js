import * as React from "react";
import Container from "react-bootstrap/Container";
import TaskEditInputGroup from "./TaskEditInputGroup";

class TaskEditContent extends React.Component {


    constructor(props, context) {
        super(props, context);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(task) {
        this.props.onSubmit(task);
    }

    render() {
        return (
            <div className={"content"}>
                <Container>
                    <TaskEditInputGroup task={this.props.task} onSubmit={this.onSubmit}/>
                </Container>
            </div>
        )
    }
}

export default TaskEditContent;