import * as React from "react";
import {userActions} from "../../../action/admin.actions";
import UserContent from "../../fragment/user/UserContent";

class UserContentContainer extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            users: []
        };
        this.removeHandle = this.removeHandle.bind(this);
    }

    componentDidMount() {
        userActions.findAll().then(res => {
            this.setState({users: res});
            console.log(this.state.users);
        });
    }

    removeHandle(userId) {
        userActions.remove(userId);
        window.location.reload();
    }

    render() {
        return (
            <UserContent users={this.state.users} removeHandle={this.removeHandle}/>
        )
    }
}

export default UserContentContainer;