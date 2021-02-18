import React, {Component, useCallback} from "react";
import { Row, FormGroup, FormControl, FormLabel, Button } from 'react-bootstrap';
import './Login.css';
import Link, {withRouter} from "react-router-dom";
import CaregiverContainer from "../caregiver/caregiver-container";
import CaregiverTable from "../caregiver/components/caregiver-table";
import {getPatients} from "../caregiver/api/caregiver-api";
import Redirect from "react-router-dom/es/Redirect";

class Login extends Component {

    constructor(props) {
        super(props)


        this.state = {
            formData: {}, // Contains login form data
            errors: {}, // Contains login field errors
            formSubmitted: false, // Indicates submit status of login form
            loading: false, // Indicates in progress state of login form
            name: "",
            pass: "",
            caregiverData: {}

        };


    }

    handleInputChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        let { formData } = this.state;
        formData[name] = value;

        this.setState({
            formData: formData
        });
    }
    //function callback();



    validateLoginForm = (e) => {

        let errors = {};
        const { formData } = this.state;
        const name = formData.email;
        const pass = formData.password;

        this.setState({
            name: name,
            pass: pass
        });

        if(name === 'doctor' && pass === 'doctor'){
            this.props.history.push('/doctor');
        }

        if(name === 'admin' && pass === 'admin'){
            this.props.history.push('/medicalRecord');
        }

        let caregiver = {name: formData.email}
        localStorage.setItem('name', caregiver.name);
        localStorage.getItem('name');


        let alex = formData.email


        fetch("http://localhost:8080/caregiver/findCaregiver/"+alex,
                {
                    method: "GET",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                    },
                })
                .then((response) => response.json())
                .then((response) => {
                    this.setState({
                        caregiverData: response.id
                    })
                    console.log(response.id)
                })
                .catch(error => console.warn(error));


        console.log("pass = ", pass);
    }

    login = (e) => {

        e.preventDefault();

        let errors = this.validateLoginForm();

        console.log(e);

        if(errors === true){
            alert("You are successfully signed in...");
            window.location.reload()
        } else {

            this.setState({
                errors: errors,
                formSubmitted: true
            });
        }
    }

    render() {

        const { errors, formSubmitted } = this.state;

        console.log(this.state);

        return (
            <div className="Login">
                <Row>
                    <form onSubmit={this.login}>
                       <FormGroup controlId="email">
                            <FormLabel>Email</FormLabel>
                            <FormControl type="text" name="email" placeholder="Enter your email" onChange={this.handleInputChange} />

                        </FormGroup>
                        <FormGroup controlId="password">
                            <FormLabel>Password</FormLabel>
                            <FormControl type="password" name="password" placeholder="Enter your password" onChange={this.handleInputChange} />

                        </FormGroup>
                        <Button type="submit" bsStyle="primary">Sign-In</Button>
                    </form>
                    {this.state.name === "MureaS" && this.state.pass=== "caregiver" &&
                    <Redirect to="/caregiver"/>}
                    { this.state.pass == this.state.caregiverData &&
                    <Redirect to="/caregiver"/>}
                </Row>
            </div>
        )
    }
}

export default withRouter(Login);