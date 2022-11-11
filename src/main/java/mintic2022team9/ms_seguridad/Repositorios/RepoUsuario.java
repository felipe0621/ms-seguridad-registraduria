package mintic2022team9.ms_seguridad.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import mintic2022team9.ms_seguridad.Modelos.Usuario;

public interface RepoUsuario extends MongoRepository<Usuario, String>{
    /* @Query(value=*{}*,fields=*{seudonimo:0, rol:0}}*))
    List<Usuario> index();

    List<Usuario> findByEmail(String email);  */

    /* @Aggregation(pipeline={
        "{'$lookup': {from:'rol', localfield:'rol.$id', foreignField:'_id', as:'rol'}}"
        "{'$match':{'rol.tipo':?70}}"
    })
    List<Usuario> findByRol(String rol): */
    
}
