package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.alura.gerenciador.acoes.Acao;

/**
 * Servlet Filter implementation class AutorizacaoFilter
 */
@WebFilter("/UnicaEntrada")
public class ControladorFilter extends HttpFilter implements Filter {
       
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	@Override
	public void destroy() {
	
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		System.out.println("Entrando no controlador");

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
		
		//programação procedural para verificar a ação (não recomendado)
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
