import React from 'react';
import Button from "react-bootstrap/Button";
import * as API_MEDICAMENTS from "../api/medication-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';



class MedicationForm extends React.Component {

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
                    placeholder: 'Medicament ID',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isNumber:true,
                        isRequired: true
                    }
                },
                medicamentName: {
                    value: '',
                    placeholder: 'Medicament name ',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

                dosage: {
                    value: '',
                    placeholder: 'Dosage  ',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },

                sideEffects: {
                    value: '',
                    placeholder: 'Side Effects',
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

    registerMed (medicament) {
        return API_MEDICAMENTS.insertMedication(medicament, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted medicament with id: " + result);
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
        let medicament = {
            id: this.state.formControls.id.value,
            medicamentName: this.state.formControls.medicamentName.value,
            dosage: this.state.formControls.dosage.value,
            sideEffects: this.state.formControls.sideEffects.value,


        };

        console.log(medicament);
        this.registerMed(medicament);
    }

    render() {
        return (
            <div>

                <FormGroup id='ID'>
                    <Label for='idField'> ID </Label>
                    <Input name='id' id='idField' placeholder={this.state.formControls.id.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.id.value}
                           touched={this.state.formControls.id.touched? 1 : 0}
                           valid={this.state.formControls.id.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='medicamentName'>
                    <Label for='medicamentNameField'> MEDICAMENT NAME </Label>
                    <Input name='medicamentName' id='medicamentNameField' placeholder={this.state.formControls.medicamentName.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.medicamentName.value}
                           touched={this.state.formControls.medicamentName.touched? 1 : 0}
                           valid={this.state.formControls.medicamentName.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='dosage'>
                    <Label for='dosageField'> DOSAGE </Label>
                    <Input name='dosage' id='dosageField' placeholder={this.state.formControls.dosage.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.dosage.value}
                           touched={this.state.formControls.dosage.touched? 1 : 0}
                           valid={this.state.formControls.dosage.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='sideEffects'>
                    <Label for='sideEffectsField'> SIDE EFFECTS </Label>
                    <Input name='sideEffects' id='sideEffectsField' placeholder={this.state.formControls.sideEffects.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.sideEffects.value}
                           touched={this.state.formControls.sideEffects.touched? 1 : 0}
                           valid={this.state.formControls.sideEffects.valid}
                           required
                    />
                </FormGroup>


                <Row>
                    <Col sm={{size: '4', offset: 8}}>
                        <Button type={"submit"} onClick={this.handleSubmit}> Register </Button>
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

export default MedicationForm;
