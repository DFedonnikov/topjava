<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="meal.title" var="mealTitle"/>
<spring:message code="meal.fromDate" var="fromDate"/>
<spring:message code="meal.toDate" var="toDate"/>
<spring:message code="meal.fromTime" var="fromTime"/>
<spring:message code="meal.toTime" var="toTime"/>
<spring:message code="meal.date" var="mealDate"/>
<spring:message code="meal.description" var="mealDecription"/>
<spring:message code="meal.calories" var="mealCalories"/>
<spring:message code="meal.filter" var="mealFilter"/>
<spring:message code="meal.add" var="mealAdd"/>
<spring:message code="meal.update" var="mealUpdate"/>
<spring:message code="meal.delete" var="mealDelete"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3>${mealTitle}</h3>
    <form method="post" action="meals?action=filter">
        <dl>
            <dt>${fromDate}</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt>${toDate}</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt>${fromTime}</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt>${toTime}</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit">${mealFilter}</button>
    </form>
    <hr/>
    <a href="meals?action=create">${mealAdd}</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">${mealUpdate}</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">${mealDelete}</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>