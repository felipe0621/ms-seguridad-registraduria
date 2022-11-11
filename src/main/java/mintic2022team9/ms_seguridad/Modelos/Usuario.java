package mintic2022team9.ms_seguridad.Modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Document()  //document y data son anotaciones de clase 
@Data   //esta libreria automaticamente me construye todos los getters and setters
public class Usuario {
    @Id
    @Setter(AccessLevel.NONE) //le establezco acces none para qeu no pued amodificarse el id
    private String _id;

     private String seudonimo;
     private String correo;
     private String contrasena;
     @DBRef private Rol rol;  //clave foránea a ROL

   @JsonIgnore     //para que ignore la contraseña es decir al hacer la peticion no se necesita ese campo
   public String getContrasena(){
        return contrasena;
   }
   @JsonProperty
   public void setContrasena(String contrasena){
   if(contrasena != null) this.contrasena = contrasena;
   }

}
