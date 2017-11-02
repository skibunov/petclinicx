package by.gsu.petclinicx.repository.disease;

import by.gsu.petclinicx.model.Disease;
import by.gsu.petclinicx.repository.common.GetRepository;
import by.gsu.petclinicx.repository.common.QueryExecutor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DiseaseRepository implements GetRepository<Disease> {

    private final RowMapper<Disease> parser;
    private final QueryExecutor queryExecutor;

    public DiseaseRepository(RowMapper<Disease> parser,
                             QueryExecutor queryExecutor) {
        this.parser = parser;
        this.queryExecutor = queryExecutor;
    }

    @Override
    @Transactional
    public List<Disease> getAll() {
        return queryExecutor.getAll("select  * from disease", parser);
    }

    @Override
    public Disease getById(Long id) {
       return queryExecutor.getById(
               "select  * from disease where id = " + id, parser
       );
    }
}
