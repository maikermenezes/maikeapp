package gama.bankline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import gama.bankline.config.FtpConfig;
import gama.bankline.model.Mensagem;
import gama.bankline.repository.MensagemRepository;

@Component
public class Mensageria {
	@Value("${noreplay}")
	private String noReplayEmail;
	
	@Autowired
	private FtpConfig ftpConfig;
	
	@Autowired
	private MensagemRepository repository;
	
	@Autowired
	private Gson gson;
	
	public void enviar(Mensagem mensagem) {
		String jsonInString = gson.toJson(mensagem);
		System.out.println(jsonInString);
		System.out.println("FTP "+ ftpConfig.getPort() + " " + ftpConfig.getHost());
		
		System.out.println("Enviado por: "+ noReplayEmail);
		System.out.println("Enviando a mensagem: " + mensagem.getTitulo() + " " + mensagem.getConteudo());
		repository.save(mensagem);
	}
}
