package by.gsu.petclinicx.repository.disease;

import by.gsu.petclinicx.model.Disease;
import by.gsu.petclinicx.repository.common.GetRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DiseaseRepository implements GetRepository<Disease> {

    private final SessionFactory factory;

    @Autowired
    public DiseaseRepository(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    @Transactional
    public List<Disease> getAll() {
        return factory.getCurrentSession()
                .createQuery("select d from Disease d", Disease.class)
                .list();
    }

    @Override
    public Disease getById(Long id) {
       return factory.getCurrentSession().get(Disease.class, id);
    }
}
