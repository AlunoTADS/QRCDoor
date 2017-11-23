package br.ufpr.qrcdoor.entity;

public enum TipoPessoaEnum {
	
	F("PESSOA_FISICA"), J("PESSOA_JURIDICA");
	
	private String value;
	
	private TipoPessoaEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
