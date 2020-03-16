package singh.mahabir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import singh.mahabir.security.repository.User;
import singh.mahabir.security.repository.UserRepository;

@SpringBootApplication
public class ImageStoreServiceApplication implements CommandLineRunner {

    @Autowired
    UserRepository r;

    @Autowired
    PasswordEncoder encoder;

    public static void main(String[] args) {
	SpringApplication.run(ImageStoreServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	User u = new User();
	u.setUserName("admin");
	u.setPassword(encoder.encode("admin"));
	u.setActive(true);
	u.setRoles("ROLE_ADMIN");

	User t = new User();
	t.setUserName("user");
	t.setPassword(encoder.encode("user"));
	t.setActive(true);
	t.setRoles("ROLE_USER");

	r.save(u);
	r.save(t);
    }

}
