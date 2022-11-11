package mintic2022team9.ms_seguridad.Controladores;

import java.lang.ModuleLayer.Controller;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import lombok.extern.apachecommons.CommonsLog;
import mintic2022team9.ms_seguridad.Modelos.Rol;
import mintic2022team9.ms_seguridad.Modelos.Usuario;
import mintic2022team9.ms_seguridad.Repositorios.RepoRol;
import mintic2022team9.ms_seguridad.Repositorios.RepoUsuario;

@CommonsLog //para poner mensajes de logging
@RestController  //cada unao de estas clases conecta los metodos 
@CrossOrigin    // con el framework   lineas 25-26-27
@RequestMapping("/usuarios")

public class ControladorUsuario {
    //para que el controlador instancie el repositorio
    @Autowired private RepoUsuario repositorio;
    @Autowired private RepoRol repositorioRol;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Usuario> index(){
        log.debug("[GET /usuarios]");
        List<Usuario> l = null;
        try {
            l = repositorio.findAll();
        } catch (Exception e) {
            log.error("[GET /usuarios]" + e.getMessage() + " -> " + e.getCause());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getMessage(), e.getCause());
        }        
        return l;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public Usuario retrieve(@PathVariable String id){
        log.debug("[GET /usuarios" + id + "]");
        Usuario u = repositorio.findById(id).orElse(null);
        if(u == null){
            log.error("[GET /usuarios/" + id + "] El Usuario no pudo ser instanciado");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no fue encontrado");

        }        
        return u;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@RequestBody Usuario infoUsuario){
        log.debug("[POST /usuarios]");
        infoUsuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
        Usuario u = null;
        try {
            u = repositorio.save(infoUsuario);
        } catch (Exception e) {
            log.error("[POST /usuarios]" + e.getMessage() + " -> " + e.getCause());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getMessage(), e.getCause());
        }
        
        return u;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("{id}")
    public Usuario update(@PathVariable String id, @RequestBody Usuario infoUsuario ){
        log.debug("[PUT /usuarios]" +id+"]" + infoUsuario);
        Usuario usuarioActual=repositorio.findById(id).orElse(null);
        if (usuarioActual!=null){
            usuarioActual.setSeudonimo(infoUsuario.getSeudonimo());
            usuarioActual.setCorreo(infoUsuario.getCorreo());
            usuarioActual.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
            return repositorio.save(usuarioActual);
        }else{
            return null;
        }
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Usuario usuarioActual= repositorio.findById(id).orElse(null);
        if (usuarioActual!=null){
            repositorio.delete(usuarioActual);
        }        
        
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}/rol/{id_rol}")
    public Usuario asignarRol(@PathVariable String id, @PathVariable String id_rol){
        log.debug("[ASIGNARrol /usuarios]" +id+"] -> rol" + id_rol);
        Usuario u = repositorio.findById(id).orElse(null);
        Rol r = repositorioRol.findById(id_rol).orElse(null);
        if(u == null || r == null){
            log.error("[ASIGNARrol /usuarios "+id+"] -> rol" + id_rol);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "El Usuario o el Rol no existen");
        }
        u.setRol(r);
        return repositorio.save(u);

    }
    

    
     private String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

     }


    
}
