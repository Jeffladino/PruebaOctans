package octan.com.co.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

 //  Clase Rol de la entidad de la BD Rol

@Entity
@Data
@Table(name = "usuario")
public class Usuario  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;
    
    @NotEmpty
    private String nombre;
    private char activo; 
    
    //Relaciones en JPA una  a uno con el constrain rol
    @OneToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String nombre, char activo, Rol rol) {
        this.nombre = nombre;
        this.activo = activo;
        this.rol = rol;
    }
    
    
    
}
