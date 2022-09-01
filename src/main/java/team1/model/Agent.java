package team1.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Agent {

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Questo campo è obbligatorio")
	@Column(nullable = false)
	private String name;

	@NotEmpty(message = "Questo campo è obbligatorio")
	@Column(nullable = false)
	private String surname;

	@NotEmpty(message = "Questo campo è obbligatorio")
	@Column(nullable = false)
	private String email;

	@NotEmpty(message = "Questo campo è obbligatorio")
	@Column(nullable = false)
	private String password;

	// Matricola agente
	@NotNull
	private Integer serialNumber;

	// Data di assunzione agente
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hiringDate;

	// Livello di sicurezza per autenticazione
	@NotNull
	@Min(2)
	@Max(3)
	private Integer securityLevel;

	@OneToMany(mappedBy = "agent")
	private List<AgentImage> agentImage;

	@OneToMany(mappedBy = "agent")
	private List<Estate> estate;

	// Getter and Setter

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public LocalDate getHiringDate() {
		return hiringDate;
	}

	public void setHiringDate(LocalDate hiringDate) {
		this.hiringDate = hiringDate;
	}

	public String getFormattedHiringDate() {
		return hiringDate.format(dateFormatter);
	}

	public Integer getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(Integer securityLevel) {
		this.securityLevel = securityLevel;
	}

}
