package gama.bankline.dto;

public class MensagemDto {
	private String conteudo;
	
	public MensagemDto() {
		
	}
	
	public MensagemDto(String conteudo) {
		super();
		this.conteudo = conteudo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
}
