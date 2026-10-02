package co.edu.unbosque.taller2.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.edu.unbosque.taller2.entity.Pais;
import co.edu.unbosque.taller2.repository.PaisRepository;

@Component
public class CargaInicial implements CommandLineRunner {

	@Autowired
	private PaisRepository paisRepo;

	@Override
	public void run(String... args) throws Exception {

		if (paisRepo.count() == 0) {

			paisRepo.saveAll(Arrays.asList(
					new Pais("Colombia", "Bogotá", "Peso colombiano", "Español", 52000000L),
					new Pais("México", "Ciudad de México", "Peso mexicano", "Español", 128000000L),
					new Pais("Argentina", "Buenos Aires", "Peso argentino", "Español", 46000000L),
					new Pais("Brasil", "Brasilia", "Real", "Portugués", 215000000L),
					new Pais("Estados Unidos", "Washington D.C.", "Dólar estadounidense", "Inglés", 335000000L),
					new Pais("Francia", "París", "Euro", "Francés", 68000000L),
					new Pais("Alemania", "Berlín", "Euro", "Alemán", 84000000L),
					new Pais("Japón", "Tokio", "Yen", "Japonés", 124000000L),
					new Pais("India", "Nueva Delhi", "Rupia india", "Hindi, Inglés", 1428000000L),
					new Pais("Canadá", "Ottawa", "Dólar canadiense", "Inglés, Francés", 40000000L)));

			System.out.println("Paises cargados con exito");
		}
	}
}