package octan.com.co.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import octan.com.co.domain.Usuario;
import octan.com.co.dto.Mensaje;
import octan.com.co.services.UsuarioService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* 
    Clase el cual tiene los metodos de la REST Api,
    contiene el mapeo de la url, generación de log.info del servicio
    y se utiliza la url de donde se va a consumer la Api
 */
@RestController
@Slf4j
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorUsuario {

    //Inyecta el codigo de la clase servivce cuando sea necesario
    @Autowired
    UsuarioService usuarioService;

    //Petición get para listar usuarios
    //retonra un responseEnttity de usuarios y el status
    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    //Petición get para obter un usuario por id
    //retonra un responseEnttity de usuario y el status
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable("id") int id) {
        if (!usuarioService.existePorId(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Usuario usurio = usuarioService.obtenerUsuario(id).get();

        return new ResponseEntity<Usuario>(usurio, HttpStatus.OK);
    }

    //Petición get para obtener usuario por nombre
    //retonra un responseEnttity de  usuario y el status
    @GetMapping("/usuariopornombre/{nombre}")
    public ResponseEntity<Usuario> obnerUsuarioPorNombre(@PathVariable("nombre") String nombre) {
        if (!usuarioService.existePorNombre(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioService.obtenerUsuarioNombre(nombre).get();

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    //Petición get para listar usuarios por nombre
    //retonra un responseEnttity de usuarios por nombre y el status
    @GetMapping("/usuariospornombre/{nombre}")
    public ResponseEntity<List<Usuario>> obnerUsuariosPorNombre(@PathVariable("nombre") String nombre) {

        if (!usuarioService.existePorNombre(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }

        List<Usuario> usuarios = usuarioService.ObtenerUsuariosNombres(nombre);
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    //Petición post para guardar usuario
    //retonra un responseEnttity del objeto mensaje  y el status
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario) {
        if (StringUtils.isBlank(usuario.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (usuario.getActivo() == ' ' || usuario.getRol().getNombre() == null) {
            return new ResponseEntity(new Mensaje("Escoger Activo o no"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existePorNombre(usuario.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario2 = new Usuario(usuario.getNombre(), usuario.getActivo(), usuario.getRol());
        usuarioService.guradar(usuario2);
        return new ResponseEntity(new Mensaje("El usuario se guardo"), HttpStatus.OK);
    }
    
    //Petición put para editar usuario
    //retonra un responseEnttity del objeto mensaje  y el status
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        if (!usuarioService.existePorId(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        //Validación para que si existe el nombre no edite el usuario.
        if (usuarioService.existePorNombre(usuario.getNombre())
                && usuarioService.obtenerUsuarioNombre(usuario.getNombre()).get().getIdUsuario() != id) {
            return new ResponseEntity(new Mensaje("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuario.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (usuario.getActivo() == ' ') {
            return new ResponseEntity(new Mensaje("Escoger activo o no "), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario2 = usuarioService.obtenerUsuario(id).get();
        usuario2.setNombre(usuario.getNombre());
        usuario2.setActivo(usuario.getActivo());
        usuario2.setRol(usuario.getRol());
        usuarioService.guradar(usuario2);
        return new ResponseEntity(new Mensaje("El usuario se actualizo"), HttpStatus.OK);
    }

    //Petición delete para eliminar el usuario
    //retonra un responseEnttity del objeto mensaje y el status
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int id) {
        if (!usuarioService.existePorId(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        usuarioService.eliminar(id);
        return new ResponseEntity(new Mensaje("El usuario se elimino"), HttpStatus.OK);
    }
}
