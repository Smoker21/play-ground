package demo.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.TomcatDataSourceConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ConfigurationProperties(value = "spring.xedatasource")
public class DataSourceConfig extends TomcatDataSourceConfiguration {

	@Bean
	public DataSource dataSource() {
		return super.dataSource();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dsItems) {
		return new JdbcTemplate(dataSource());
	}
}
