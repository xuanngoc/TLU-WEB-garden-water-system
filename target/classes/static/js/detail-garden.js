
function toggle(state, deviceId) {
    console.log(deviceId);
    var xhttp = new XMLHttpRequest();
     xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             console.log("device's state was changed");
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
             console.log("sensor's state was changed");
        }
    };
    xhttp.open("POST", "/api/sensor/update/state/" + sensorId, true);
    xhttp.send();
}