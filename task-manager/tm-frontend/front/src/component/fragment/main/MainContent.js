import * as React from "react";
import Container from "react-bootstrap/Container";
import Image from "react-bootstrap/Image";
import logo from '../../../image/titleLogo.png'

class MainContent extends React.Component {

    render() {
        return (
            <div className={"content"}>
                <Container>
                    <div className={"greetingsContainer"}>
                        <div className={"greetingsBox"}>
                            <div>
                                <h3>Welcome to Task Manager</h3>
                            </div>
                            <div className={"imageBox"}>
                                <Image src={logo}/>
                            </div>
                        </div>
                    </div>
                </Container>
            </div>
        )
    }

}

export default MainContent;