package by.gsu.petclinicx.repository.disease;

import by.gsu.petclinicx.model.Disease;
import by.gsu.petclinicx.repository.common.QueryExecutor;
import by.gsu.petclinicx.repository.common.ResultSetParser;

import java.util.List;

public class DiseaseRepository {

    private final ResultSetParser<Disease> parser;
    private final QueryExecutor queryExecutor;

    public DiseaseRepository(ResultSetParser<Disease> parser,
                             QueryExecutor queryExecutor) {
        this.parser = parser;
        this.queryExecutor = queryExecutor;
    }

    public List<Disease> getAll() {
        return queryExecutor.getAll("select  * from disease", parser);
    }

    public Disease getById(Long id) {
       return queryExecutor.getById(
               "select  * from disease where id = " + id, parser
       );
    }
}
