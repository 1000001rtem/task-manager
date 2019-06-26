import * as React from "react";
import Container from "react-bootstrap/Container";
import {testActions} from "../../../action/test.actions";

class Footer extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.state={
            port:0,
        }
    }

    componentDidMount() {
        testActions.ping().then(e=>{
            console.log(e);
            this.setState({port:e});
        })
    }

    render() {
        return (
            <div className={"footer"}>
                <Container/>
            </div>
        )
    }

}

export default Footer;