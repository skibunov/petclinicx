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
    public int delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Animal animal = session.load(Animal.class, id);
        session.remove(animal);
        return 0;
    }

    @Override
    public int update(Animal animal) {
        Session session = sessionFactory.getCurrentSession();
        session.save(animal);
        return 0;
    }

    @Override
    public Animal create(Animal animal) {
        Session session = sessionFactory.getCurrentSession();
        return (Animal) session.save(animal);
    }

    @Override
    public List<Animal> getAll() {
        return null;
    }

    @Override
    public Animal getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Animal.class, id);
    }
}
