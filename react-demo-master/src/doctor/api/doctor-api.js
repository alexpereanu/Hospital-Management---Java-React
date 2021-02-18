import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";


const endpoint = {
    doctor: '/doctor'
};

function getDTODoctors(callback) {
    let request = new Request(HOST.backend_api + endpoint.doctor, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function getDoctorById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.doctor + params.id, {
        method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function postDoctor(doctor, callback){
    let request = new Request(HOST.backend_api + endpoint.doctor+"/insert" , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(doctor)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function deleteDoctor(name, callback){
    let request = new Request(HOST.backend_api + endpoint.doctor+"/deleteDoctorByName/"+name , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function updateDoctor(name, doctor, callback){
    let request = new Request(HOST.backend_api + endpoint.doctor+"/updateDoctorByName/"+name, {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(doctor)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

export {
    getDTODoctors,
    deleteDoctor,
    postDoctor,
    updateDoctor
};
