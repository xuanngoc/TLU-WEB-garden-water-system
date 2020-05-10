
function toggle(state, deviceId) {
    console.log(deviceId);
    var xhttp = new XMLHttpRequest();
     xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             console.log("send ok");
         }
     };
     xhttp.open("POST", "/api/device/update/state/" + deviceId, true);
     xhttp.send();
}