package sn.isi.batchms.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import sn.isi.batchms.entities.AppUser;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {


	@Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;
    @Autowired private ItemReader<AppUser> appUserItemReader;
    @Autowired private ItemWriter<AppUser> appUserItemWriter;
    @Autowired private ItemProcessor<AppUser, AppUser> appUserAppUserItemProcessor;

    @Bean
    public Job userJob() {
        Step step1 = stepBuilderFactory.get("step-load-data")
                .<AppUser,AppUser>chunk(10)
                .reader(appUserItemReader)
                .processor(appUserAppUserItemProcessor)
                .writer(appUserItemWriter)
                .build();
        return jobBuilderFactory.get("utilisateur-data-load")
                .start(step1).build();
    }

    @Bean
    public FlatFileItemReader<AppUser> getItemReader(@Value("${inputFile}") Resource inputFile) {
        FlatFileItemReader<AppUser> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("csvReader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(inputFile);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }
     @Bean
    public LineMapper<AppUser> lineMapper() {
        DefaultLineMapper<AppUser> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "nom", "prenom", "email", "password");
        lineMapper.setLineTokenizer(lineTokenizer);

        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(AppUser.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }


}
