<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Katalog towarów</title>
<link rel="stylesheet" type="text/css" href="/styl.css"/>
</head>
<body>

<c:if test="${not empty basket}">
<div class="basket">
<h4>Koszyk</h4>
<ul>
<c:forEach var="p" items="${basket.products}">
	<li>${p.name}: ${p.count} × ${p.price} = <b>${p.value}</b></li>
</c:forEach>
</ul>
<p>Wartość koszyka: ${basket.basketValue}</p>
</div>
</c:if>

<h1>Wszystkie produkty</h1>

<c:forEach var="product" items="${products}">
<div class="product">
<p>Towar <a href="/products/${product.productId}" class="product-name">${product.name}</a></p>
<p>Cena: <span class="product-price">${product.price}</span></p>
<p class="product-description">${product.description}</p>
<div class="action"><a href="/products/${product.productId}/edit">Edytuj</a></div>
<div class="action"><a href="/products/${product.productId}/add-to-basket">Dodaj do koszyka</a></div>
</div>
</c:forEach>

<div><a href="/add_new_product">Dodaj nowy produkt</a></div>
</body>
</html>
