package gama.bankline.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gama.bankline.dto.MensagemDto;
import gama.bankline.exception.Erro;
import gama.bankline.exception.Erros;
import gama.bankline.model.Contato;
import gama.bankline.service.ServiceCore;

@RestController
@RequestMapping("/testes")
public class TestController {
	@Autowired
	private ServiceCore service;
	
	@GetMapping("/{nome}/{sobrenome}")
	public String pathVariable(@PathVariable("nome") String nome,@PathVariable("sobrenome")  String sobrenome) {
		return  "Path Variable -- " + nome + " " + sobrenome; 
	}
	
	@GetMapping("/")
	public String requestParam(@RequestParam("nome") String nome,@RequestParam("sobrenome")  String sobrenome) {
		return "Param: -- " + nome + " " + sobrenome; 
	}
	
	//https://www.baeldung.com/spring-date-parameters
	@GetMapping("/filter")
	public LocalDate filtrarLancamentos(@DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam("data") LocalDate data) {
		return data.plusMonths(2);
	}
	
	@GetMapping()
	public String get() {
		return "ola mundo";
	}
	
	@GetMapping("/erros")
	public List<Erros> erros() {
		List<Erros> erros = new ArrayList<Erros>();
		
		for(Erro e: Erro.values()) {
			erros.add(new Erros() {

				@Override
				public String getCodigo() {
					return e.getCodigo();
				}

				@Override
				public String getMensagem() {
					return e.getMensagem();
				}
				
			});
		}
		
		return erros;
	}
	
	@PostMapping
	public void post(@RequestBody MensagemDto dto ) {
		//throw new IllegalCustomException("O Conteudo não pode ser nulo");
		//service.save(dto);
		/*
		Mensagem m = new Mensagem();
		m.setConteudo(dto.getConteudo());
		
		//repository
		System.out.println("post");
		*/
	}
	
	//@PutMapping(path = "/{id}")
	@PatchMapping(path = "/{id}")
	public void altararContatoMensagem(@PathVariable("id") Integer id, @RequestBody Contato dto ) {
		//Mensagem m = findById(id);
		//m.setContato(dto);
		System.out.println("patch");
	}
	
}
