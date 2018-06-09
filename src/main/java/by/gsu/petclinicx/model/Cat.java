package by.gsu.petclinicx.model;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cat")
public class Cat extends Animal {

    @Basic
    private Integer mice;

    public Cat() {
    }

    public Cat(Long id, String name, Disease disease, Integer mice) {
        super(id, name, disease);
        this.mice = mice;
    }

    public Integer getMice() {
        return mice;
    }

    public void setMice(Integer mice) {
        this.mice = mice;
    }

    @Override
    public String toString() {
        return "Cat{" +
                super.toString() +
                ", mice=" + mice + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Cat cat = (Cat) o;

        return mice != null ? mice.equals(cat.mice) : cat.mice == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mice != null ? mice.hashCode() : 0);
        return result;
    }
}
