package mongo.keywords.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class QueryClient implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(QueryClient.class, args);
    }

    public void run(String... strings) throws Exception {
        List<User> users = userRepository.findByName("test1");
        System.out.println(users.toString());
    }
}
