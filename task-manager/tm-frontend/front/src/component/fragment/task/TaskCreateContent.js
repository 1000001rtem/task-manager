import React from "react";
import TaskCreateInputGroup from "./TaskCreateInputGroup";
import Container from "react-bootstrap/Container";

class TaskCreateContent extends React.Component {

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
                    <TaskCreateInputGroup onSubmit={this.onSubmit}/>
                </Container>
            </div>
        )
    }

}

export default TaskCreateContent;