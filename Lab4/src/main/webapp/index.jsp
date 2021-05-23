<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Welcome to my movie shop!</h2>

<c:forEach var="genre" items="${requestScope.genres}">
    <ul>
        <li>Genre: <c:out value="${genre.name}"/></li>
<%--        <c:forEach var="movie" items="${requestScope.movies}">--%>
<%--            <c:if test="${genre.id}==${movie.genre.id}">--%>
<%--                <ul>--%>
<%--                    <li>Movie: <c:out value="${movie.name}"/></li>--%>
<%--                    <li>Director: <c:out value="${movie.director}"/></li>--%>
<%--                    <li>Producer: <c:out value="${movie.producer}"/></li>--%>
<%--                    <li>Screenwriter: <c:out value="${movie.screenwriter}"/></li>--%>
<%--                    <li>Company: <c:out value="${movie.company}"/></li>--%>
<%--                    <li>Duration: <c:out value="${movie.duration}"/> minutes</li>--%>
<%--                    <li>Country: <c:out value="${movie.country}"/></li>--%>
<%--                    <li>Year: <c:out value="${movie.year}"/></li>--%>
<%--                </ul>--%>
<%--            </c:if>--%>
<%--        </c:forEach>--%>
    </ul>
    <hr/>
</c:forEach>
</body>
</html>
