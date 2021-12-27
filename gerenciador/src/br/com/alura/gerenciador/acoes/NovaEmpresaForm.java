package br.com.alura.gerenciador.acoes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovaEmpresaForm  implements Acao{

	public String executa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return "forward:formNovaEmpresa.jsp";
	}
}
