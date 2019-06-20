import * as React from "react";
import Container from "react-bootstrap/Container";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import {Link} from "react-router-dom";
import Pagination from "react-js-pagination";

function getProject(projects, projectId) {
    for (let i = 0; i < projects.length; i++) {
        if (projects[i].id === projectId) return projects[i].name;
    }
}

class TaskContent extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.handleClick = this.handleClick.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
    }

    handleClick = (taskId) => {
        this.props.removeHandle(taskId);
    };

    handlePageChange(page) {
        this.props.handlePageChange(page);
    }

    render() {
        return (
            <div className={"content"}>
                <Container>
                    <div className={"tableBox"}>
                        <Table>
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Status Date</th>
                                <th>Project</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.props.tasks.map((e, i) =>
                                <tr>
                                    <td>{i + 1 + 10 * this.props.page.currentPageNumber}</td>
                                    <td>
                                        <Link to={`/taskEdit/${e.id}`}>
                                            {e.name}
                                        </Link>
                                    </td>
                                    <td>{e.description}</td>
                                    <td>{e.startDate}</td>
                                    <td>{e.endDate}</td>
                                    <td>{e.status}</td>
                                    <td>{getProject(this.props.projects, e.projectId)}</td>
                                    <td>
                                        <Button onClick={() => this.handleClick(e.id)}>Delete</Button>
                                    </td>
                                </tr>
                            )}
                            </tbody>
                        </Table>
                        <Pagination
                            itemClass="page-item"
                            linkClass="page-link"
                            activePage={this.props.page.currentPageNumber + 1}
                            itemsCountPerPage={10}
                            totalItemsCount={this.props.page.totalItems}
                            pageRangeDisplayed={5}
                            onChange={this.handlePageChange}
                        />
                        <Button href={'/createTask'}>NEW</Button>
                    </div>
                </Container>
            </div>
        )
    }

}

export default TaskContent;