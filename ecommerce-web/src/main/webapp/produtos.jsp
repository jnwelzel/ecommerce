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
          		<c:forEach var="produto" items="${produtos}">
          			<div class="col-xs-4 produto">
          				<img class="img-circle" style="width: 150px; height: 150px;" data-src="holder.js/150x150" alt="150x150" src="${produto.arquivoFoto}">
			          	<h2>${produto.marca} ${produto.nome}</h2>
			          	<p>${produto.descricao}</p>
			          	<button type="button" class="btn btn-primary">Adicionar ao carrinho</button>
			        </div>
          		</c:forEach>
          	</div>
        </div>
    </jsp:body>
</t:genericpage>