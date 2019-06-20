import * as React from "react";
import Container from "react-bootstrap/Container";
import {userActions} from "../../../action/user.actions";
import Alert from "react-bootstrap/Alert";

class LoginContent extends React.Component {

    constructor(props) {
        super(props);
        userActions.logout();
        this.state = {
            login: '',
            password: '',
            show: false,
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleDismiss = this.handleDismiss.bind(this);
        this.handleShow = this.handleShow.bind(this);
        this.alertHandler = this.alertHandler.bind(this);
    }

    handleChange(e) {
        const name = e.target.name;
        const value = e.target.value;
        this.setState({[name]: value});
    }

    handleDismiss = () => this.setState({show: false});
    handleShow = () => this.setState({show: true});

    handleSubmit(e) {
        e.preventDefault();
        const {login, password} = this.state;
        if (login && password) {
            userActions.login(login, password).then(res => {
                    console.log();
                    if (localStorage.getItem("token")) {
                        this.props.history.push('/main');
                        window.location.reload();
                    } else {
                        this.handleShow();
                    }
                }
            )
        }
    }

    alertHandler() {
        if (this.state.show) {
            return <Alert variant="danger" onClose={this.handleDismiss} dismissible>
                <Alert.Heading>Error</Alert.Heading>
                <p>
                    Wrong Login or Password;
                </p>
            </Alert>
        }
    }

    render() {
        const {login, password} = this.state;
        return (
            <div className={"content"}>
                <Container>
                    {this.alertHandler()}
                    <div className={"loginBox"}>
                        <form className="form-signin" onSubmit={this.handleSubmit}>
                            <h2 className="form-signin-heading">Please login</h2>
                            <input type="text" className="form-control" name="login" value={login}
                                   placeholder="Login"
                                   required=""
                                   autoFocus=""
                                   onChange={this.handleChange}/>
                            <input type="password" className="form-control" name="password" value={password}
                                   placeholder="Password"
                                   required=""
                                   onChange={this.handleChange}/>
                            <button className="btn btn-lg btn-primary btn-block" type="submit">Login</button>
                        </form>
                    </div>
                </Container>
            </div>
        )
    }

}

export default LoginContent;