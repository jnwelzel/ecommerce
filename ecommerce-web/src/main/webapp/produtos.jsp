<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:genericpage>
	<jsp:body>
        <div class="container">
        	<div class="jumbotron">
        		<h1>Confira abaixo nossos produtos!</h1>
            </div>
          	<div class="row">
          		<c:forEach var="produto" items="${produtos}" varStatus="i">
          			<div class="col-xs-4 produto">
          				<img class="img-circle" style="width: 150px; height: 150px;" data-src="holder.js/150x150" alt="150x150" src="${produto.arquivoFoto}">
			          	<h2>${produto.marca} ${produto.nome}</h2>
			          	<p>${produto.descricao}</p>
			          	<h4>${produto.valorTexto}</h4>
			          	<form action="produtos" method="post">
			          		<input type="hidden" value="${produto.codigo}" name="codigo">
				          	<button type="submit" class="btn btn-primary">Adicionar ao carrinho</button>
			          	</form>
			        </div>
			        <c:if test="${(i.index + 1) % 3 == 0}"></div><div class="row"></c:if>
          		</c:forEach>
          	</div>
        </div>
    </jsp:body>
</t:genericpage>