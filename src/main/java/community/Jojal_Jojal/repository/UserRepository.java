package community.Jojal_Jojal.repository;

import community.Jojal_Jojal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
