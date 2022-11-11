package mintic2022team9.ms_seguridad.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mintic2022team9.ms_seguridad.Modelos.Permiso;
import mintic2022team9.ms_seguridad.Modelos.PermisosRoles;
import mintic2022team9.ms_seguridad.Modelos.Rol;
import mintic2022team9.ms_seguridad.Repositorios.RepoPemisosRoles;
import mintic2022team9.ms_seguridad.Repositorios.RepoPermiso;
import mintic2022team9.ms_seguridad.Repositorios.RepoRol;
import org.springframework.http.HttpStatus;

@CrossOrigin
@RestController
@RequestMapping("/permisos-roles")

public class ControladorPermisosRoles {
    @Autowired private RepoPemisosRoles repositorioPermisosRoles;
    @Autowired private RepoPermiso repositorioPermiso;
    @Autowired private RepoRol repositorioRol;

    @GetMapping("")
    public List<PermisosRoles> index(){
        return this.repositorioPermisosRoles.findAll();
    }    

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles create(@PathVariable String id_rol,@PathVariable String id_permiso){
        PermisosRoles nuevo=new PermisosRoles();
        Rol rol=this.repositorioRol.findById(id_rol).get();
        Permiso
        permiso=this.repositorioPermiso.findById(id_permiso).get();
        if (rol!=null && permiso!=null){
        nuevo.setPermiso(permiso);
        nuevo.setRol(rol);
        return this.repositorioPermisosRoles.save(nuevo);
        }else{
        return null;
        }
}

    @GetMapping("{id}")
    public PermisosRoles show(@PathVariable String id){
        PermisosRoles permisosRolesActual=this.repositorioPermisosRoles
                        .findById(id)
                        .orElse(null);
        return permisosRolesActual;
    }
    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles update(@PathVariable String id, @PathVariable String id_rol, @PathVariable String id_permiso){
        PermisosRoles permisosRolesActual=this.repositorioPermisosRoles.findById(id).orElse(null);
        Rol rol=this.repositorioRol.findById(id_rol).get();
        Permiso permiso=this.repositorioPermiso.findById(id_permiso).get();

        if(permisosRolesActual!=null && permiso!=null && rol!=null){
            permisosRolesActual.setPermiso(permiso);
            permisosRolesActual.setRol(rol);
            return
            this.repositorioPermisosRoles.save(permisosRolesActual);
            }else{
            return null;
            }
        
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        PermisosRoles permisosRolesActual=this.repositorioPermisosRoles
                .findById(id)
                .orElse(null);
        if (permisosRolesActual!=null){
            this.repositorioPermisosRoles.delete(permisosRolesActual);
        }
    }
    
}
