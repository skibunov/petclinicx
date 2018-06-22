package by.gsu.petclinicx.repository.animal;

import by.gsu.petclinicx.model.Animal;
import by.gsu.petclinicx.repository.common.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("animalRepository")
@Transactional
public class AnimalRepository implements CrudRepository<Animal> {

    private final SessionFactory sessionFactory;

    @Autowired
    public AnimalRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Animal animal = session.load(Animal.class, id);
        session.remove(animal);
    }

    @Override
    public void update(Animal animal) {
        sessionFactory.getCurrentSession().update(animal);
    }

    @Override
    public Animal create(Animal animal) {
        return (Animal) sessionFactory.getCurrentSession().save(animal);
    }

    @Override
    public List<Animal> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery(
                        "select a from Animal a inner join fetch a.disease", Animal.class)
                .list();
    }

    @Override
    public Animal getById(Long id) {
        Animal animal = sessionFactory.getCurrentSession().get(Animal.class, id);
        return animal;
    }
}
