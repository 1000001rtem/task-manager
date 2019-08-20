import * as React from "react";
import ProjectCreateContent from "../../fragment/project/ProjectCreateContent";
import {projectActions} from "../../../action/project.actions";

class ProjectCreateContentContainer extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(project) {
        project.userId = localStorage.getItem("userId");
        projectActions.create(project).then(this.props.history.push('/project'));
        window.location.reload();
    }

    render() {
        return (
            <ProjectCreateContent onSubmit={this.onSubmit}/>
        )
    }

}

export default ProjectCreateContentContainer;