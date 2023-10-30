package edu.javeriana.react3.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.javeriana.react3.modelo.Estudiante;

 
public interface RepositorioEstudiante extends JpaRepository<Estudiante, Integer> {

}