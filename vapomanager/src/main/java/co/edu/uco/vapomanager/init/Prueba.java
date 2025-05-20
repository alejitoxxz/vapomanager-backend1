package co.edu.uco.vapomanager.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class Prueba {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/prueba")
    public String pruebaConexión() {
        try (Connection conn = dataSource.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                return "✔️ Conexión exitosa a la base de datos.";
            } else {
                return "❌ No se pudo abrir la conexión.";
            }
        } catch (Exception ex) {
            return "❌ Error de conexión: " + ex.getMessage();
        }
    }
}
