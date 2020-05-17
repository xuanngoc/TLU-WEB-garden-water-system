const ctx = document.getElementById('myChart').getContext('2d');
const xLabels = [];
const yHumility = [];
const yTemperature = [];
const yHumility1 = [];
const yTemperature1 = [];


function getDataHumility(gardenId) {
    fetch('/api/sensor-value/humility/' + gardenId)
        .then((resp) => {
                return resp.json();
             })// Transform the data into json
        .then(function(data) {
            data.forEach(obj => {
                yHumility.push(obj.avg);
                const d = new Date(obj.year, obj.month - 1, obj.day, obj.hour , obj.minute).toLocaleString();
                xLabels.push(d);
            });
        })
}

function getDataTemperature(gardenId) {
    fetch('/api/sensor-value/temperature/' + gardenId)
        .then((resp) => {
                return resp.json();
             })// Transform the data into json
        .then(function(data) {
            data.forEach(obj => {
                yTemperature.push(obj.avg);
            });
        })
}
const gardenId = window.location.href.split('/').pop();
getDataHumility(gardenId);
getDataTemperature(gardenId);

const myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: xLabels,
        datasets: [{
            label: 'Nhiệt độ',
            data: yTemperature,
            backgroundColor: [
                'rgba(0 , 99, 7, 0)',
            ],
            borderColor: [
                'rgba(255, 99, 7, 1)',
            ],
            borderWidth: 1
        },
        {
            label: 'Độ ẩm',
            data: yHumility,
            backgroundColor: [
                'rgba(0, 233, 9, 0)',
            ],
            borderColor: [
                'rgba(0, 233, 9, 1)',
            ],
            borderWidth: 1
            },
        ]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        },
         title: {
                    display: true,
                    text: 'BIỂU ĐỒ ĐƯỜNG THỂ HIỆN ĐỘ ẨM VÀ NHIỆT ĐỌ CỬA VƯỜN'
                }
    }
});
