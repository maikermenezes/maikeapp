package gama.bankline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gama.bankline.model.Usuario;
import gama.bankline.repository.UsuarioRepository;

@Service
public class ServiceCore {
	@Autowired
	private UsuarioRepository rep;
	
	@Transactional
	public void salvar(Usuario usuario) {
		//se já exitir na base um usuario login dar execption
		
		
		if(rep.existsByLogin(usuario.getLogin()))
			throw new IllegalArgumentException("Já existe um usuário com este login " + usuario.getLogin());
		
		
		rep.save(usuario);
	}
		
	public int somar(int um, int dois) {
		return um + dois;
	}
}
