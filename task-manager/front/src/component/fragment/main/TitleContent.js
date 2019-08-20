import * as React from "react";
import Container from "react-bootstrap/Container";
import Image from "react-bootstrap/Image";
import Button from "react-bootstrap/Button";
import logo from '../../../image/titleLogo.png'

class TitleContent extends React.Component {

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
                        <div className={'buttonBox'}>
                            <Button href={'/login'}>Login</Button>
                            <Button href={'/registration'}>Registration</Button>
                        </div>
                    </div>
                </Container>
            </div>
        )
    }

}

export default TitleContent;