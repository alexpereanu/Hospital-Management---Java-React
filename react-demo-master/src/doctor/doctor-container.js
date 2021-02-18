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
import DoctorForm from "./components/doctor-form";

import * as API_USERS from "./api/doctor-api"
import DoctorTable from "./components/doctor-table";
import DoctorUpdate from "./components/doctor-update";
import DoctorDelete from "./components/doctor-delete";
import * as API_CAREGIVER from "../caregiver/api/caregiver-api";
import CaregiverTable from "../caregiver/components/caregiver-table";



class DoctorContainer extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.toggleFormUpdate = this.toggleFormUpdate.bind(this);
        this.toggleFormDelete = this.toggleFormDelete.bind(this);
        this.toggleFormCaregiver = this.toggleFormCaregiver.bind(this);

        this.reload = this.reload.bind(this);
        this.state = {
            selected:false,
            selectedUpdate:false,
            selectedDelete:false,
            getCaregiver:false,
            select: ['none','add','upgrade'],
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            isLoadedCaregiver:false,
            errorStatus: 0,
            error: null
        };
    }

    componentDidMount() {
        this.fetchDoctors();
    }

    fetchDoctors() {
        return API_USERS.getDTODoctors((result, status, err) => {

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

    toggleFormUpdate() {
        this.setState({selectedUpdate: !this.state.selectedUpdate});
    }

    toggleFormDelete() {
        this.setState({selectedDelete: !this.state.selectedDelete});
    }

    toggleFormCaregiver() {
        this.setState({getCaregiver: !this.state.getCaregiver});
    }


    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        this.fetchDoctors();
    }

    reload2() {
        this.setState({
            isLoaded: false
        });
        this.toggleFormUpdate();
        this.fetchDoctors();
    }

    reload3() {
        this.setState({
            isLoaded: false
        });
        this.toggleFormDelete();
        this.fetchDoctors();
    }

    render() {
        return (
            <div>
                <CardHeader>
                    <strong> DOCTOR Management </strong>
                </CardHeader>
                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '10', offset: 1}}>
                            <Button title = 'Add' color="primary" onClick={this.toggleForm}>Add Doctor </Button>
                        </Col>

                        <Col sm={{size: '10', offset: 1}}>
                            <Button title = 'Upp' color="primary" onClick={this.toggleFormUpdate}>Update Doctor </Button>
                        </Col>

                        <Col sm={{size: '10', offset: 1}}>
                            <Button title = 'Delete' color="primary" onClick={this.toggleFormDelete}>Delete Doctor </Button>
                        </Col>


                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '10', offset: 1}}>
                            {this.state.isLoaded && <DoctorTable tableData = {this.state.tableData}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />   }
                        </Col>
                    </Row>

                    <Row>
                        <Col sm={{size: '10', offset: 1}}>
                            {this.state.isLoadedCaregiver && <CaregiverTable tableData = {this.state.tableData}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />   }
                        </Col>
                    </Row>
                </Card>

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add Doctor: </ModalHeader>
                    <ModalBody>
                        <DoctorForm reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.selectedUpdate} toggle={this.toggleFormUpdate}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormUpdate}> Update Doctor: </ModalHeader>
                    <ModalBody>
                        <DoctorUpdate reloadHandler={this.reload2}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.selectedDelete} toggle={this.toggleFormDelete}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormDelete}> Delete Doctor: </ModalHeader>
                    <ModalBody>
                        <DoctorDelete reloadHandler={this.reload3}/>
                    </ModalBody>
                </Modal>

            </div>
        )

    }
}


export default DoctorContainer;
