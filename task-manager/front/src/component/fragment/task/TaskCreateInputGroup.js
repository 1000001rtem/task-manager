import * as React from "react";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import {FormControl} from "react-bootstrap";
import {DatePickerInput} from "rc-datepicker";
import Button from "react-bootstrap/Button";
import ProjectDropBox from "../common/ProjectDropBox";

class TaskCreateInputGroup extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.changeProject = this.changeProject.bind(this);
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
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
                            <InputGroup.Text id="inputGroup-sizing-default">Name</InputGroup.Text>
                        </InputGroup.Prepend>
                        <FormControl
                            name={'name'}
                            onChange={this.handleChange}
                            placeHolder={'Task Name'}
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
                            placeHolder={'Task Description'}
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
                            displayFormat='DD.MM.YYYY'
                            className='my-custom-datepicker-component'
                        />
                    </InputGroup>
                    <ProjectDropBox onChange={this.changeProject}/>
                    <br/>
                    <Button type={"submit"}>SAVE</Button>
                </Form>
            </div>
        )
    }

}

export default TaskCreateInputGroup;