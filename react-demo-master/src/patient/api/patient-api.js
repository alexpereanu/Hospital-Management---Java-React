import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";


const endpoint = {
    patient: '/patient'
};

function getDTOPatients(callback) {
    let request = new Request(HOST.backend_api + '/patient', {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function postPatient(patient, doctorId, caregiverId, callback){
    let request = new Request(HOST.backend_api + endpoint.patient+"/insertPatient/"+doctorId+"/"+caregiverId , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(patient)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function deletePatient(name, callback){
    let request = new Request(HOST.backend_api + endpoint.patient+"/deletePatientByName/"+name , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function updatePatient(name, patient, callback){
    let request = new Request(HOST.backend_api + endpoint.patient+"/updatePatientByName/"+name, {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(patient)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

export {
    getDTOPatients,
    deletePatient,
    postPatient,
    updatePatient
};
