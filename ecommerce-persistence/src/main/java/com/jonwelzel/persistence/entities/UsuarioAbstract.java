package com.jonwelzel.persistence.entities;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jonwelzel.persistence.enumerations.Roles;

/**
 * Classe abstrata contendo dados comuns do domńio de usuários.
 * 
 * @author jwelzel
 * 
 */
@MappedSuperclass
public abstract class UsuarioAbstract extends Bean<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(nullable = false, length = 128)
	@NotNull(message = "O campo \"nome\" é obrigatório")
	@Size(min = 2, max = 128, message = "O campo \"nome\" deve ter entre 2 e 128 caracteres")
	protected String nome;

	@Column(nullable = false, length = 15)
	@Size(min = 3, max = 15, message = "A senha deve ter entre 3 e 15 caracteres")
	@NotNull(message = "O campo \"senha\" é obrigatório")
	protected String senha;

	@Column(nullable = false, length = 60)
	@Size(max = 60, message = "O email deve ter no máximo 60 caracteres")
	@NotNull(message = "O campo \"email\" é obrigatório")
	protected String email;

	@Enumerated(EnumType.STRING)
	protected Roles role;

	protected boolean active = false;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

}
