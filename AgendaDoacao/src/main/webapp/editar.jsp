<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de Doações</title>
<link rel="icon" href="img/gota.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar contato</h1>
	<form name="frmContato" action="update">
		<table align="center">
			<tr>
				<td><input type="text" name="idcon" id="caixa3" readonly
					value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="nome" class="Caixa1"
					value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="tipo" class="Caixa1"
					value="<%out.print(request.getAttribute("tipo"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="fone" class="Caixa2"
					value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="email" class="Caixa1"
					value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1 alinharbotao"
			onclick="validar()">
	</form>

	<a href="index.html" class="Botao1">Home</a>
	
	<footer>
		<p class="footer">Matheus Rodrigues Vicente - 2022</p>
	</footer>
	
	<script src="scripts/validador.js"></script>
</body>
</html>