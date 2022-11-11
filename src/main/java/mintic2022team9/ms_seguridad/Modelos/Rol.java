package mintic2022team9.ms_seguridad.Modelos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;

@Document
@Data
public class Rol {
    @Id
    @Setter(AccessLevel.NONE) //le establezco acces none para qeu no pued amodificarse el id
    private String _id;

    private String nombre_rol;

}
