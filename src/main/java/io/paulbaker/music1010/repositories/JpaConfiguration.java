package io.paulbaker.music1010.repositories;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by paul on 10/14/15.
 */
@Configuration
@EnableJpaRepositories(
//  transactionManagerRef = WebDb
//  basePackageClasses = {
//    QuestionRepository.class
//  }
  basePackages = {"io.paulbaker.music1010.repositories"}
)
public class JpaConfiguration {
}
