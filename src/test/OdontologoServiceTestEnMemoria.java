package test;
import dao.impl.OdontologoDaoEnMemoria;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class OdontologoServiceTestEnMemoria {
    static Logger logger = Logger.getLogger(OdontologoServiceTestEnMemoria.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoEnMemoria());
    @Test
    @DisplayName("Testear que se guarde el odontologo")
    void caso1(){
        Odontologo odontologo = new Odontologo(123,"Lorenzo","Fares");
        Odontologo guardarOdontologoEnDB = odontologoService.guardarOdontologo(odontologo);
        assertNotNull(guardarOdontologoEnDB.getId());
    }

    @Test
    @DisplayName("Testear que se busquen todos los odontologos")
    void caso2(){
        Odontologo odontologo = new Odontologo(123,"Lorenzo","Fares");
        odontologoService.guardarOdontologo(odontologo);
        List<Odontologo> listaOdontologos = odontologoService.listarTodosLosOdontologos();
        assertFalse(listaOdontologos.isEmpty());
    }
}
