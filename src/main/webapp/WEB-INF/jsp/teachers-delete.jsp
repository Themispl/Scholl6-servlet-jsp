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

<%--    <div class="m-bottom">--%>
<%--        <a href="${pageContext.request.contextPath}/teachers/insert">Εισαγωγή Καθηγητή</a>--%>
<%--    </div>--%>

    <div class="form m-bottom">

        <form method="POST" action="">
            <div class="row m-bottom">
                <input class="m-bottom" type="text" name="firstname" value="${requestScope.teacherInsertDTO.firstname}" required placeholder="Firstname">
                <p class="validation-error">${requestScope.firstnameMessage}</p>
            </div>
            <div class="row m-bottom">
                <input class="m-bottom" type="text" name="lastname" value="${requestScope.teacherInsertDTO.lastname}" required placeholder="Lastname">
                <p class="validation-error">${requestScope.lastnameMessage}</p>
            </div>
            <div class="row">
                <button type="submit">Διαγραφή</button>
            </div>
        </form>
    </div>

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
