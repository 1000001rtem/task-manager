import * as React from "react";
import Navbar from "react-bootstrap/Navbar";
import NavLink from "react-bootstrap/NavLink";

class LeftMenu extends React.Component {

    render() {
        return (
            <div className={"leftMenu"}>
                <Navbar>
                    <NavLink href="/main">Main</NavLink>
                    <NavLink href="/project">Project</NavLink>
                    <NavLink href="/task">Task</NavLink>
                </Navbar>
            </div>
        )
    }

}

export default LeftMenu;