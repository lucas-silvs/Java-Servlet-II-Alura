package br.com.alura.gerenciador.acoes;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class ListaEmpresa   implements Acao{

	
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		Banco banco = new Banco();
		List<Empresa> lista = banco.getEmpresas();
		request.setAttribute("empresas", lista);

		return "forward:listaEmpresas.jsp";
	}
}
