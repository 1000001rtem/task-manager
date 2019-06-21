import * as React from "react";
import Header from "../fragment/header/Header";
import Footer from "../fragment/footer/Footer";
import ProjectContent from "../fragment/project/ProjectContent";

class ProjectView extends React.Component {

    render() {
        return (
            <div>
                <Header/>
                <ProjectContent/>
                <Footer/>
            </div>
        )
    }

}

export default ProjectView;