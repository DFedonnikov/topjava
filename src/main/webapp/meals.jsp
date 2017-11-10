<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Meals</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid black;
            text-align: left;
            padding: 8px;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
    <tr>
        <th>Meal Type</th>
        <th>Date</th>
        <th>Calories</th>
        <th>Exceeded</th>
    </tr>
    <c:forEach items="${requestScope.meals}" var="meal">
        <tr style="color:${meal.exceed ? 'red' : 'green'}">
            <td>${meal.description}</td>
            <td>${meal.dateTime.format(requestScope.dateFormat)}</td>
            <td>${meal.calories}</td>
            <td>${meal.exceed}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>