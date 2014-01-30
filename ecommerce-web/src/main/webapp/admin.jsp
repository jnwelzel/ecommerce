<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:body>
        <div class="container">
        	<div class="row">
				<div class="col-xs-12">
					<h1>Página administrativa</h1>
					<hr/>
				</div>
        	</div>
        	
        	<div class="row">
				<div class="col-xs-12">
					<form class="form-horizontal" role="form" action="admin" method="post">
						<div class="form-group">
							<label for="inputDespesasTotais" class="col-sm-2 control-label">Despesas totais (R$)</label>
							<div class="col-sm-2">
								<input name="despesasTotais" value="${despesasTotais}" type="text" class="form-control" id="inputDespesasTotais" placeholder="0,00" autofocus="true">
							</div>
						</div>
						<div class="form-group">
							<label for="inputLucro" class="col-sm-2 control-label">Lucro (%)</label>
							<div class="col-sm-2">
								<input name="lucro" value="${lucro}" type="text" class="form-control" id="inputLucro" placeholder="0,00">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
						    	<button type="submit" class="btn btn-primary">Salvar</button>
							</div>
						</div>
					</form>
				</div>
        	</div>
        </div>
    </jsp:body>
</t:genericpage>