package com.app.trinsi.model;

import javax.persistence.*;

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

	private String name;

	private String surname;

	@Column(unique=true, length=100)
	private String email;
	
	private boolean verified;

	@Column(unique=true, length=100)
	private String username;

	private String password;

	@Enumerated(EnumType.STRING)
	private USER_ROLE userRole;

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
		this.userRole = USER_ROLE.ROLE_REGULAR;
		this.verified = false;
	}
	
	public User(Long userId) {
		this.id = userId;
	}
}
