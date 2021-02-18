import React from 'react';
import { Client } from "@stomp/stompjs";
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

import * as API_CAREGIVER from "./api/caregiver-api"
import CaregiverTable from "./components/caregiver-table";



class CaregiverContainer extends React.Component {


    ws = new WebSocket('ws://localhost:8080/activity');


    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reload = this.reload.bind(this);
        this.state = {
            selected: false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null,
            mesaj: []
        };
    }

    componentDidMount() {


        this.client = new Client();
        let newComponent = this.state.mesaj

        this.client.configure({
            brokerURL: 'ws://localhost:8080/activity',
            onConnect: () =>{
                console.log("Websocket is connected !");
                this.client.subscribe('/topic/message', message => {
                    //console.log(message.body);
                    newComponent.push(message.body)

                    this.setState({mesaj:newComponent})
                    console.log(this.state.mesaj)

                })
            },

            onWebSocketError: (e) => {
                console.log("Cannot connect to websocket ! ");
            }
        })

        this.client.activate();
        this.fetchCaregivers();
    }

    fetchCaregivers() {

        let name = localStorage.getItem('name');
        console.log(name);
        return API_CAREGIVER.getPatients(name,(result, status, err) => {

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


    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        this.fetchCaregivers();
    }

    render() {
        let name = localStorage.getItem('name');

        return (
            <div>
                <CardHeader>
                    <strong> {name} - Caregiver </strong>


                </CardHeader>
                <Card>
                    <Row>


                        <Col sm={{size: '10', offset: 1}}>
                            {this.state.isLoaded && <CaregiverTable tableData = {this.state.tableData}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                errorStatus={this.state.errorStatus}
                                error={this.state.error}
                            />   }
                        </Col>
                    </Row>

                    <div>

                            <table>
                                {this.state.mesaj.map((item,index) =>
                                        <tr key={index}>{item}</tr>
                                )}
                            </table>

                    </div>


                </Card>


            </div>



        )

    }
}


export default CaregiverContainer;
