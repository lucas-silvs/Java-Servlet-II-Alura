package br.com.alura.cliente;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class ClienteWebService {

	public static void main(String[] args) throws ClientProtocolException, IOException {
	
		String request = Request.Post("http://localhost:8080/gerenciador/empresas") //requisição POST
		.addHeader("Accept","application/json")
		.execute(). //executa a requisição 
		returnContent() // aguarda o envio do XML ou JSON
		.asString(); //converte o resultado para string
		
		System.out.println(request);
		

	}

}
