package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.put.poznan.pdrive.entity.UserType;
import pl.put.poznan.pdrive.entity.UserTypeE;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    UserType findByRole(UserTypeE role);
}
