package mongodb.custom.repository;

import java.util.List;

public interface CustomUserRepository {
    List<User> findByName(String name);
}
