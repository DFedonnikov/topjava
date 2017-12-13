<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="meal.create" var="mealCreate"/>
<spring:message code="meal.edit" var="mealEdit"/>
<spring:message code="meal.date" var="mealDate"/>
<spring:message code="meal.description" var="mealDecription"/>
<spring:message code="meal.calories" var="mealCalories"/>
<spring:message code="meal.save" var="mealSave"/>
<spring:message code="meal.cancel" var="mealCancel"/>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<section>
    <h3><jsp:include page="fragments/bodyHeader.jsp"/></h3>
    <h2><spring:eval expression="param.action == 'create' ? mealCreate : mealEdit"/></h2>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>${mealDate}</dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
            <dt>${mealDecription}:</dt>
            <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>
        </dl>
        <dl>
            <dt>${mealCalories}:</dt>
            <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
        </dl>
        <button type="submit">${mealSave}</button>
        <button onclick="window.history.back()" type="button">${mealCancel}</button>
    </form>
</section>
</body>
</html>
