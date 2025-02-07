import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import NavigationBar from './navigation-bar'
import Home from './home/home';
import PersonContainer from './person/person-container'

import ErrorPage from './commons/errorhandling/error-page';
import styles from './commons/styles/project-style.css';
import DoctorContainer from "./doctor/doctor-container";
import Login from "./login/Login";
import CaregiverContainer from "./caregiver/caregiver-container";
import PatientContainer from "./patient/patient-container";
import MedicationContainer from "./medication/medication-container";
import MedicalRecordContainer from "./medicalRecord/medicalRecord-container";

class App extends React.Component {


    render() {

        return (
            <div className={styles.back}>
            <Router>
                <div>
                    <NavigationBar />
                    <Switch>

                        <Route
                            exact
                            path='/'
                            render={() => <Home/>}
                        />

                        <Route
                            exact
                            path='/person'
                            render={() => <PersonContainer/>}
                        />

                        <Route
                            exact
                            path='/doctor'
                            render={() => <DoctorContainer/>}
                        />

                        <Route
                            exact
                            path='/caregiver'
                            render={() => <CaregiverContainer/>}
                        />

                        <Route
                            exact
                            path='/login'
                            render={() =>

                                <Login/>}
                        />

                        <Route
                            exact
                            path='/patient'
                            render={() =>

                                <PatientContainer/>}
                        />

                        <Route
                            exact
                            path='/medication'
                            render={() =>

                                <MedicationContainer/>}
                        />

                        <Route
                            exact
                            path='/medicalRecord'
                            render={() =>

                                <MedicalRecordContainer/>}
                        />

                        {/*Error*/}
                        <Route
                            exact
                            path='/error'
                            render={() => <ErrorPage/>}
                        />

                        <Route render={() =><ErrorPage/>} />
                    </Switch>
                </div>
            </Router>
            </div>
        )
    };
}

export default App
