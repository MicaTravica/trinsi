package com.app.trinsi.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name can not be empty string")
	private String name;
	
	@NotBlank(message = "Surname can not be empty string")
	private String surname;
	
	@Email(message="Email should be valid")
	@Column(unique=true, length=100)
	private String email;
	
	private boolean verified;
	
	@NotBlank(message = "Username can not be empty string")
	@Column(unique=true, length=100)
	private String username;
	
	@Size(min = 8, message = "Password must be at least {value}")
	private String password;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_health_id", referencedColumnName = "id")
	private UserHealth userHealth;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_planner_id", referencedColumnName = "id")
	private UserPlanner userPlanner;

	public void update(User user) {
		this.username = user.getUsername();
		this.name = user.getName();
		this.surname = user.getSurname();
	}

	public void registration() {
		this.userRole = UserRole.ROLE_REGULAR;
		this.verified = false;
	}
	
	public User(Long userId) {
		this.id = userId;
	}
}
