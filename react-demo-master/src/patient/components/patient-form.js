import React from 'react';
//import validate from "./validators/person-validators";
import Button from "react-bootstrap/Button";
import * as API_PATIENTS from "../api/patient-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';



class PatientForm extends React.Component {

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
                    placeholder: 'Introduce patient ID',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isNumber:true,
                        isRequired: true
                    }
                },
                name: {
                    value: '',
                    placeholder: 'Patient name  ',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

                gender: {
                    value: '',
                    placeholder: 'Patient gender  ',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

                address: {
                    value: '',
                    placeholder: 'Patient address  ',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

                doctorId: {
                    value: '',
                    placeholder: 'Doctor ID ',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

                caregiverId: {
                    value: '',
                    placeholder: 'Caregiver ID ',
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

    registerPatient (patient) {
        return API_PATIENTS.postPatient(patient, patient.doctorId, patient.caregiverId, (result, status, error) => {
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
        let patient = {
            id: this.state.formControls.id.value,
            name: this.state.formControls.name.value,
            gender: this.state.formControls.gender.value,
            address: this.state.formControls.address.value,
            doctorId: this.state.formControls.doctorId.value,
            caregiverId: this.state.formControls.caregiverId.value,

        };

        console.log(patient);
        this.registerPatient(patient);
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

                <FormGroup id='name'>
                    <Label for='nameField'> NAME: </Label>
                    <Input name='name' id='nameField' placeholder={this.state.formControls.name.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.name.value}
                           touched={this.state.formControls.name.touched? 1 : 0}
                           valid={this.state.formControls.name.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='gender'>
                    <Label for='genderField'> GENDER: </Label>
                    <Input name='gender' id='genderField' placeholder={this.state.formControls.gender.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.gender.value}
                           touched={this.state.formControls.gender.touched? 1 : 0}
                           valid={this.state.formControls.gender.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='address'>
                    <Label for='addressField'> ADDRESS: </Label>
                    <Input name='address' id='addressField' placeholder={this.state.formControls.address.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.address.value}
                           touched={this.state.formControls.address.touched? 1 : 0}
                           valid={this.state.formControls.address.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='doctorId'>
                    <Label for='doctorIdField'> DOCTOR-ID: </Label>
                    <Input name='doctorId' id='doctorIdField' placeholder={this.state.formControls.doctorId.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.doctorId.value}
                           touched={this.state.formControls.doctorId.touched? 1 : 0}
                           valid={this.state.formControls.doctorId.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='caregiverId'>
                    <Label for='caregiverIdField'> CAREGIVER-ID: </Label>
                    <Input name='caregiverId' id='caregiverIdField' placeholder={this.state.formControls.caregiverId.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.caregiverId.value}
                           touched={this.state.formControls.caregiverId.touched? 1 : 0}
                           valid={this.state.formControls.caregiverId.valid}
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

export default PatientForm;
