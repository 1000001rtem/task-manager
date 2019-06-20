import * as React from "react";
import Header from "../fragment/header/Header";
import Footer from "../fragment/footer/Footer";
import {BrowserRouter, Route} from "react-router-dom";
import MainContent from "../fragment/main/MainContent";
import ProjectContentContainer from "../container/project/ProjectContentContainer";
import TaskContentContainer from "../container/task/TaskContentContainer";
import LoginContent from "../fragment/login/LoginContent";
import ProjectEditContentContainer from "../container/project/ProjectEditContentContainer";
import ProjectCreateContentContainer from "../container/project/ProjectCreateContentContainer";
import TaskCreateContentContainer from "../container/task/TaskCreateContentContainer";
import TaskEditContentContainer from "../container/task/TaskEditContentContainer";
import PrivateRoute from "../route/PrivateRoute";
import TitleContent from "../fragment/main/TitleContent";
import UserContentContainer from "../container/user/UserContentContainer";
import PrivateAdminRoute from "../route/PrivateAdminRoute";
import RegistrationContent from "../fragment/registration/RegistrationContent";

class MainView extends React.Component {

    render() {
        return (
            <div className={"view"}>
                <Header/>
                <BrowserRouter>
                    <Route exact path='/' component={TitleContent}/>
                    <Route path='/login' component={LoginContent}/>
                    <Route path='/registration' component={RegistrationContent}/>
                    <PrivateRoute path='/main' component={MainContent}/>
                    <PrivateRoute path='/project' component={ProjectContentContainer}/>
                    <PrivateRoute path='/task' component={TaskContentContainer}/>
                    <PrivateRoute path='/createProject' component={ProjectCreateContentContainer}/>
                    <PrivateRoute path='/projectEdit/:projectId' component={ProjectEditContentContainer}/>
                    <PrivateRoute path='/createTask' component={TaskCreateContentContainer}/>
                    <PrivateRoute path='/taskEdit/:taskId' component={TaskEditContentContainer}/>
                    <PrivateAdminRoute path='/user' component={UserContentContainer}/>
                </BrowserRouter>
                <Footer/>
            </div>
        )
    }

}

export default MainView;