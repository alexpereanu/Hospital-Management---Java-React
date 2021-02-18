import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";


const endpoint = {
    medication: '/medication'
};

function getMedications(callback) {
    let request = new Request(HOST.backend_api + endpoint.medication, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function insertMedication(medicament, callback){
    let request = new Request(HOST.backend_api + endpoint.medication+"/insertMedication" , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(medicament)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function updateMedication(medicationId, medicament, callback){
    let request = new Request(HOST.backend_api + endpoint.medication+"/updateMedicationById/"+medicationId, {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(medicament)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}
function deleteMedication(medicationId, callback){
    let request = new Request(HOST.backend_api + endpoint.medication+"/deleteMedicationById/" + medicationId , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}
export {
    getMedications,
    insertMedication,
    updateMedication,
    deleteMedication

};
