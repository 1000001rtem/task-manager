import * as React from "react";
import Container from "react-bootstrap/Container";
import LeftMenu from "./LeftMenu";
import RightMenu from "./RightMenu";

class Header extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.authorization = this.authorization.bind(this);
    }

    authorization() {
        if (localStorage.getItem('userId')) {
            return <div className={"menuPanel"}>
                <LeftMenu/>
                <RightMenu/>
            </div>
        }
    }

    render() {
        return (
            <div className={"header"}>
                <Container>
                    <div className={"menuPanel"}>
                        {this.authorization()}
                    </div>
                </Container>
            </div>
        )
    }

}

export default Header;