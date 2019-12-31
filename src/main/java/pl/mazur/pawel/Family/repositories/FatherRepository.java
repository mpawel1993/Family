package pl.mazur.pawel.Family.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mazur.pawel.Family.domain.Father;

@Repository
public interface FatherRepository extends JpaRepository<Father, Long> {
}
