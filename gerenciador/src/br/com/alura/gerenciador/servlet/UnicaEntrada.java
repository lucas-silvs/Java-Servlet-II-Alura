package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acoes.Acao;
import br.com.alura.gerenciador.acoes.AlteraEmpresa;
import br.com.alura.gerenciador.acoes.ListaEmpresa;
import br.com.alura.gerenciador.acoes.MostraEmpresa;
import br.com.alura.gerenciador.acoes.NovaEmpresa;
import br.com.alura.gerenciador.acoes.NovaEmpresaForm;
import br.com.alura.gerenciador.acoes.RemoveEmpresa;
import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

/**
 * Servlet implementation class UnicaEntrada
 */
@WebServlet("/UnicaEntrada")
public class UnicaEntrada extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnicaEntrada() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramAcao = request.getParameter("acao");
		String nome = null;
		String nomeClasse = "br.com.alura.gerenciador.acoes."+paramAcao;
		Class classe = null;
		Object obj = null;
		try {
			classe = Class.forName(nomeClasse);
			obj = classe.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		
			
		Acao acao = (Acao) obj;
		nome = acao.executa(request,response);
//		if(paramAcao.equals("ListaEmpresa")) {
//			
//			ListaEmpresas listaEmpresas = new ListaEmpresas();
//			nome= listaEmpresas.executa(request,response);
//			System.out.println("utilizando o unicaentrada para listar as empresas");
//		}
//			
//		else if(paramAcao.equals("RemoveEmpresa")) {
//			RemoveEmpresa removeEmpresa = new RemoveEmpresa();
//			nome = removeEmpresa.executa(request,response);
//			System.out.println("utilizando o unicaentrada para remover empresa");
//			
//		}
//			
//		else if(paramAcao.equals("MostraEmpresa")) {
//			MostraEmpresa mostraEmpresa = new MostraEmpresa();
//			nome = mostraEmpresa.executa(request, response);
//			System.out.println("utilizando o unicaentrada para mostrar empresa");
//		}
//		else if(paramAcao.equals("AlteraEmpresa")) {
//			AlteraEmpresa alteraEmpresa = new AlteraEmpresa();
//			nome = alteraEmpresa.executa(request, response);
//			System.out.println("utilizando o unicaentrada para alterar empresa");
//		}
//		else if(paramAcao.equals("NovaEmpresa")) {
//			NovaEmpresa novaEmpresa = new NovaEmpresa();
//			nome = novaEmpresa.executa(request, response);
//			System.out.println("utilizando o unicaentrada para add nova empresa");
//		}
//		
//		else if(paramAcao.equals("NovaEmpresaForm")) {
//			 NovaEmpresaForm novaEmpresaForm = new NovaEmpresaForm();
//			nome = novaEmpresaForm.executa(request, response);
//			System.out.println("utilizando o unicaentrada para ir para o form de add nova empresa");
//		}
		
		
		String[] acaoString = nome.split(":");
		if(acaoString[0].equals("forward")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/"+acaoString[1]);
			rd.forward(request, response);
		}
		else {
			response.sendRedirect(acaoString[1]);
		}
	}

}
