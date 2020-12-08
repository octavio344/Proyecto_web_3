package com.edu.iua.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7698937455154753453L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
	private String nombre;
	
	@Column(length = 100)
	private String apellido;
	
	@Column(length = 100,nullable = false)
	private String email;
	
	@Column(length = 70,nullable = false)
	private String password;
	
	@Column(length = 50,nullable = false,unique = true)
	private String username;
	
	@ManyToOne
	@JoinColumn(name = "id_rol_principal")
	private Rol rolPrincipal;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "id_user", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_rol", referencedColumnName = "id") })
	private Set<Rol> roles;
	
	@OneToMany(targetEntity=Alarma.class, mappedBy="usuario", fetch = FetchType.LAZY)
	@JsonBackReference
	@ApiModelProperty(notes = "Lista de todas las alarmas que acepto este usuario", required = false)
	private List<Alarma> alarmasList;
	
	@Column(columnDefinition = "tinyint default 1")
	private boolean accountNonExpired = true;
	
	@Column(columnDefinition = "int default 360")
	private int sessionTimeout;

	public int getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	
	@Transient
	private String sessionToken;

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Rol getRolPrincipal() {
		return rolPrincipal;
	}


	public void setRolPrincipal(Rol rolPrincipal) {
		this.rolPrincipal = rolPrincipal;
	}


	public Set<Rol> getRoles() {
		return roles;
	}


	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}


	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}


	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}


	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}


	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}


	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(columnDefinition = "tinyint default 1")
	private boolean accountNonLocked = true;

	@Column(columnDefinition = "tinyint default 1")
	private boolean credentialsNonExpired = true;

	@Column(columnDefinition = "tinyint default 1")
	private boolean enabled;

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRol()))
				.collect(Collectors.toList());

		return authorities;
	}
	
	@Transient
	public String checkAccount(PasswordEncoder passwordEncoder, String password) {
		if (!passwordEncoder.matches(password, getPassword()))
			return "BAD_PASSWORD";
		if (!isEnabled())
			return "ACCOUNT_NOT_ENABLED";
		if (!isAccountNonLocked())
			return "ACCOUNT_LOCKED";
		if (!isCredentialsNonExpired())
			return "CREDENTIALS_EXPIRED";
		if (!isAccountNonExpired())
			return "ACCOUNT_EXPIRED";

		return null;
	}
	
	@Transient
	public String getNombreCompleto() {
		return nombre+" "+apellido;
	}



	

	
	
	
}
