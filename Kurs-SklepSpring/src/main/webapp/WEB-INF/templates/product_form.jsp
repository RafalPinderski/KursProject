<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edycja danych towaru</title>
<link rel="stylesheet" type="text/css" href="/styl.css"/>
</head>
<body>
<h1>Edycja danych towaru</h1>

<f:form id="product-form" method="post" modelAttribute="product">
	<table class="form-tab">
		<tr>
			<td><f:label path="productId">Numer:</f:label></td>
			<td><f:input path="productId" placeholder="brak" type="number" readonly="true"/></td>
		</tr>
		<tr>
			<td><f:label path="name">Nazwa towaru:</f:label></td>
			<td><f:input path="name" placeholder="nazwa..." type="text"/>
				<f:errors path="name" cssClass="form-error" element="div"/>
			</td>
		</tr>
		<tr>
			<td><f:label path="price">Cena:</f:label></td>
			<td><f:input path="price" placeholder="12.90" type="text"/>
				<f:errors path="price" cssClass="form-error" element="div"/>
			</td>
		</tr>
		<tr>
			<td><f:label path="description">Opis:</f:label></td>
			<td><f:textarea path="description" rows="10" cols="120"/></td>
		</tr>
		<tr>
			<td><f:button>Wyślij</f:button></td>
		</tr>
	</table>
</f:form>

<c:if test="${saved}">
	<div class="info">Produkt został zapisany pod numerem <a href="/products/${product.productId}">${product.productId}</a>.</div>
</c:if>

<c:if test="${not empty errors}">
	<ul class="error">
		<c:forEach var="error" items="${errors}">
			<%-- <li>${error}</li> --%>
			<li>${error.defaultMessage}</li>
		</c:forEach>
	</ul>
</c:if>

</body>
</html>

