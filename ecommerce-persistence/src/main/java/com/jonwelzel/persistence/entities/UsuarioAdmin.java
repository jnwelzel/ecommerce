package com.jonwelzel.persistence.entities;

import javax.persistence.Entity;

import com.jonwelzel.persistence.enumerations.Roles;

/**
 * Um usuário administrador.
 * 
 * @author jwelzel
 * 
 */
@Entity
public class UsuarioAdmin extends UsuarioAbstract {

	private static final long serialVersionUID = 1L;

	public UsuarioAdmin() {
		super();
		role = Roles.ADMIN;
		active = true;
	}
}
