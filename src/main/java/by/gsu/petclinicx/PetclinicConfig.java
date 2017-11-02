package by.gsu.petclinicx;

import by.gsu.petclinicx.model.Disease;
import by.gsu.petclinicx.repository.common.QueryExecutor;
import by.gsu.petclinicx.repository.disease.DiseaseRepository;
import by.gsu.petclinicx.repository.disease.DiseaseResultSetParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("by.gsu")
public class PetclinicConfig {

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:sqlite:animals.db");
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DiseaseResultSetParser diseaseResultSetParser() {
        return new DiseaseResultSetParser();
    }

    @Bean
    public DiseaseRepository diseaseRepository(QueryExecutor executor,
                                               RowMapper<Disease> parser) {
        return new DiseaseRepository(parser, executor);
    }
}
