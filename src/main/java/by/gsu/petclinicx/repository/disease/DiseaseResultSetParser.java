package by.gsu.petclinicx.repository.disease;

import by.gsu.petclinicx.model.Disease;
import by.gsu.petclinicx.repository.common.ResultSetParser;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DiseaseResultSetParser
        implements ResultSetParser<Disease> {

    @Override
    public List<Disease> parse(ResultSet rs) {
        try {
            List<Disease> diseases = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");

                Disease d = new Disease(id, name);
                diseases.add(d);
            }
            rs.close();
            return diseases;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
