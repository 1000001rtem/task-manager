import * as React from "react";
import Dropdown from "react-bootstrap/Dropdown";

class StatusDropBox extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.state = {
            selectedStatus: null
        };
    }

    onSelect = (eventKey) => {
        this.setState({selectedStatus: eventKey});
        this.props.onChange(eventKey);
    };

    render() {
        return (
            <Dropdown title={this.state.selectedStatus} onSelect={this.onSelect} id="d">
                <Dropdown.Toggle>
                    {this.state.selectedStatus ? this.state.selectedStatus : this.props.status}
                </Dropdown.Toggle>
                <Dropdown.Menu>
                    {statuses.map(status => (
                        <Dropdown.Item eventKey={status.name} key={status.id}>
                            {status.name}
                        </Dropdown.Item>
                    ))}
                </Dropdown.Menu>
            </Dropdown>
        )
    }

}

const statuses = [
    {id: 1, name: 'PLANNED'},
    {id: 2, name: 'DURING'},
    {id: 3, name: 'DONE'},
];

export default StatusDropBox;
