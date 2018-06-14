package by.gsu.petclinicx.model;

import javax.persistence.*;

@Entity
@Table(name = "animals")
@DiscriminatorColumn(name = "atype")
public abstract class Animal extends NamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Disease disease;

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Animal() {
    }

    public Animal(Long id, String name, Disease disease) {
        super(id, name);
        this.disease = disease;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", disease=" + disease;
    }
}
