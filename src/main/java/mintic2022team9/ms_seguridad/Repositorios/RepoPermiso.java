package mintic2022team9.ms_seguridad.Repositorios;
import org.springframework.data.mongodb.repository.MongoRepository;
import mintic2022team9.ms_seguridad.Modelos.Permiso;

public interface RepoPermiso extends MongoRepository<Permiso, String>{



}