import {userActions} from "../../../action/user.actions";
import Container from "react-bootstrap/Container";
import * as React from "react";

class RegistrationContent extends React.Component {

    constructor(props) {
        super(props);
        userActions.logout();
        this.state = {
            login: '',
            password: '',
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({[name]: value});
    }

    handleSubmit(e) {
        e.preventDefault();
        const {login, password} = this.state;
        if (login && password) {
            userActions.registration(login, password).then(res => {
                this.props.history.push('/');
            })
        }
    }

    render() {
        const {login, password} = this.state;
        return (
            <div className={"content"}>
                <Container>
                    <div className={"loginBox"}>
                        <form className="form-signin" onSubmit={this.handleSubmit}>
                            <h2 className="form-signin-heading">Registration</h2>
                            <input type="text" className="form-control" name="login" value={login}
                                   placeholder="Login"
                                   required=""
                                   autoFocus=""
                                   onChange={this.handleChange}/>
                            <input type="password" className="form-control" name="password" value={password}
                                   placeholder="Password"
                                   required=""
                                   onChange={this.handleChange}/>
                            <button className="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                        </form>
                    </div>
                </Container>
            </div>
        )
    }

}

export default RegistrationContent;