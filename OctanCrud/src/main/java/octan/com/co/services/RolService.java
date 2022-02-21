package octan.com.co.services;

import java.util.List;
import java.util.Optional;
import octan.com.co.dao.IRol;
import octan.com.co.domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolService {
    
    //Inyecta el codigo de la clase Dao cuando sea necesario
    @Autowired
    IRol rolDao;
    
     public List<Rol> listarRoles(){
        return rolDao.findAll();
    }
 
}
