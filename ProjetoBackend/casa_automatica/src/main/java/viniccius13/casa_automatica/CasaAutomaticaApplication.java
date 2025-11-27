package viniccius13.casa_automatica;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CasaAutomaticaApplication {
    public static void main(String[] args) {
        // Carrega variáveis do arquivo .env se existir
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();
        
        // Define as variáveis de ambiente do sistema a partir do .env
        dotenv.entries().forEach(entry -> {
            if (System.getProperty(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
        
        SpringApplication.run(CasaAutomaticaApplication.class, args);
    }
}
