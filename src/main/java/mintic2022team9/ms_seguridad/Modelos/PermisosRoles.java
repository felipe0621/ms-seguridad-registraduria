package mintic2022team9.ms_seguridad.Modelos;

import lombok.Data;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;

@Document
@Data

public class PermisosRoles {
    @Id
    @Setter(AccessLevel.NONE) //le establezco acces none para qeu no pued amodificarse el id
    private String _id;

    @DBRef private Rol rol;
    @DBRef private Permiso permiso;
    
}
