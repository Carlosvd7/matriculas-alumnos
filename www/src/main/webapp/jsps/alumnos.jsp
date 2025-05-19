<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alumnos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 80%;
            margin: auto;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #dddddd;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        .btn {
            display: inline-block;
            padding: 10px 15px;
            background-color: #28a745;
            color: #ffffff;
            border-radius: 5px;
            text-align: center;
            text-decoration: none;
            margin: 5px 0;
        }
        .btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Lista de Alumnos</h1>

    <c:set var="uriAddAlumno" value="${mvc.uri('formAddAlumno')}"/>
    <a href="${uriAddAlumno}" class="btn">Nuevo Alumno</a>

    <c:set var="uriAddExpediente" value="${mvc.uri('formAddExpediente')}"/>
    <a href="${uriAddExpediente}" class="btn">Nuevo Expediente</a>

    <table>
        <thead>
        <tr>
            <th>Dni</th>
            <th>Nombre</th>
            <th>Edad</th>
            <th>Email</th>
            <th>Actualizar</th>
            <th>Borrar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="alumno" items="${alumnos}">
            <c:set var="uriGetAlumno" value="${mvc.uri('getAlumnoByDni',{'dni':alumno.dni()})}"/>
            <c:set var="uriUpdateAlumno" value="${mvc.uri('formUpdateAlumno',{'dni':alumno.dni()})}"/>
            <c:set var="uriBorrarAlumno" value="${mvc.uri('formBorrarAlumno',{'dni':alumno.dni()})}"/>
            <c:set var="uriGetExpediente" value="${mvc.uri('getExpedienteByDni',{'dni':alumno.dni()})}"/>
            <tr>
                <td> <a href="${uriGetExpediente}"> ${alumno.dni()} </a></td>
                <td> <a href="${uriGetAlumno}"> ${alumno.nombre()} </a></td>
                <td>${alumno.edad()}</td>
                <td>${alumno.email()}</td>
                <td><a href="${uriUpdateAlumno}" class="btn">Actualizar</a></td>
                <td><a href="${uriBorrarAlumno}" class="btn">Borrar</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
