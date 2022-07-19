package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" ,"/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}

	// Listar Contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando objeto para receber oque irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		// Encaminhando a Lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

	}

	// Editar Contato

	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do ID do contato que será editado
		String idcon = request.getParameter("idcon");
		// Setando a variavel JavaBeans
		contato.setIdcon(idcon);

		// **
		// Executar o método selecionarContato (DAO)
		dao.selecionarContato(contato);
		// Setar os atributos do formulário com o conteudo JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("tipo", contato.getTipo());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		// Encaminhando para o documento edtar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as variaveis JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setTipo(request.getParameter("tipo"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// Executa o método alterarContato
		dao.alteraContato(contato);
		// Redireciona para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}

	// Remover um contato

	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato a ser excluido (validador.js)
		String idcon = request.getParameter("idcon");
		// setar a variável idcon JavaBeans
		contato.setIdcon(idcon);
		// Executando o método deletarContato (DAO) passando o objeto contato
		dao.deletarContato(contato);
		// Redireciona para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");

	}
	
	//Gerar relatório em PDF
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			//Tipo de conteúdo 
			response.setContentType("apllication/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			//Criando o documento PDF
			PdfWriter.getInstance(documento, response.getOutputStream());
			//Abrindo o documento -> conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de doadores cadastrados:"));
			documento.add(new Paragraph(" "));
			//Criando uma tabela
			PdfPTable tabela = new PdfPTable(4); //Numero igual a quantidade de colunas que terá no relatório
			//Cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Tipo"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			//Populando a tablela com os contatos do DB
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for (int i=0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getTipo());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);			
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

	// Novo Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setar as varáveis do JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setTipo(request.getParameter("tipo"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Invocando o método inserirContato passando o objeto contato
		dao.inserirContato(contato);

		// Redirecionar para o documento agenda.jsp

		response.sendRedirect("main");

	}

}