package co.edu.unbosque.taller2.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unbosque.taller2.entity.Pais;
import co.edu.unbosque.taller2.repository.PaisRepository;

@Service

public class PaisService {

	@Autowired
	private PaisRepository paisRepo;

	public PaisService() {
		
	}

	
	private boolean textoValido(String texto, int max) {
		return texto != null && !texto.trim().isEmpty() && texto.length() <= max;
	}

	private int validar(Pais pais) {
		if (pais == null) {
			return 2;
		}
		if (!textoValido(pais.getNombre(), 50)) {
			return 2;
		}
		if (!textoValido(pais.getCapital(), 50)) {
			return 2;
		}
		if (!textoValido(pais.getMoneda(), 30)) {
			return 2;
		}
		if (!textoValido(pais.getIdiomas(), 100)) {
			return 2;
		}
		if (pais.getHabitantes() <= 0) {
			return 2;
		}
		return 0;
	}

	
	
	public long contar() {
		return paisRepo.count();
	}

	public boolean existe(long id) {
		return paisRepo.existsById(id);
	}

	public List<Pais> mostrarTodo() {
		return (List<Pais>) paisRepo.findAll();
	}

	public Optional<Pais> buscarPorId(long id) {
		return paisRepo.findById(id);
	}

	public Optional<Pais> buscarPorNombre(String nombre) {
		return paisRepo.findByNombre(nombre);
	}

	public List<Pais> buscarPorCapital(String capital) {
		return paisRepo.findByCapital(capital);
	}

	public List<Pais> buscarPorMoneda(String moneda) {
		return paisRepo.findByMoneda(moneda);
	}

	public List<Pais> buscarPorIdioma(String idioma) {
		return paisRepo.findByIdiomasContaining(idioma);
	}

	public List<Pais> buscarPorHabitantes(long habitantes) {
		return paisRepo.findByHabitantes(habitantes);
	}

	
	public int crear(Pais NuevoPais) {
		int validacion = validar(NuevoPais);
		if (validacion != 0) {
			return validacion;
		}
		if (paisRepo.existsByNombre(NuevoPais.getNombre())) {
			return 3;
		}
		try {
			NuevoPais.setId(0);
			paisRepo.save(NuevoPais);
			return 0;
		} catch (DataIntegrityViolationException e) {
			return 3;
		} catch (Exception e) {
			return 4;
		}
	}

	
	public int eliminarPorId(long id) {

		Optional<Pais> encontrado = paisRepo.findById(id);

		if (encontrado.isPresent()) {
			paisRepo.deleteById(id);
			return 0;
		} else {
			return 1;
		}
	}

	@Transactional
	public int eliminarPorNombre(String nombre) {
		return paisRepo.deleteByNombre(nombre) > 0 ? 0 : 1;
	}

	@Transactional
	public int eliminarPorCapital(String capital) {
		return paisRepo.deleteByCapital(capital) > 0 ? 0 : 1;
	}

	@Transactional
	public int eliminarPorMoneda(String moneda) {
		return paisRepo.deleteByMoneda(moneda) > 0 ? 0 : 1;
	}

	@Transactional
	public int eliminarPorIdiomas(String idiomas) {
		return paisRepo.deleteByIdiomas(idiomas) > 0 ? 0 : 1;
	}

	@Transactional
	public int eliminarPorHabitantes(long habitantes) {
		return paisRepo.deleteByHabitantes(habitantes) > 0 ? 0 : 1;
	}

	// Actulaliza objeto
	public int actualizarPorId(long id, Pais NuevoDato) {

		int validacion = validar(NuevoDato);
		if (validacion != 0) {
			return validacion;
		}

		Optional<Pais> encontrado = paisRepo.findById(id);
		Optional<Pais> conMismoNombre = paisRepo.findByNombre(NuevoDato.getNombre());

		if (conMismoNombre.isPresent() && conMismoNombre.get().getId() != id) {
			return 3;
		}

		try {
			if (encontrado.isPresent()) {

				Pais temp = encontrado.get();

				temp.setNombre(NuevoDato.getNombre());
				temp.setCapital(NuevoDato.getCapital());
				temp.setMoneda(NuevoDato.getMoneda());
				temp.setIdiomas(NuevoDato.getIdiomas());
				temp.setHabitantes(NuevoDato.getHabitantes());

				paisRepo.save(temp);

				return 0;

			} else {

				NuevoDato.setId(0);
				paisRepo.save(NuevoDato);

				return 1;
			}
		} catch (DataIntegrityViolationException e) {
			return 3;
		} catch (Exception e) {
			return 4;
		}
	}

	// Actualiza atributo
	public int actualizarNombre(long id, String nombre) {

		Optional<Pais> encontrado = paisRepo.findById(id);

		if (encontrado.isEmpty()) {
			return 1;
		}
		if (!textoValido(nombre, 50)) {
			return 2;
		}
		Optional<Pais> otro = paisRepo.findByNombre(nombre);
		if (otro.isPresent() && otro.get().getId() != id) {
			return 3;
		}
		try {
			Pais temp = encontrado.get();
			temp.setNombre(nombre);
			paisRepo.save(temp);
			return 0;
		} catch (DataIntegrityViolationException e) {
			return 3;
		} catch (Exception e) {
			return 4;
		}
	}

	public int actualizarCapital(long id, String capital) {

		Optional<Pais> encontrado = paisRepo.findById(id);

		if (encontrado.isEmpty()) {
			return 1;
		}
		if (!textoValido(capital, 50)) {
			return 2;
		}
		try {
			Pais temp = encontrado.get();
			temp.setCapital(capital);
			paisRepo.save(temp);
			return 0;
		} catch (Exception e) {
			return 4;
		}
	}

	public int actualizarMoneda(long id, String moneda) {

		Optional<Pais> encontrado = paisRepo.findById(id);

		if (encontrado.isEmpty()) {
			return 1;
		}
		if (!textoValido(moneda, 30)) {
			return 2;
		}
		try {
			Pais temp = encontrado.get();
			temp.setMoneda(moneda);
			paisRepo.save(temp);
			return 0;
		} catch (Exception e) {
			return 4;
		}
	}

	public int actualizarIdiomas(long id, String idiomas) {

		Optional<Pais> encontrado = paisRepo.findById(id);

		if (encontrado.isEmpty()) {
			return 1;
		}
		if (!textoValido(idiomas, 100)) {
			return 2;
		}
		try {
			Pais temp = encontrado.get();
			temp.setIdiomas(idiomas);
			paisRepo.save(temp);
			return 0;
		} catch (Exception e) {
			return 4;
		}
	}

	public int actualizarHabitantes(long id, long habitantes) {

		Optional<Pais> encontrado = paisRepo.findById(id);

		if (encontrado.isEmpty()) {
			return 1;
		}
		if (habitantes <= 0) {
			return 2;
		}
		try {
			Pais temp = encontrado.get();
			temp.setHabitantes(habitantes);
			paisRepo.save(temp);
			return 0;
		} catch (Exception e) {
			return 4;
		}
	}
}