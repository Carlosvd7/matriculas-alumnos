<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${action eq 'SELECT'}">
        <c:set var="readonly" value="readonly"/>
        <c:set var="formMethod" value="GET"/>
    </c:when>
    <c:when test="${action eq 'INSERT'}">
        <c:set var="readonly" value=""/>
        <c:set var="formMethod" value="POST"/>
        <c:set var="labelSubmit" value="Añadir"/>
        <c:set var="formAction" value="${mvc.uri('addAlumno')}"/>
    </c:when>
    <c:when test="${action eq 'UPDATE'}">
        <c:set var="readonly" value=""/>
        <c:set var="formMethod" value="PUT"/>
        <c:set var="labelSubmit" value="Actualizar"/>
        <c:set var="formAction" value="${mvc.uri('updateAlumno',{'dni':alumno.dni()})}"/>
    </c:when>
    <c:when test="${action eq 'DELETE'}">
        <c:set var="readonly" value="readonly"/>
        <c:set var="formMethod" value="DELETE"/>
        <c:set var="labelSubmit" value="Borrar"/>
        <c:set var="formAction" value="${mvc.uri('deleteAlumno',{'dni':alumno.dni()})}"/>
    </c:when>
</c:choose>

<html>
<head>
    <title>Alumno</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 50%;
            margin: auto;
            background-color: #ffffff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-control {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ced4da;
            border-radius: 4px;
        }
        .form-control[readonly] {
            background-color: #e9ecef;
        }
        .form-text {
            margin-top: 5px;
            font-size: 0.875em;
            color: #dc3545;
        }
        .btn {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            font-size: 1em;
            cursor: pointer;
            text-align: center;
        }
        .btn:hover {
            background-color: #28a745;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Formulario de Alumno</h1>
    <form method="POST" action="${formAction}" enctype="application/x-www-form-urlencoded">
        <input name="_method" type="hidden" value="${formMethod}"/>
        <div class="form-group">
            <label class="form-label" for="dni">DNI:</label>
            <input class="form-control" id="dni" name="dni" type="text" minlength="2" maxlength="10" value="${alumno.dni()}" placeholder="Introduce el DNI del alumno" aria-errormessage="errorDni" ${readonly} required/>
            <div class="form-text" id="errorDni"></div>
        </div>
        <div class="form-group">
            <label class="form-label" for="nombre">Nombre:</label>
            <input class="form-control" id="nombre" name="nombre" type="text" minlength="2" maxlength="40" value="${alumno.nombre()}" placeholder="Introduce el nombre del alumno" aria-errormessage="errorNombre" ${readonly} required/>
            <div class="form-text" id="errorNombre"></div>
        </div>
        <div class="form-group">
            <label class="form-label" for="edad">Edad:</label>
            <input class="form-control" id="edad" name="edad" type="number" min="0" value="${alumno.edad()}" placeholder="Introduce la edad del alumno" aria-errormessage="errorEdad" ${readonly} required/>
            <div class="form-text" id="errorEdad"></div>
        </div>
        <div class="form-group">
            <label class="form-label" for="email">Email:</label>
            <input class="form-control" id="email" name="email" type="text" minlength="2" maxlength="40" value="${alumno.email()}" placeholder="Introduce el email del alumno" aria-errormessage="errorEmail" ${readonly} required/>
            <div class="form-text" id="errorEmail"></div>
        </div>
        <c:if test="${action != 'SELECT'}">
            <button type="submit" class="btn">${labelSubmit}</button>
        </c:if>
    </form>
</div>
</body>
</html>
