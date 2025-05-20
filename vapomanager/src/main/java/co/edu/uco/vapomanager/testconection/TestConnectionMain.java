package co.edu.uco.vapomanager.testconection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication(scanBasePackages = "co.edu.uco.vapomanager")
public class TestConnectionMain {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TestConnectionMain.class, args);

        // Obtén el DataSource
        DataSource ds = ctx.getBean(DataSource.class);

        // Prueba la conexión
        try (Connection conn = ds.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✔️ Conexión exitosa a la base de datos.");
            } else {
                System.err.println("❌ No se pudo abrir la conexión.");
            }
        } catch (Exception e) {
            System.err.println("❌ Error al obtener conexión:");
            e.printStackTrace();
        } finally {
            // Cierra el contexto y termina la app
            SpringApplication.exit(ctx);
        }
    }
}
