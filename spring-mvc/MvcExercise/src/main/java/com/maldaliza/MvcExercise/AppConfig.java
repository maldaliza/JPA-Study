package com.maldaliza.MvcExercise;

import com.maldaliza.MvcExercise.repository.ItemRepository;
import com.maldaliza.MvcExercise.repository.implementation.ItemRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ItemRepository itemRepository() {
        return new ItemRepositoryImpl();
    }
}
