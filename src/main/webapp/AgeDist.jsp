<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Распределение Сотрудников По вВозрасту</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>

<h2>Гистограмма распределения сотрудников по возрасту</h2>
<canvas id="ageChart" width="400" height="200"></canvas>

<script>
    const ages = ${ages};
    const ageCounts = {};

    ages.forEach(age => {
        ageCounts[age] = (ageCounts[age] || 0) + 1;
    });

    const labels = Object.keys(ageCounts);
    const data = Object.values(ageCounts);

    const ctx = document.getElementById('ageChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Число Работников',
                data: data,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
</body>
</html>
