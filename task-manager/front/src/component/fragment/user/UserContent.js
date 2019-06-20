import * as React from "react";
import Container from "react-bootstrap/Container";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

class UserContent extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick = (userId) => {
        this.props.removeHandle(userId);
    };

    render() {
        return (
            <div className={"content"}>
                <Container>
                    <div className={"tableBox"}>
                        <Table>
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Id</th>
                                <th>Login</th>
                                <th>Create Date</th>
                                <th>Role</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.props.users.map((e, i) =>
                                <tr>
                                    <td>{i + 1}</td>
                                    <td>{e.id}</td>
                                    <td>{e.login}</td>
                                    <td>{e.createDate}</td>
                                    <td>{e.role}</td>
                                    <td>
                                        <Button onClick={() => this.handleClick(e.id)}>Delete</Button>
                                    </td>
                                </tr>
                            )}
                            </tbody>
                        </Table>
                    </div>
                </Container>
            </div>
        )
    }
}

export default UserContent;