package octan.com.co.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;



 //  Clase Rol de la entidad de la BD Rol



@Entity
@Data
@Table(name = "rol")
public class Rol implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rol")
    private int idRol;
    
    @NotEmpty
    private String nombre;
    
}
