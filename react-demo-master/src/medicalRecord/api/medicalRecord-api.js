import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";


const endpoint = {
    medicalRecord: '/medicalRecord'
};

function getMedicalRecord(callback) {
    let request = new Request(HOST.backend_api + endpoint.medicalRecord, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function insertMedicalRecord(medicalRecord,medicationId,patientId, callback){
    let request = new Request(HOST.backend_api + endpoint.medicalRecord+"/insert/" + medicationId + "/" + patientId , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(medicalRecord)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

export {
    getMedicalRecord,
    insertMedicalRecord

};