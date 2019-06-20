import * as React from "react";
import ProjectContent from "../../fragment/project/ProjectContent";
import {projectActions} from "../../../action/project.actions";

class ProjectContentContainer extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            projects: [],
            page: {
                currentPageNumber: 0,
                totalItems: null,
                numItemsPerPage: null,
            }
        };
        this.removeHandle = this.removeHandle.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
    }

    componentDidMount() {
        projectActions.findPage(1, 10).then(res => {
            this.setState({
                projects: res.content,
                page: {
                    currentPageNumber: res.number,
                    totalItems: res.totalElements,
                    numItemsPerPage: res.size,
                }
            });
        });
    }

    removeHandle(projectId) {
        projectActions.remove(projectId);
        window.location.reload();
    }

    handlePageChange(page) {
        projectActions.findPage(page, 10).then(res => {
            this.setState({
                projects: res.content,
                page: {
                    currentPageNumber: res.number,
                    totalItems: res.totalElements,
                    numItemsPerPage: res.size,
                }
            });
        });
    }

    render() {
        return (
            <ProjectContent projects={this.state.projects}
                            page={this.state.page}
                            removeHandle={this.removeHandle}
                            handlePageChange={this.handlePageChange}/>
        )
    }

}

export default ProjectContentContainer;