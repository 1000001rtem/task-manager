import * as React from "react";
import InputGroup from "react-bootstrap/InputGroup";
import {FormControl} from "react-bootstrap";
import {DatePickerInput} from "rc-datepicker";
import 'rc-datepicker/lib/style.css';
import StatusDropBox from "../common/StatusDropBox";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

class ProjectEditInputGroup extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.state = {
            id: props.project.id,
            createDate: props.project.createDate,
            name: props.project.name,
            description: props.project.description,
            startDate: props.project.startDate,
            endDate: props.project.endDate,
            status: props.project.status,
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.changeStatus = this.changeStatus.bind(this);

    }

    componentWillReceiveProps(nextProps, nextContext) {
        if (nextProps.project !== this.props.project) {
            this.setState({
                id: nextProps.project.id,
                createDate: nextProps.project.createDate,
                name: nextProps.project.name,
                description: nextProps.project.description,
                startDate: nextProps.project.startDate,
                endDate: nextProps.project.endDate,
                status: nextProps.project.status,
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
                    <StatusDropBox status={this.state.status} onChange={this.changeStatus}/>
                    <br/>
                    <Button type={"submit"}>SAVE</Button>
                </Form>
            </div>
        )
    }

}

export default ProjectEditInputGroup;