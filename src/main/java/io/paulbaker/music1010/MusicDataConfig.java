package io.paulbaker.music1010;

import io.paulbaker.music1010.entities.Answer;
import io.paulbaker.music1010.entities.Question;
import io.paulbaker.music1010.repositories.QuestionRepository;
import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by paul on 10/13/15.
 */
@Configuration
@EnableBatchProcessing
@EnableJpaRepositories(
//  transactionManagerRef = WebDbConfig.Names.TRANSACTION_MANAGER,
//  entityManagerFactoryRef = "myEmf", //WebDbConfig.Names.ENTITY_MANAGER_FACTORY,
  basePackageClasses = {
    QuestionRepository.class
  }
//  basePackages = {"io.paulbaker.music1010.repositories"}
)
public class MusicDataConfig {

  private Logger logger = Logger.getLogger(this.getClass());

  @Value("classpath:musicdata.csv")
  private Resource musicCsvResource;

  //  @Bean(name = "myEmf")
//  public EntityManagerFactory myEntityManagerFactory() {
////    return new EntityManagerFactory();
//    EntityManagerFactory nativeEntityManagerFactory = new LocalEntityManagerFactoryBean().getNativeEntityManagerFactory();
//    return nativeEntityManagerFactory;
//  }

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
      .setType(EmbeddedDatabaseType.HSQL)
//      .addScript("classpath:schema.sql")
//      .addScript("classpath:test-data.sql")
      .build();
  }

//  @Bean(name = "myEmf")
//  public EntityManagerFactory myEmf(DataSource dataSource) {
//    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//    entityManagerFactoryBean.setDataSource(dataSource);
//    entityManagerFactoryBean.setPersistenceUnitName(new PersistenceUnit());
//    return entityManagerFactoryBean.getObject();
//  }

  @Bean
  public LineMapper<Question> musicalFactoidLineMapper() {
    return (line, lineNumber) -> {
      String[] values = line.split(";");
      String questionString = values[0];
      List<Answer> answers = new ArrayList<>(values.length - 1);
      Question question = new Question(questionString, answers);
      for(int i = 1; i < values.length; i++) {
        Answer answer = new Answer(question, values[i]);
        answers.add(answer);
      }
      return question;
    };
  }

  @Bean
  public ItemReader<Question> musicReader(LineMapper<Question> musicalFactoidLineMapper) {
    FlatFileItemReader<Question> itemReader = new FlatFileItemReader<>();
    itemReader.setResource(musicCsvResource);
    itemReader.setLineMapper(musicalFactoidLineMapper);
    return itemReader;
  }

  @Bean
  public ItemWriter<Question> musicWriter(DataSource dataSource) {
//  public ItemWriter<Question> musicWriter(EntityManagerFactory entityManagerFactory) {
//    JpaItemWriter<Question> itemWriter = new JpaItemWriter<>();
//    itemWriter.setEntityManagerFactory(entityManagerFactory);
//    return itemWriter;
    JdbcBatchItemWriter<Question> itemWriter = new JdbcBatchItemWriter<>();
    itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Question>());
//    itemWriter.setSql("INSERT INTO music_facts (questions, answers) VALUES (:question, :answer)");
    itemWriter.setSql("INSERT INTO music_questions (question) VALUES (:question)");
    itemWriter.setSql("INSERT INTO music_facts (questions, answers) VALUES (:question, :answer)");
    itemWriter.setDataSource(dataSource);
    return itemWriter;
  }

  @Bean
  public Step step1(StepBuilderFactory stepFactory, ItemReader<Question> itemReader, ItemWriter<Question> itemWriter) {
    return stepFactory.get("step1").<Question, Question>chunk(10)
      .reader(itemReader).writer(itemWriter).build();
  }

  @Bean
  public Job importUserJob(JobBuilderFactory jobs, Step step1, JobExecutionListener listener) {
    return jobs.get("importMusicFactsJob")
      .incrementer(new RunIdIncrementer()).listener(listener).flow(step1).end().build();
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public JobExecutionListener listener(final JdbcTemplate jdbcTemplate) {
    return new JobExecutionListener() {
      @Override
      public void beforeJob(JobExecution jobExecution) {
        logger.info("STARTING JOB:: " + jobExecution.getJobConfigurationName());
      }

      @Override
      public void afterJob(JobExecution jobExecution) {
        logger.info("ENDING JOB:: " + jobExecution.getJobConfigurationName());
//        jdbcTemplate.query("select * from music_facts", new RowMapper<Question>() {
//          @Override
//          public Question mapRow(ResultSet resultSet, int i) throws SQLException {
//
//            return null;
//          }
//        });
      }
    };
  }

}
