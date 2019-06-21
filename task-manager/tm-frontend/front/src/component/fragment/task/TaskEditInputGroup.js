import * as React from "react";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import {FormControl} from "react-bootstrap";
import {DatePickerInput} from "rc-datepicker";
import StatusDropBox from "../common/StatusDropBox";
import Button from "react-bootstrap/Button";
import ProjectDropBox from "../common/ProjectDropBox";

class TaskEditInputGroup extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.state = {
            id: props.task.id,
            createDate: props.task.createDate,
            name: props.task.name,
            description: props.task.description,
            startDate: props.task.startDate,
            endDate: props.task.endDate,
            status: props.task.status,
            projectId: props.task.projectId,
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.changeStatus = this.changeStatus.bind(this);
        this.changeProject = this.changeProject.bind(this);
    }

    componentWillReceiveProps(nextProps, nextContext) {
        if (nextProps.task !== this.props.task) {
            this.setState({
                id: nextProps.task.id,
                createDate: nextProps.task.createDate,
                name: nextProps.task.name,
                description: nextProps.task.description,
                startDate: nextProps.task.startDate,
                endDate: nextProps.task.endDate,
                status: nextProps.task.status,
                projectId: nextProps.task.projectId,
            })
        }
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    changeStatus(status) {
        this.setState({status: status});
    }

    handleSubmit(e) {
        e.preventDefault();
        this.props.onSubmit(this.state);
    }

    changeProject(project) {
        this.setState({projectId: project});
    }

    render() {
        return (
            <div className={'inputGroup'}>
                <Form onSubmit={this.handleSubmit}>
                    <InputGroup className="mb-3">
                        <InputGroup.Prepend>
                            <InputGroup.Text id="inputGroup-sizing-default">Id</InputGroup.Text>
                        </InputGroup.Prepend>
                        <FormControl
                            name={'id'}
                            onChange={this.handleChange}
                            disabled={true}
                            value={this.state.id}
                            aria-label="Default"
                            aria-describedby="inputGroup-sizing-default"
                        />
                    </InputGroup>
                    <InputGroup className="mb-3">
                        <InputGroup.Prepend>
                            <InputGroup.Text id="inputGroup-sizing-default">Create Date</InputGroup.Text>
                        </InputGroup.Prepend>
                        <FormControl
                            disabled={true}
                            value={this.state.createDate}
                            aria-label="Default"
                            aria-describedby="inputGroup-sizing-default"
                        />
                    </InputGroup>
                    <InputGroup className="mb-3">
                        <InputGroup.Prepend>
                            <InputGroup.Text id="inputGroup-sizing-default">Name</InputGroup.Text>
                        </InputGroup.Prepend>
                        <FormControl
                            name={'name'}
                            onChange={this.handleChange}
                            value={this.state.name}
                            aria-label="Default"
                            aria-describedby="inputGroup-sizing-default"
                        />
                    </InputGroup>
                    <InputGroup className="mb-3">
                        <InputGroup.Prepend>
                            <InputGroup.Text id="inputGroup-sizing-default">Description</InputGroup.Text>
                        </InputGroup.Prepend>
                        <FormControl
                            name={'description'}
                            onChange={this.handleChange}
                            value={this.state.description}
                            aria-label="Default"
                            aria-describedby="inputGroup-sizing-default"
                        />
                    </InputGroup>
                    <InputGroup className="mb-3">
                        <InputGroup.Prepend>
                            <InputGroup.Text id="inputGroup-sizing-default">Start Date</InputGroup.Text>
                        </InputGroup.Prepend>
                        <DatePickerInput
                            returnFormat='DD.MM.YYYY'
                            name={'startDate'}
                            onChange={(jsDate, dateString) => this.setState({startDate: dateString})}
                            value={this.state.startDate}
                            displayFormat='DD.MM.YYYY'
                            className='my-react-component'
                        />
                    </InputGroup>
                    <InputGroup className="mb-3">
                        <InputGroup.Prepend>
                            <InputGroup.Text id="inputGroup-sizing-default">End Date</InputGroup.Text>
                        </InputGroup.Prepend>
                        <DatePickerInput
                            returnFormat='DD.MM.YYYY'
                            name={'endDate'}
                            onChange={(jsDate, dateString) => this.setState({endDate: dateString})}
                            value={this.state.endDate}
                            displayFormat='DD.MM.YYYY'
                            className='my-custom-datepicker-component'
                        />
                    </InputGroup>
                    <div className={'dropBoxContainer'}>
                        <StatusDropBox status={this.state.status} onChange={this.changeStatus}/>
                        <ProjectDropBox onChange={this.changeProject} projectId={this.state.projectId}/>
                    </div>
                    <br/>
                    <Button type={"submit"}>SAVE</Button>
                </Form>
            </div>
        )
    }
}

export default TaskEditInputGroup;