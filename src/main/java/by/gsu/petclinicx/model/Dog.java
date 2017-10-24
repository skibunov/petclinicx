package by.gsu.petclinicx.model;

public class Dog extends Animal {
    private Integer bones;

    public Dog() {
    }

    public Dog(Long id, String name, Disease disease, Integer bones) {
        super(id, name, disease);
        this.bones = bones;
    }

    public Integer getBones() {
        return bones;
    }

    public void setBones(Integer bones) {
        this.bones = bones;
    }

    @Override
    public String toString() {
        return "Dog{" +
                super.toString() +
                ", bones=" + bones + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Dog dog = (Dog) o;

        return bones != null ? bones.equals(dog.bones) : dog.bones == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bones != null ? bones.hashCode() : 0);
        return result;
    }
}
