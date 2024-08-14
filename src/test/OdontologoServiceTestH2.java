package test;

import dao.impl.OdontologoDaoH2;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTestH2 {
    static Logger logger = Logger.getLogger(OdontologoServiceTestH2.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @BeforeAll
    static void crearTablas() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./clinicaOdontologica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Testear que se guarde el odontologo")
    void caso1() {
        Odontologo odontologo = new Odontologo(5678, "Carlos", "Rodriguez");
        Odontologo guardarOdontologoEnDB = odontologoService.guardarOdontologo(odontologo);
        assertNotNull(guardarOdontologoEnDB.getId());
    }

    @Test
    @DisplayName("Testear que liste todos los odontologos")
    void caso2(){
        Odontologo odontologo = new Odontologo(5678,"Carlos","Rodriguez");
        odontologoService.guardarOdontologo(odontologo);
        List<Odontologo> listaDeOdontologos = odontologoService.listarTodosLosOdontologos();
        assertFalse(listaDeOdontologos.isEmpty());
    }


}
