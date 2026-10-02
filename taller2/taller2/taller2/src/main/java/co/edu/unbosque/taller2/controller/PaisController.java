package co.edu.unbosque.taller2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.taller2.entity.Pais;
import co.edu.unbosque.taller2.service.PaisService;

@RestController
@RequestMapping("/Pais")
@CrossOrigin(origins= {"*"})
public class PaisController {
	@Autowired
	private PaisService paisService;

	private ResponseEntity<Integer> responder(int codigo, HttpStatus exito) {
		switch (codigo) {
		case 0:
			return new ResponseEntity<>(codigo, exito);
		case 1:
			return new ResponseEntity<>(codigo, HttpStatus.NOT_FOUND);
		case 2:
			return new ResponseEntity<>(codigo, HttpStatus.BAD_REQUEST);
		case 3:
			return new ResponseEntity<>(codigo, HttpStatus.CONFLICT);
		default:
			return new ResponseEntity<>(codigo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private ResponseEntity<List<Pais>> responderLista(List<Pais> encontrados) {
		if (encontrados.isEmpty()) {
			return new ResponseEntity<>(encontrados, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(encontrados, HttpStatus.ACCEPTED);
		}
	}

	private ResponseEntity<Pais> responderPais(Optional<Pais> encontrado) {
		if (encontrado.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(encontrado.get(), HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/contar")
	public ResponseEntity<Long>contar(){
		long numeroEncontrado = paisService.contar();
		if(numeroEncontrado==0L) {
			return new ResponseEntity<>(numeroEncontrado,HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(numeroEncontrado, HttpStatus.ACCEPTED);
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<Integer> crear(@RequestBody Pais NuevoPais) {
		return responder(paisService.crear(NuevoPais), HttpStatus.CREATED);
	}

	@GetMapping("/mostrarTodo")
	public ResponseEntity<List<Pais>> mostrarTodo() {
		return responderLista(paisService.mostrarTodo());
	}

	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<Pais> buscarPorId(@PathVariable long id) {
		return responderPais(paisService.buscarPorId(id));
	}

	@GetMapping("/buscarPorNombre/{nombre}")
	public ResponseEntity<Pais> buscarPorNombre(@PathVariable String nombre) {
		return responderPais(paisService.buscarPorNombre(nombre));
	}

	@GetMapping("/buscarPorCapital/{capital}")
	public ResponseEntity<List<Pais>> buscarPorCapital(@PathVariable String capital) {
		return responderLista(paisService.buscarPorCapital(capital));
	}

	@GetMapping("/buscarPorMoneda/{moneda}")
	public ResponseEntity<List<Pais>> buscarPorMoneda(@PathVariable String moneda) {
		return responderLista(paisService.buscarPorMoneda(moneda));
	}

	@GetMapping("/buscarPorIdiomas/{idiomas}")
	public ResponseEntity<List<Pais>> buscarPorIdiomas(@PathVariable String idiomas) {
		return responderLista(paisService.buscarPorIdioma(idiomas));
	}

	@GetMapping("/buscarPorHabitantes/{habitantes}")
	public ResponseEntity<List<Pais>> buscarPorHabitantes(@PathVariable long habitantes) {
		return responderLista(paisService.buscarPorHabitantes(habitantes));
	}

	@PutMapping("/actualizarPorId/{id}")
	public ResponseEntity<Integer> actualizarPorId(@PathVariable long id, @RequestBody Pais NuevoDato) {
		int codigo = paisService.actualizarPorId(id, NuevoDato);
		if (codigo == 1) {
			
			return new ResponseEntity<>(codigo, HttpStatus.CREATED);
		}
		return responder(codigo, HttpStatus.ACCEPTED);
	}

	@PutMapping("/actualizarNombre/{id}/{nombre}")
	public ResponseEntity<Integer> actualizarNombre(@PathVariable long id, @PathVariable String nombre) {
		return responder(paisService.actualizarNombre(id, nombre), HttpStatus.ACCEPTED);
	}

	@PutMapping("/actualizarCapital/{id}/{capital}")
	public ResponseEntity<Integer> actualizarCapital(@PathVariable long id, @PathVariable String capital) {
		return responder(paisService.actualizarCapital(id, capital), HttpStatus.ACCEPTED);
	}

	@PutMapping("/actualizarMoneda/{id}/{moneda}")
	public ResponseEntity<Integer> actualizarMoneda(@PathVariable long id, @PathVariable String moneda) {
		return responder(paisService.actualizarMoneda(id, moneda), HttpStatus.ACCEPTED);
	}

	@PutMapping("/actualizarIdiomas/{id}/{idiomas}")
	public ResponseEntity<Integer> actualizarIdiomas(@PathVariable long id, @PathVariable String idiomas) {
		return responder(paisService.actualizarIdiomas(id, idiomas), HttpStatus.ACCEPTED);
	}

	@PutMapping("/actualizarHabitantes/{id}/{habitantes}")
	public ResponseEntity<Integer> actualizarHabitantes(@PathVariable long id, @PathVariable long habitantes) {
		return responder(paisService.actualizarHabitantes(id, habitantes), HttpStatus.ACCEPTED);
	}
	
	

	@DeleteMapping("/eliminarPorId/{id}")
	public ResponseEntity<Integer> eliminarPorId(@PathVariable long id) {
		return responder(paisService.eliminarPorId(id), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarPorNombre/{nombre}")
	public ResponseEntity<Integer> eliminarPorNombre(@PathVariable String nombre) {
		return responder(paisService.eliminarPorNombre(nombre), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/eliminarPorCapital/{capital}")
	public ResponseEntity<Integer> eliminarPorCapital(@PathVariable String capital) {
		return responder(paisService.eliminarPorCapital(capital), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/eliminarPorMoneda/{moneda}")
	public ResponseEntity<Integer> eliminarPorMoneda(@PathVariable String moneda) {
		return responder(paisService.eliminarPorMoneda(moneda), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/eliminarPorIdiomas/{idiomas}")
	public ResponseEntity<Integer> eliminarPorIdiomas(@PathVariable String idiomas) {
		return responder(paisService.eliminarPorIdiomas(idiomas), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/eliminarPorHabitantes/{habitantes}")
	public ResponseEntity<Integer> eliminarPorHabitantes(@PathVariable long habitantes) {
		return responder(paisService.eliminarPorHabitantes(habitantes), HttpStatus.ACCEPTED);
	}
}