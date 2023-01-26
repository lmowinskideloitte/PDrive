package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import pl.put.poznan.pdrive.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(@NonNull String username);
}
