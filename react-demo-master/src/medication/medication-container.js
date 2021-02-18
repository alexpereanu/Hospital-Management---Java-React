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
import MedicationForm from "./components/medication-form";

import * as API_MEDICATIONS from "./api/medication-api"
import MedicationTable from "./components/medication-table";

import MedicationUpdate from "./components/medication-update";
import MedicationDelete from "./components/medication-delete";

class MedicationContainer extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.toggleFormDelete = this.toggleFormDelete.bind(this);
        this.toggleFormUpdateMed = this.toggleFormUpdateMed.bind(this);

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
        this.fetchMedications();
    }

    fetchMedications() {
        return API_MEDICATIONS.getMedications((result, status, err) => {

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

    toggleFormUpdateMed() {
        this.setState({selectedUpdate: !this.state.selectedUpdate});
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        this.fetchMedications();
    }

    reload2() {
        this.setState({
            isLoaded: false
        });
        this.toggleFormDelete();
        this.fetchMedications();
    }

    reload3() {
        this.setState({
            isLoaded: false
        });
        this.toggleFormUpdateMed();
        this.fetchMedications();
    }
    render() {
        return (
            <div>
                <CardHeader>
                    <strong> Medications </strong>
                </CardHeader>
                <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleForm}>Create New Medication </Button>
                        </Col>

                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleFormUpdateMed}>Update Existing Medication </Button>
                        </Col>

                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleFormDelete}>Delete Medication </Button>
                        </Col>

                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <MedicationTable tableData = {this.state.tableData}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />   }
                        </Col>
                    </Row>
                </Card>

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add Medication </ModalHeader>
                    <ModalBody>
                        <MedicationForm reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>


                <Modal isOpen={this.state.selectedUpdate} toggle={this.toggleFormUpdateMed}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormUpdateMed}> Update Medication </ModalHeader>
                    <ModalBody>
                        <MedicationUpdate reloadHandler={this.reload3}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.selectedDelete} toggle={this.toggleFormDelete}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleFormDelete}> Delete Medication </ModalHeader>
                    <ModalBody>
                        <MedicationDelete reloadHandler={this.reload2}/>
                    </ModalBody>
                </Modal>
            </div>


        )

    }
}


export default MedicationContainer;
