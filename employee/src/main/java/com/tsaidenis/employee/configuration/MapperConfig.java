package com.tsaidenis.employee.configuration;

import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.MapperFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
public class MapperConfig implements OrikaMapperFactoryConfigurer {
    @Override
    public void configure(@NotNull MapperFactory orikaMapperFactory) {

    }
}
