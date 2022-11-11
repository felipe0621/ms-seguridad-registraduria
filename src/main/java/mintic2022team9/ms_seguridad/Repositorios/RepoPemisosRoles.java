package mintic2022team9.ms_seguridad.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import mintic2022team9.ms_seguridad.Modelos.PermisosRoles;

public interface RepoPemisosRoles extends MongoRepository<PermisosRoles, String>{
    
    
}
