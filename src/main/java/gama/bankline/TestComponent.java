package gama.bankline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import gama.bankline.model.Usuario;
import gama.bankline.service.UsuarioService;

@Component
public class TestComponent {
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void test() {
		Usuario user = new Usuario();
		user.setLogin("gso");
		user.setPassword(encoder.encode("gso123"));
		service.salvar(user);

		System.out.println("FUNCIONADO");
	}
}
