let xLabelsHumility = [];
let xLabelsTemperature = [];

let yHumility = [];
let yTemperature = [];

let humility = new Map();
let temperature = new Map();


async function getDataHumility(gardenId) {
    return fetch('/api/sensor-value/humility/' + gardenId)
        .then((resp) => {
                return resp.json();
             })// Transform the data into json
        .then(function(data) {
            //const humility = new Map();
            data.forEach(obj => {
                yHumility.push(obj.avg);
                const date = new Date(obj.year, obj.month - 1, obj.day, obj.hour , obj.minute).toLocaleString();
                xLabelsHumility.push(date);
                humility.set(obj.avg, date);
            });
            return humility;
        });
}

async function getDataTemperature(gardenId) {
    return fetch('/api/sensor-value/temperature/' + gardenId)
        .then((resp) => {
                return resp.json();
             })
        .then(function(data) {
            //const temperature = new Map();
            data.forEach(obj => {
                yTemperature.push(obj.avg);
                const date = new Date(obj.year, obj.month - 1, obj.day, obj.hour , obj.minute).toLocaleString();
                console.log(obj);
                xLabelsTemperature.push(date);
                temperature.set(obj.avg, date);
            });
            return temperature;
        });
}

async function getXLabelMaxLength(gardenId) {
    humility = await getDataHumility(gardenId);
    temperature = await getDataTemperature(gardenId);

    //console.log(humility);
    return humility.size > temperature.size ? [...humility.values()] : [...temperature.values()];
}

const gardenId = window.location.href.split('/').pop();
//getDataTemperature(gardenId);
//getDataHumility(gardenId);

const xLabels = []
let xPromise = getXLabelMaxLength(gardenId); // now is Promise
xPromise = Promise.resolve(xPromise);
xPromise.then(value => {
    xLabels.push(value)
    const ctx = document.getElementById('myChart').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: xLabels[0],
            datasets: [{
                label: 'Nhiệt độ',
                data: [...temperature.keys()],
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
                data: [...humility.keys()],
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
            events: ['click'],
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

});
