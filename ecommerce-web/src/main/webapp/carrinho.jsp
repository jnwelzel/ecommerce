<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:genericpage>
	<jsp:body>
        <div class="container">
        	<div class="row">
				<div class="col-xs-12">
					<h1>Carrinho de compras</h1>
					<hr/>
				</div>
        	</div>
        	
        	<div class="row">
				<div class="col-xs-12">
					<c:if test="${produtos == null}">
						<div class="alert alert-warning"><strong>Atenção!</strong> Você não possui nenhum produto no carrinho.</div>
					</c:if>
					<c:if test="${produtos != null}">
						<form action="carrinho" method="post">
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Produto</th>
									<th>Quantidade</th>
									<th>Preço</th>
									<th>Total</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="produto" items="${produtos}" varStatus="i">
								<tr>
									<td>${produto.marca} ${produto.nome }</td>
									<td><input type="number" value="${produto.quantidadeAtual}" name="${produto.codigo}" /></td>
									<td>${produto.valorTexto}</td>
									<th>${produto.valorTotalTexto}</th>
								</tr>
								</c:forEach>
								<tr>
									<td colspan="3"></td>
									<td colspan="1">${total}</td>
								</tr>
							</tbody>
						</table>
						<div class="form-group">
							<div class="pull-right">
						    	<button type="submit" class="btn btn-primary">Atualizar</button>
							</div>
						</div>
						</form>
					</c:if>
				</div>
        	</div>
        </div>
    </jsp:body>
</t:genericpage>