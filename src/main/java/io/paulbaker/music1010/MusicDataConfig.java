package io.paulbaker.music1010;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Created by paul on 10/13/15.
 */
@Configuration
@EnableBatchProcessing
public class MusicDataConfig {

  @Value("classpath:question-answers.csv")
  private Resource musicCsvResource;

  @Bean
  public LineMapper<MusicalFactoid> musicalFactoidLineMapper() {
    DefaultLineMapper<MusicalFactoid> musicalFactoidDefaultLineMapper = new DefaultLineMapper<>();
    musicalFactoidDefaultLineMapper.setLineTokenizer(new DelimitedLineTokenizer() {{
      setDelimiter(";");
      setNames(new String[]{"question", "answer"});
    }});
    musicalFactoidDefaultLineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<MusicalFactoid>() {{
      setTargetType(MusicalFactoid.class);
    }});
    return musicalFactoidDefaultLineMapper;
  }

  @Bean
  public FlatFileItemReader<MusicalFactoid> musicData(LineMapper<MusicalFactoid> musicalFactoidLineMapper) {
    FlatFileItemReader<MusicalFactoid> itemReader = new FlatFileItemReader<>();
    itemReader.setResource(musicCsvResource);
    itemReader.setLineMapper(musicalFactoidLineMapper);
    return itemReader;
  }



}
