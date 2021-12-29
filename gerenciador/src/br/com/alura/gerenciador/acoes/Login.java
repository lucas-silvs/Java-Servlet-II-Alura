package br.com.alura.gerenciador.acoes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Usuario;

public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		System.out.println("Logando "+login);
		Banco banco = new Banco();
		Usuario usuario = null;
		usuario = banco.buscaUsuarioPorLogin(login, senha);
		if(usuario == null) {
			System.out.println("usuario não existe");
		}
		else {
			System.out.println("usuario logado com sucesso");
			HttpSession sessao = request.getSession();
			
			sessao.setAttribute("usuarioLogado",usuario);
			return "redirect:UnicaEntrada?acao=ListaEmpresa";
		}
		
		
		return "redirect:UnicaEntrada?acao=LoginForm";
		
	}

}
