import * as React from "react";
import Container from "react-bootstrap/Container";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import {Link} from "react-router-dom";
import Pagination from "react-js-pagination";

class ProjectContent extends React.Component {

    constructor(props, context) {
        super(props, context);
        this.handleClick = this.handleClick.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
    }

    handleClick = (projectId) => {
        this.props.removeHandle(projectId);
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
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.props.projects.map((e, i) =>
                                <tr>
                                    <td>{i + 1 + 10 * this.props.page.currentPageNumber}</td>
                                    <td>
                                        <Link to={`/projectEdit/${e.id}`}>
                                            {e.name}
                                        </Link>
                                    </td>
                                    <td>{e.description}</td>
                                    <td>{e.startDate}</td>
                                    <td>{e.endDate}</td>
                                    <td>{e.status}</td>
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
                    </div>
                    <Button href={'/createProject'}>NEW</Button>
                </Container>
            </div>
        )
    }

}

export default ProjectContent;