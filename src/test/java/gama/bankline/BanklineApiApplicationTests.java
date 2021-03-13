package gama.bankline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import gama.bankline.model.Usuario;
import gama.bankline.repository.UsuarioRepository;
import gama.bankline.service.ServiceCore;

@SpringBootTest
@ContextConfiguration(classes = TestBeans.class)
class BanklineApiApplicationTests {
	
	@Autowired
	private ServiceCore service;
	
	@MockBean
	UsuarioRepository repository;
	
	@BeforeEach
	public void before() {
		Usuario usuario = new Usuario();
		usuario.setLogin("gso");
		Mockito.when(repository.existsByLogin("gso"))
		.thenReturn(true);
		
		/*
		usuario = new Usuario();
		usuario.setLogin("zezinho");
		Mockito.when(repository.findByLogin("zezinho"))
		.thenReturn(usuario);
		*/
	}
	
	@Test
	void contextLoads() {
		Assertions.assertEquals(10, service.somar(7, 3));
	}
	
	@Test
	public void loginDuplicadoException() {
		Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Usuario usuario = new Usuario();
			usuario.setLogin("gso");
	        service.salvar(usuario);
	    });
		
		Assertions.assertEquals("Já existe um usuário com este login gso", exception.getMessage());
	}
	
	@Test
	public void loginValido() {
		Usuario usuario = new Usuario();
		usuario.setLogin("zezinho");
        service.salvar(usuario);
		
       // Usuario dbUser = repository.findByLogin("zezinho");
       // Assertions.assertEquals(usuario.getLogin(), dbUser.getLogin());
		
	}

}
