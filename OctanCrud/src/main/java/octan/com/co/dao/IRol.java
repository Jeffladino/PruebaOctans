
package octan.com.co.dao;

import octan.com.co.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

//Interface de usuario 
//Hereda metodos de JPA para crear modificar y editar
public interface IRol extends JpaRepository<Rol, Integer> {
    
}
