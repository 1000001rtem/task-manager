import React from "react";
import Dropdown from "react-bootstrap/Dropdown";
import {projectActions} from "../../../action/project.actions";

class ProjectDropBox extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.state = {
            selectedProject: {
                id: null,
                name: null,
            },
            projects: [],
        };
    }

    onSelect = (eventKey, key) => {
        this.setState({
            selectedProject: {
                id: eventKey,
                name: this.state.projects.map(e => {
                    if (e.id === eventKey) return e.name;
                }),
            }
        });
        this.props.onChange(eventKey);
    };

    componentWillReceiveProps(nextProps, nextContext) {
        if (nextProps.projectId !== this.props.projectId) {
            this.setState({
                selectedProject: {
                    id: nextProps.projectId,
                    name: this.state.projects.map(e => {
                            if (e.id === nextProps.projectId) return e.name;
                        }
                    )
                }
            });
        }
    }

    componentWillMount() {
        projectActions.findAll().then(res => {
            this.setState({projects: res});
            this.setState({
                selectedProject: {
                    id: this.state.projects[0].id,
                    name: this.state.projects[0].name,
                }
            });
        });
    }

    render() {
        return (
            <Dropdown title={this.state.selectedProject.name} onSelect={this.onSelect} id="d">
                <Dropdown.Toggle>
                    {console.log(this.state.selectedProject.name + ' 5')}
                    {this.state.selectedProject.name}
                </Dropdown.Toggle>
                <Dropdown.Menu>
                    {this.state.projects.map(project => (
                        <Dropdown.Item eventKey={project.id} key={project.name}>
                            {project.name}
                        </Dropdown.Item>
                    ))}
                </Dropdown.Menu>
            </Dropdown>
        )
    }

}

export default ProjectDropBox;