import * as React from "react";
import {projectActions} from "../../../action/project.actions";
import ProjectEditContent from "../../fragment/project/ProjectEditContent";

class ProjectEditContentContainer extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.state = {
            project: [],
        };
        this.onSubmit = this.onSubmit.bind(this);

    }

    onSubmit(project) {
        project.userId = localStorage.getItem("userId");
        projectActions.update(project).then(this.props.history.push('/project'));
        window.location.reload();
    }

    componentDidMount() {
        projectActions.findOne(this.props.match.params.projectId).then(res => {
            this.setState({project: res});
        });
    }

    render() {
        return (
            <ProjectEditContent project={this.state.project} onSubmit={this.onSubmit}/>
        )
    }

}

export default ProjectEditContentContainer;