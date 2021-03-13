package gama.bankline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gama.bankline.model.Conta;
import gama.bankline.model.Usuario;
import gama.bankline.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService service;
	@PostMapping
	public void post(@RequestBody Usuario entity ) {
		service.salvar(entity);
	}
	
	@GetMapping()
	public List<Usuario> usuarios() {
		return service.usuarios();
	}
	@GetMapping("/contas")
	public List<Conta> usuarios(@RequestParam("usuarioId") Integer id) {
		return service.contas(id);
	}
}
