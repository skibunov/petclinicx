package by.gsu.petclinicx.model;

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
