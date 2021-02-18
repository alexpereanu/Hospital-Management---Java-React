import React from 'react';
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {
    Button,
    Card,
    CardHeader,
    Col,
    Modal,
    ModalBody,
    ModalHeader,
    Row
} from 'reactstrap';
import PatientForm from "./components/patient-form";

import * as API_PATIENTS from "./api/patient-api"
import PatientTable from "./components/patient-table";
import PatientDelete from "./components/patient-delete";
import PatientUpdate from "./components/patient-update";

class PatientContainer extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.toggleFormDelete = this.toggleFormDelete.bind(this);
        this.toggleFormUpdate = this.toggleFormUpdate.bind(this);
        this.reload = this.reload.bind(this);
        this.state = {
            selected: false,
            selectedDelete:false,
            selectedUpdate:false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };
    }

    componentDidMount() {
        this.fetchPatients();
    }

    fetchPatients() {
        return API_PATIENTS.getDTOPatients((result, status, err) => {

            if (result !== null && status === 200) {
                this.setState({
                    tableData: result,
                    isLoaded: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });
    }

    toggleForm() {
        this.setState({selected: !this.state.selected});
    }

    toggleFormDelete() {
        this.setState({selectedDelete: !this.state.selectedDelete});
    }

    toggleFormUpdate() {
        this.setState({selectedUpdate: !this.state.selectedUpdate});
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        this.fetchPatients();
    }

    reload2() {
        this.setState({
            isLoaded: false
        });
        this.toggleFormDelete();
        this.fetchPatients();
    }

    reload3() {
        this.setState({
            isLoaded: false
        });
        this.toggleFormUpdate();
        this.fetchPatients();
    }
    render() {
        return (
            <div>
                <CardHeader>
                    <strong> Patient Management </strong>
                </CardHeader>
                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleForm}>Add Patient </Button>
                        </Col>

                        <Col sm={{size: '10', offset: 1}}>
                            <Button title = 'Delete' color="primary" onClick={this.toggleFormUpdate}>Update Patient </Button>
                        </Col>

                        <Col sm={{size: '10', offset: 1}}>
                            <Button title = 'Delete' color="primary" onClick={this.toggleFormDelete}>Delete Patient </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <PatientTable tableData = {this.state.tableData}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />   }
                        </Col>
                    </Row>
                </Card>

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add Patient: </ModalHeader>
                    <ModalBody>
                        <PatientForm reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.selectedDelete} toggle={this.toggleFormDelete}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormDelete}> Delete Patient: </ModalHeader>
                    <ModalBody>
                        <PatientDelete reloadHandler={this.reload2}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.selectedUpdate} toggle={this.toggleFormDelete}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormDelete}> Update Patient: </ModalHeader>
                    <ModalBody>
                        <PatientUpdate reloadHandler={this.reload3}/>
                    </ModalBody>
                </Modal>
            </div>


        )

    }
}


export default PatientContainer;
