package team1.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Questo campo è obbligatorio")
	private LocalDate date;

	@NotNull(message = "Questo campo è obbligatorio")
	private Integer hour;

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
	private String phone;

	@ManyToOne
	private Agent agent;

	@ManyToOne
	private Estate estate;

	private String status;

	// Getter and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Estate getEstate() {
		return estate;
	}

	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStatusValue(String status) {
		Integer res = 0;

		switch (status) {
		case "Effettuato":
			res = 2;
			break;
		case "Annullato":
			res = 1;
			break;
		case "Da effettuare":
			res = 0;
			break;
		}

		return res;
	}
}
