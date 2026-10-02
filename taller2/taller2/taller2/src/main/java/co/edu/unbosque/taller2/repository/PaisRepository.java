package co.edu.unbosque.taller2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.taller2.entity.Pais;

public interface PaisRepository extends CrudRepository<Pais, Long> {


	public Optional<Pais> findByNombre(String nombre);
	public boolean existsByNombre(String nombre);
	public List<Pais> findByCapital(String capital);
	public List<Pais> findByMoneda(String moneda);
    public List<Pais> findByIdiomasContaining(String idioma);
    public List<Pais> findByHabitantes(long habitantes);


	
	public long deleteByNombre(String nombre);
	public long deleteByCapital(String capital);
	public long deleteByMoneda(String moneda);
	public long deleteByIdiomas(String idiomas);
	public long deleteByHabitantes(long habitantes);
}