package octan.com.co.dao;


import java.util.List;
import java.util.Optional;
import octan.com.co.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


//Interface de usuario 
//Hereeda metodos de JPA para crear modificar y editar
public interface IUsuarioDao  extends JpaRepository<Usuario, Integer>{
    
    //Metodos abstractos para encontrar nombre tipo optional y retornar un boolean si esiste
    Optional<Usuario>findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    
    
    //Query a la base de datos para generar metodo abstracto del listado de usuarios.
    @Query(value = "SELECT * FROM usuario u WHERE u.nombre LIKE %?1%", nativeQuery=true)
    List<Usuario> search(String nombre);
    
   
    
}
