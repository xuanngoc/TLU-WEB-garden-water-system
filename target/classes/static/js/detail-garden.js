
function toggle(state, deviceId) {
    console.log(deviceId);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            //document.getElementById('status-device-' + deviceId).innerHTML ;
            getStatus(deviceId);
            //console.log("device's state was changed");
        }
     };
     xhttp.open("POST", "/api/device/update/state/" + deviceId, true);
     xhttp.send();
}

function toggleSensor(state, sensorId) {
    console.log(sensorId);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
             //console.log("sensor's state was changed");
        }
    };
    xhttp.open("POST", "/api/sensor/update/state/" + sensorId, true);
    xhttp.send();
}

function getStatus(deviceId) {
    let url = '/api/device/' + deviceId + '/status';

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
             //console.log(xhttp.response);
            document.getElementById('status-device-' + deviceId).innerHTML  = xhttp.response;
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function plantWater() {
    const gardenId = window.location.href.split('/').pop();

    let url = '/api/plant-water/' + gardenId;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
             //console.log(xhttp.response);
            console.log("ok");
        }
    };
    xhttp.open("PUT", url, true);
    xhttp.send();
}

setInterval(function(){
    plantWater()
}, 10000);