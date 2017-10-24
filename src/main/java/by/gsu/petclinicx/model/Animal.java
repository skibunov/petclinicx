package by.gsu.petclinicx.model;

public abstract class Animal extends NamedEntity {

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
