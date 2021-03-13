package gama.bankline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gama.bankline.dto.MensagemDto;
import gama.bankline.model.Mensagem;
import gama.bankline.repository.MensagemRepository;
import gama.bankline.repository.MyRepository;
import gama.bankline.service.Mensageria;

@RestController
@RequestMapping("/mensagens")
public class MensageriaController {
	@Autowired
	private MensagemRepository repository;
	
	@Autowired
	private Mensageria mensageria;
	
	@Autowired
	private MyRepository myRepo;
	
	@GetMapping
	public List<Mensagem> mensagens(){
		return repository.findAll(); 
	}
	
	@GetMapping("/dtos")
	public List<MensagemDto> dtos(){
		return repository.listDtos();
	}
	
	@GetMapping("/filter/{titulo}")
	public List<Mensagem> listPorTitulos(@PathVariable ("titulo") String titulo){
		//return myRepo.listByTitulo(titulo);
		return repository.findByTitulo(titulo);
	}
	@PostMapping
	public void enviar(@RequestBody Mensagem mensagem) {
		mensageria.enviar(mensagem);
	}
	
}
