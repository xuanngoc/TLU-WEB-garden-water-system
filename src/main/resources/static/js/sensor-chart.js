const ctx = document.getElementById('myChart').getContext('2d');
const xLabels = [2020, 24923, 2131, 323, 13123, 12312, 4343];
const yHumility = [3,522,6,222,2,651,23];
const yTemperature = [30,422,22,41,564,42,21];

const myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: xLabels,
        datasets: [{
            label: 'Nhiet fo',
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
            label: 'Do am',
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
        }
    }
});
