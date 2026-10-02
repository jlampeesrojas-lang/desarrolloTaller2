package co.edu.unbosque.taller2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pais")
public class Pais {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
	@Column(unique = true, nullable = false, length = 50)
	private String nombre;
	@Column(nullable = false, length = 50)
	private String capital;
	@Column(nullable = false, length = 30)
	private String moneda;
	@Column(nullable = false, length = 100)
	private String idiomas;
	@Column(nullable = false)
	private long habitantes;

	public Pais() {
	}

	public Pais(String nombre, String capital, String moneda, String idiomas, long habitantes) {
		super();
		this.nombre = nombre;
		this.capital = capital;
		this.moneda = moneda;
		this.idiomas = idiomas;
		this.habitantes = habitantes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	public long getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(long habitantes) {
		this.habitantes = habitantes;
	}

	@Override
	public String toString() {
		return "Pais [nombre=" + nombre + ", capital=" + capital + ", moneda=" + moneda + ", idiomas=" + idiomas
				+ ", habitantes=" + habitantes + "]";
	}

}
