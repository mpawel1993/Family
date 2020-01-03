package pl.mazur.pawel.Family.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mazur.pawel.Family.domain.Father;

import java.util.Optional;

@Repository
public interface FatherRepository extends JpaRepository<Father, Long> {

    Optional<Father> findByPesel(String pesel);
}
