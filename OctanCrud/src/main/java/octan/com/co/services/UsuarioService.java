package octan.com.co.services;

import java.util.List;
import java.util.Optional;
import octan.com.co.dao.IUsuarioDao;
import octan.com.co.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/* En la clase encontramos los metodos del CRUD
   Se intancia la interfaz para herderar los metodos CRUD creado y el JPA

*/

@Service
@Transactional
public class UsuarioService {
    
    //Inyecta el codigo de la clase cuando sea necesario cuando sea necesario
    @Autowired
    IUsuarioDao usuarioDao;
    
    
    //Retorna una lista de usuarios
    public List<Usuario> listarUsuarios(){
        return usuarioDao.findAll();
    }
    
    //Retorna un optional de usuarios con parametro del id
    public Optional<Usuario> obtenerUsuario(int idUsuario){
        return usuarioDao.findById(idUsuario);
        
    }
 
    //Retoran un optional para obtener el nombre por usuario
    public Optional<Usuario> obtenerUsuarioNombre(String nombre){
        return usuarioDao.findByNombre(nombre);
    }
    
    //Retora una ista de usuarios por el nombre
    public List<Usuario> ObtenerUsuariosNombres(String nombre){
        return usuarioDao.search(nombre);
        
    } 
    
    //Gurada un objeto de usuario
    public void guradar(Usuario usuario ){
        usuarioDao.save(usuario);
    }
    
    //Elimina un objeto de usuario con el ID
    public void eliminar(int idUsuario){
        usuarioDao.deleteById(idUsuario);
    }
    
    //Retorna un boolean si el id existe
    public boolean existePorId(int idUsuario){
        return usuarioDao.existsById(idUsuario);
    }
    
    //Retorna un boolean si el nombre existe
    public boolean existePorNombre(String nombre){
        return usuarioDao.existsByNombre(nombre);
    }
    
}
