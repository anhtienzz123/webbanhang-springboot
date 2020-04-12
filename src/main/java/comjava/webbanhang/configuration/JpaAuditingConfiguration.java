package comjava.webbanhang.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import comjava.webbanhang.other.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvide")
public class JpaAuditingConfiguration {

	@Bean
	public AuditorAware<String> auditorProvide(){
		return new AuditorAwareImpl();
	}
}
