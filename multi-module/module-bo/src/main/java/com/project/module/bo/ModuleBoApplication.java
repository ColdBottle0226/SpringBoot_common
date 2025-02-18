package com.project.module.bo;

import com.project.module.common.core.config.BeanNameConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(nameGenerator = BeanNameConfig.class,
		basePackages = {"com.project.module.common", "com.project.module.bo"})
public class ModuleBoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuleBoApplication.class, args);
	}

}
