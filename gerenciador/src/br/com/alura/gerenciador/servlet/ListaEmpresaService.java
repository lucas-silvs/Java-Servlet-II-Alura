package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;


@WebServlet("/empresas")
public class ListaEmpresaService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<Empresa> empresas = new Banco().getEmpresas();
		String formato = request.getHeader("Accept");
		
		System.out.println(formato);
		
		if(formato.contains("json")) {
		
		
		//envio de dados web service em formato JSON utilizando a biblioeca externa Gson
		Gson gson = new Gson();
		String json = gson.toJson(empresas);
		
		response.setContentType("application/json");
		response.getWriter().print(json);
		
		}
		else if(formato.contains("xml")) {
		//Envio de dados web service em formato XML utilizando a biblioteca externa Xstream
		XStream xstream = new XStream();
		xstream.alias("empresa", Empresa.class);
		String xml = xstream.toXML(empresas);
		
		response.setContentType("application/xml");
		response.getWriter().print(xml);
		
		}
		else {
			
			
			response.setContentType("application/json");
			response.getWriter().print("não identificado o formato pedido no request");
			
		}
		
	}

}
