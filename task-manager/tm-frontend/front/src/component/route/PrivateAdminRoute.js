import * as React from "react";
import {Redirect, Route} from "react-router-dom";

const PrivateAdminRoute = ({component: Component, ...rest}) => (
    <Route {...rest} render={(props) => (
        localStorage.getItem('login')
            ? <Component {...props} {...rest}/>
            : <Redirect to='/main'/>
    )}/>
);

export default PrivateAdminRoute;