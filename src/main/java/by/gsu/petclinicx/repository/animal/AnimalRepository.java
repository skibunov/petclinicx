package by.gsu.petclinicx.repository.animal;

import by.gsu.petclinicx.model.Animal;
import by.gsu.petclinicx.model.Cat;
import by.gsu.petclinicx.model.Dog;
import by.gsu.petclinicx.repository.common.CrudRepository;
import by.gsu.petclinicx.repository.common.PreparedStatementParamsProcessor;
import by.gsu.petclinicx.repository.common.QueryExecutor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class AnimalRepository implements CrudRepository<Animal> {
    private final QueryExecutor queryExecutor;
    private final RowMapper<Animal> animalResultSetParser;

    public AnimalRepository(QueryExecutor queryExecutor, RowMapper<Animal> animalResultSetParser) {
        this.queryExecutor = queryExecutor;
        this.animalResultSetParser = animalResultSetParser;
    }

    @Override
    public List<Animal> getAll() {
        return queryExecutor.getAll("select * from animals", animalResultSetParser);
    }

    @Override
    public Animal getById(Long id) {
        return queryExecutor.getById("select  * from disease where id = " + id, animalResultSetParser);
    }

    @Override
    public int delete(Long id) {
        return queryExecutor.executeUpdate("delete from animals where id  = ?", new PreparedStatementParamsProcessor() {
            @Override
            public void process(PreparedStatement ps) throws SQLException {
                ps.setLong(1, id);
            }
        });
    }

    @Override
    public int update(Animal animal) {
        return queryExecutor.executeUpdate(
                "update animals set name = ?, bones = ?, mice = ?, disease_id = ? where id  = ?", ps -> {
            ps.setString(1, animal.getName());
            if (animal instanceof Cat) {
                ps.setNull(2, Types.INTEGER);
                ps.setInt(3, ((Cat) animal).getMice());
            } else {
                ps.setInt(2, ((Dog) animal).getBones());
                ps.setNull(3, Types.INTEGER);
            }
            ps.setLong(4, animal.getDisease().getId());
            //and id
            ps.setLong(5, animal.getId());
        });
    }

    @Override
    public Animal create(Animal animal) {
        long id = queryExecutor.executeCreate("insert into animals (name, bones, mice, disease_id) values (?,?,?,?)", ps -> {
            ps.setString(1, animal.getName());
            if (animal instanceof Cat) {
                ps.setNull(2, Types.INTEGER);
                ps.setInt(3, ((Cat) animal).getMice());
            } else {
                ps.setInt(2, ((Dog) animal).getBones());
                ps.setNull(3, Types.INTEGER);
            }
            ps.setLong(4, animal.getDisease().getId());
        });
        animal.setId(id);
        return animal;
    }
}
