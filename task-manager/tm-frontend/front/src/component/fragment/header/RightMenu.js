import * as React from "react";
import Navbar from "react-bootstrap/Navbar";
import NavLink from "react-bootstrap/NavLink";


class RightMenu extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.checkAdmin = this.checkAdmin.bind(this);
    }

    logout() {
        localStorage.clear();
    }

    checkAdmin() {
        if (localStorage.getItem('role') === 'ADMIN') {
            return <NavLink href={"/user"}>Users</NavLink>
        }
    }

    render() {
        return (
            <div className={"rightMenu"}>
                <Navbar>
                    {this.checkAdmin()}
                    <NavLink href={"/"} onClick={this.logout}>Logout</NavLink>
                </Navbar>
            </div>
        )
    }

}

export default RightMenu;