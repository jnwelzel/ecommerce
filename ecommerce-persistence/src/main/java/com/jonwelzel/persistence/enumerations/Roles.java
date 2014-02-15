package com.jonwelzel.persistence.enumerations;

import com.jonwelzel.persistence.Constants;

/**
 * Enumeration of all the authentication roles used in the application.
 * 
 * @author jwelzel
 * 
 */
public enum Roles {

	ADMIN(Constants.ADMIN), USER(Constants.USER);

	private String descricao;

	private Roles(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
