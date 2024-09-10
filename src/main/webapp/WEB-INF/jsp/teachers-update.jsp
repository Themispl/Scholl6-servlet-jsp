<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Εισαγωγή Καθηγητών</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-register.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<%@ include file="header.jsp"%>
<div class="main-content">

    <h2>Update Teacher</h2>
    <div class="form m-bottom">

        <form action="${pageContext.request.contextPath}/teachers/update" method="post">
            <input type="hidden" name="id" value="${teacherUpdateDTO.id}">

            <label for="firstname">First Name:</label>
            <input type="text" id="firstname" name="firstname" value="${teacherUpdateDTO.firstname}">
            <c:if test="${!empty firstnameMessage}">
                <span style="color: red;">${firstnameMessage}</span>
            </c:if>
            <br><br>

            <label for="lastname">Last Name:</label>
            <input type="text" id="lastname" name="lastname" value="${teacherUpdateDTO.lastname}">
            <c:if test="${!empty lastnameMessage}">
                <span style="color: red;">${lastnameMessage}</span>
            </c:if>
            <br><br>

            <input type="submit" value="Update Teacher">
        </form>

        <div class="m-bottom">
        <a href="${pageContext.request.contextPath}/teachers">Επιστροφή</a>
    </div>

    <div>
<%--    <c:if test="${requestScope.deleteAPIError}">--%>
        <p>${requestScope.message}</p>
<%--    </c:if>--%>
</div>

<div>
    <c:if test="${requestScope.updateAPIError}">
        <p>Something went wrong in Update</p>
    </c:if>
</div>
</div>
<%@ include file="footer.jsp"%>

<script src="${pageContext.request.contextPath}/js/teachers.js">

</script>
</body>
</html>
