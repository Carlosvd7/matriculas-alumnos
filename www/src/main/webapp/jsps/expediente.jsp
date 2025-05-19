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
        <c:set var="formAction" value="${mvc.uri('addExpediente')}"/>
    </c:when>
    <c:when test="${action eq 'UPDATE'}">
        <c:set var="readonly" value=""/>
        <c:set var="labelSubmit" value="Actualizar"/>
        <c:set var="formMethod" value="PUT"/>
        <c:set var="formAction" value="${mvc.uri('updateExpedienteCod',{'cod':expediente.cod()})}"/>
    </c:when>
    <c:when test="${action eq 'DELETE'}">
        <c:set var="readonly" value="readonly"/>
        <c:set var="labelSubmit" value="Borrar"/>
        <c:set var="formMethod" value="DELETE"/>
        <c:set var="formAction" value="${mvc.uri('deleteExpediente',{'cod':expediente.cod()})}"/>
    </c:when>
</c:choose>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Expediente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f9f9f9;
        }
        .container {
            width: 100%;
            max-width: 600px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
            border-radius: 5px;
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
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-text {
            color: #d9534f;
            font-size: 0.875em;
        }
        .btn {
            display: inline-block;
            padding: 10px 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            text-decoration: none;
            text-align: center;
            cursor: pointer;
        }
        .btn-submit {
            background-color: #28a745;
            color: #fff;
        }
    </style>
</head>
<body>

<div class="container">
    <form method="POST" action="${formAction}" enctype="application/x-www-form-urlencoded">
        <input name="_method" type="hidden" value="${formMethod}"/>

        <div class="form-group">
            <label class="form-label" for="dni">DNI:</label>
            <input class="form-control" id="dni" name="dni" type="text" minlength="8" maxlength="10" value="${expediente.dni()}" placeholder="Introduce el DNI del expediente" aria-errormessage="errorDni" ${readonly} required/>
            <div class="form-text" id="errorDni">${errores.nombre}</div>
        </div>

        <div class="form-group">
            <label class="form-label" for="notaMedia">Nota Media:</label>
            <input class="form-control" id="notaMedia" name="notaMedia" type="number" min="0" max="10" value="${expediente.notaMedia()}" placeholder="Introduce la nota media" aria-errormessage="errorNotaMedia" ${readonly} required/>
            <div class="form-text" id="errorNotaMedia">${errores.nombre}</div>
        </div>

        <div class="form-group">
            <label class="form-label" for="titulacion">Titulación:</label>
            <input class="form-control" id="titulacion" name="titulacion" type="text" minlength="2" maxlength="40" value="${expediente.titulacion()}" placeholder="Introduce la titulación" aria-errormessage="errorTitulacion" ${readonly} required/>
            <div class="form-text" id="errorTitulacion">${errores.nombre}</div>
        </div>

        <div class="form-group">
            <label class="form-label" for="credSup">Créditos Superados:</label>
            <input class="form-control" id="credSup" name="credSup" type="number" min="0" max="240" value="${expediente.credSup()}" placeholder="Introduce los créditos superados" aria-errormessage="errorCredSup" ${readonly} required/>
            <div class="form-text" id="errorCredSup">${errores.nombre}</div>
        </div>

        <c:if test="${action != 'SELECT'}">
            <button class="btn btn-submit" type="submit">${labelSubmit}</button>
        </c:if>

        <c:if test="${action == 'SELECT'}">
            <c:set var="uriUpdateExpediente" value="${mvc.uri('formUpdateExpediente',{'cod':expediente.cod()})}"/>
            <a href="${uriUpdateExpediente}" class="btn">Actualizar Expediente</a>

            <c:set var="uriDeleteExpediente" value="${mvc.uri('formDeleteExpediente',{'cod':expediente.cod()})}"/>
            <a href="${uriDeleteExpediente}" class="btn">Borrar Expediente</a>
        </c:if>

    </form>
</div>
</body>
</html>
