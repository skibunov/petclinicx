package by.gsu.petclinicx.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "disease")
public class Disease extends NamedEntity {

    public Disease() {
    }

    public Disease(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Disease{" + super.toString() + "}" ;
    }


}
