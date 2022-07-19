<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de Doações</title>
<link rel="icon" href="img/gota.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Doadores cadastrados</h1>
	<a href="novo.html" class="Botao1">Novo doador</a>
	<a href="report" class="Botao2">Relatório</a>

	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Tipo sanguíneo</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getIdcon()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getTipo()%></td>
				<td><%=lista.get(i).getFone()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td><a href="select?idcon=<%=lista.get(i).getIdcon()%>"
					class="Botao1">Editar</a> <a
					href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)"
					class="Botao2">Excluir</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<img src="img/doando.png" class="alinharimg">

	<a href="index.html" class="Botao1">Home</a>

	<footer>
		<p class="footer">Matheus Rodrigues Vicente - 2022</p>
	</footer>



	<script src="scripts/confirmador.js"></script>

</body>
</html>
