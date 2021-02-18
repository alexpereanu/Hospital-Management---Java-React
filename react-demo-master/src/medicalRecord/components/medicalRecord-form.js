import React from 'react';
//import validate from "./validators/person-validators";
import Button from "react-bootstrap/Button";
import * as API_MEDICALRECORDS from "../api/medicalRecord-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';



class MedicalRecordForm extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls: {
                id: {
                    value: '',
                    placeholder: 'Introduce Medical Record ID',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isNumber:true,
                        isRequired: true
                    }
                },
                daysOfTreatment: {
                    value: '',
                    placeholder: 'Days of Treatment  ',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

                intakeIntervals: {
                    value: '',
                    placeholder: '',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

                medicalCondition: {
                    value: '',
                    placeholder: '',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

                medicationId: {
                    value: '',
                    placeholder: 'Medication ID ',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

                patientId: {
                    value: '',
                    placeholder: 'Patient ID ',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
    }


    handleChange = event => {

        const name = event.target.name;
        const value = event.target.value;

        const updatedControls = this.state.formControls;

        const updatedFormElement = updatedControls[name];

        updatedFormElement.value = value;
        updatedFormElement.touched = true;
        //updatedFormElement.valid = validate(value, updatedFormElement.validationRules);
        updatedControls[name] = updatedFormElement;

        let formIsValid = true;
        for (let updatedFormElementName in updatedControls) {
            formIsValid = updatedControls[updatedFormElementName].valid && formIsValid;
        }

        this.setState({
            formControls: updatedControls,
            formIsValid: formIsValid
        });

    };

    registerPatient (medicalRecord) {
        return API_MEDICALRECORDS.insertMedicalRecord(medicalRecord, medicalRecord.medicationId, medicalRecord.patientId, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted patient with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    handleSubmit() {
        let medicalRecord = {
            id: this.state.formControls.id.value,
            daysOfTreatment: this.state.formControls.daysOfTreatment.value,
            intakeIntervals: this.state.formControls.intakeIntervals.value,
            medicalCondition: this.state.formControls.medicalCondition.value,
            medicationId: this.state.formControls.medicationId.value,
            patientId: this.state.formControls.patientId.value,

        };

        console.log(medicalRecord);
        this.registerPatient(medicalRecord);
    }

    render() {
        return (
            <div>

                <FormGroup id='ID'>
                    <Label for='idField'> ID: </Label>
                    <Input name='id' id='idField' placeholder={this.state.formControls.id.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.id.value}
                           touched={this.state.formControls.id.touched? 1 : 0}
                           valid={this.state.formControls.id.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='daysOfTreatment'>
                    <Label for='daysOfTreatmentField'> Days of Treatment </Label>
                    <Input name='daysOfTreatment' id='daysOfTreatmentField' placeholder={this.state.formControls.daysOfTreatment.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.daysOfTreatment.value}
                           touched={this.state.formControls.daysOfTreatment.touched? 1 : 0}
                           valid={this.state.formControls.daysOfTreatment.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='intakeIntervals'>
                    <Label for='intakeIntervalsField'> Intake intervals </Label>
                    <Input name='intakeIntervals' id='intakeIntervalsField' placeholder={this.state.formControls.intakeIntervals.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.intakeIntervals.value}
                           touched={this.state.formControls.intakeIntervals.touched? 1 : 0}
                           valid={this.state.formControls.intakeIntervals.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='medicalCondition'>
                    <Label for='medicalConditionField'> Medical condition </Label>
                    <Input name='medicalCondition' id='medicalConditionField' placeholder={this.state.formControls.medicalCondition.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.medicalCondition.value}
                           touched={this.state.formControls.medicalCondition.touched? 1 : 0}
                           valid={this.state.formControls.medicalCondition.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='medicationId'>
                    <Label for='medicationIdField'> Medication id </Label>
                    <Input name='medicationId' id='medicationIdField' placeholder={this.state.formControls.medicationId.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.medicationId.value}
                           touched={this.state.formControls.medicationId.touched? 1 : 0}
                           valid={this.state.formControls.medicationId.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='patientId'>
                    <Label for='patientIdField'> Patient ID </Label>
                    <Input name='patientId' id='patientIdField' placeholder={this.state.formControls.patientId.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.patientId.value}
                           touched={this.state.formControls.patientId.touched? 1 : 0}
                           valid={this.state.formControls.patientId.valid}
                           required
                    />
                </FormGroup>


                <Row>
                    <Col sm={{size: '4', offset: 8}}>
                        <Button type={"submit"} onClick={this.handleSubmit}>  Submit </Button>
                    </Col>
                </Row>

                {
                    this.state.errorStatus > 0 &&
                    <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                }
            </div>
        ) ;
    }
}

export default MedicalRecordForm;
