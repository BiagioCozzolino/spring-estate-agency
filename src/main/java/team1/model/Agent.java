package team1.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author biagi
 *
 */
@Entity
public class Agent {

	private static final Random ran = new Random();
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
	@Column(nullable = false, unique = true)
	private String email;

	@NotEmpty(message = "Questo campo è obbligatorio")
	@Column(nullable = false, unique = true)
	private String phone;

	@NotEmpty(message = "Questo campo è obbligatorio")
	@Column(nullable = false)
	private String password;

	// Matricola agente

	@Column(unique = true)
	private Integer serialNumber = ran.nextInt(10000);

	// Data di assunzione agente
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hiringDate;

	// Livello di sicurezza per autenticazione

	private Integer securityLevel;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	private boolean hired = true;

	// Collegamento con le Immagini
	@OneToMany(mappedBy = "agent")
	private List<AgentImage> agentImage;

	// Collegamento con gli Immobili
	@OneToMany(mappedBy = "agent")
	private List<Estate> estate;

	// Collegamento con gli appuntamenti
	@OneToMany(mappedBy = "agent")
	private List<Appointment> appointment;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Integer getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(Integer securityLevel) {
		this.securityLevel = securityLevel;
	}

	public List<AgentImage> getAgentImage() {
		return agentImage;
	}

	public void setAgentImage(List<AgentImage> agentImage) {
		this.agentImage = agentImage;
	}

	public List<Estate> getEstate() {
		return estate;
	}

	public void setEstate(List<Estate> estate) {
		this.estate = estate;
	}

	public boolean isHired() {
		return hired;
	}

	public void setHired(boolean hired) {
		this.hired = hired;
	}

	public Integer getCountSold() {
		Integer res = 0;
		for (Estate e : estate) {
			if (e.getStatusValue(e.getStatus()) == 2) {
				res++;
			}
		}
		return res;
	}

	public Integer getCountRented() {
		Integer res = 0;
		for (Estate e : estate) {
			if (e.getStatusValue(e.getStatus()) == 1) {
				res++;
			}
		}
		return res;
	}

	public Integer getCountSoldAndRented() {
		return getCountRented() + getCountSold();
	}

	public Integer getCountDoneAppointments() {
		Integer res = 0;
		for (Appointment a : appointment) {
			if (a.getStatusValue(a.getStatus()) == 2)
				res++;
		}
		return res;
	}
}
