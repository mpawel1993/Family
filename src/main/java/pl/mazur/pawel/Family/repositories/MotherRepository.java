package pl.mazur.pawel.Family.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mazur.pawel.Family.domain.Mother;

import java.util.Optional;

@Repository
public interface MotherRepository extends JpaRepository<Mother, Long> {

    Optional<Mother> findByPesel(String pesel);
}
