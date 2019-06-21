import * as React from "react";
import Header from "../fragment/header/Header";
import Footer from "../fragment/footer/Footer";
import TaskContent from "../fragment/task/TaskContent";

class TaskView extends React.Component {

    render() {
        return (
            <div className={"view"}>
                <Header/>
                <TaskContent/>
                <Footer/>
            </div>
        )
    }

}

export default TaskView;