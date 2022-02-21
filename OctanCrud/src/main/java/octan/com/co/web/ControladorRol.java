
package octan.com.co.web;

import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import octan.com.co.domain.Rol;
import octan.com.co.dto.Mensaje;
import octan.com.co.services.RolService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* 
    Clase el cual tiene los metodos de la Rest Api,
    contiene el mapeo de la url, generación de log.info del servicio
    y se utiliza la url de donde se va a consumer la Api
*/

@RestController
@Slf4j
@RequestMapping("/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorRol {
    
    //Inyecta el codigo de la clase servivce cuando sea necesario
    @Autowired
    RolService rolService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Rol>> listarRoles() {
        List<Rol> usuarios = rolService.listarRoles();
        return new ResponseEntity<List<Rol>>(usuarios, HttpStatus.OK);
    }
    
}
