package pl.mazur.pawel.Family.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mazur.pawel.Family.domain.Mother;

public interface MotherRepository extends JpaRepository<Mother, Long> {
}
