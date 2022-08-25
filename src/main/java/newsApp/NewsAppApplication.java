package newsApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsAppApplication.class, args);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception ex) {

		}
	}

}


// many2many
// security
// logging