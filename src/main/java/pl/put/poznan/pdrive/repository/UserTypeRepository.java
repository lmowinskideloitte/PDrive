package pl.put.poznan.pdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.put.poznan.pdrive.entity.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}
