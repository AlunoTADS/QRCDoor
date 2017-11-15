package br.ufpr.qrcdoor.entity;

public enum RoleEnum {
	
	ADMINISTRADOR("ROLE_ADMINISTRADOR"), USUARIO("ROLE_USUARIO"), ESTRUTURA("ROLE_ESTRUTURA");
	
	private String value;
	
	private RoleEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
