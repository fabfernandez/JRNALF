package com.mercadolibre.finalchallengedemo;

import com.mercadolibre.finalchallengedemo.config.SpringConfig;
import com.mercadolibre.finalchallengedemo.util.ScopeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	// Bean para mapear entities a dtos
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		ScopeUtils.calculateScopeSuffix();
		new SpringApplicationBuilder(SpringConfig.class).registerShutdownHook(true)
				.run(args);

	}
}
