import * as React from "react";
import Container from "react-bootstrap/Container";
import ProjectCreateInputGroup from "./ProjectCreateInputGroup";

class ProjectCreateContent extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(project) {
        this.props.onSubmit(project);
    }

    render() {
        return (
            <div className={"content"}>
                <Container>
                    <ProjectCreateInputGroup onSubmit={this.onSubmit}/>
                </Container>
            </div>
        )
    }

}

export default ProjectCreateContent;