package pl.edu.utp.wtie.homeworkweek7.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    private DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        String sql = "CREATE TABLE IF NOT EXISTS cars (car_id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "mark VARCHAR(20) NOT NULL, model VARCHAR(30) NOT NULL, color VARCHAR(20) NOT NULL, " +
                "production_year INTEGER NOT NULL)";
        getJdbcTemplate().update(sql);
    }
}
