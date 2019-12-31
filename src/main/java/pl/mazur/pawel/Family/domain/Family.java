package pl.mazur.pawel.Family.domain;


import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import pl.mazur.pawel.Family.exceptions.BusinessException;

import javax.persistence.*;
import java.util.List;

import static pl.mazur.pawel.Family.service.FatherService.FAMILY_HAVE_FATHER_STATEMENT;
import static pl.mazur.pawel.Family.service.FatherService.FAMILY_HAVE_MOTHER_STATEMENT;

@Data
@Entity
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "FAMILY")
public class Family {

    @Id
    @GeneratedValue
    Long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    Father father;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    Mother mother;

    @JoinColumn(name = "FAMILY_ID", referencedColumnName = "ID")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Child> childs;

    public Family checkIsFatherExist() {
        if (this.getFather() != null) {
            throw new BusinessException(FAMILY_HAVE_FATHER_STATEMENT);
        }
        return this;
    }

    public Family checkIsMotherExist() {
        if (this.getMother() != null) {
            throw new BusinessException(FAMILY_HAVE_MOTHER_STATEMENT);
        }
        return this;
    }
}

