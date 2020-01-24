package pl.mazur.pawel.Family.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mazur.pawel.Family.domain.Child;

import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    Optional<Child> findByPesel(String pesel);

    Child findFirstByOrderByIdDesc();
}