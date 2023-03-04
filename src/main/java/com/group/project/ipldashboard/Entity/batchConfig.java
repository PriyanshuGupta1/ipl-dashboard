package com.group.project.ipldashboard.Entity;
import com.group.project.ipldashboard.Model.Match;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class batchConfig {
    private final String[] Field_names=new String[]{
        "ID","City","Date","Season","MatchNumber","Team1",
        "Team2","Venue","TossWinner","TossDecision","SuperOver",
        "WinningTeam","WonBy","Margin","method","Player_of_Match",
        "Team1Players","Team2Players","Umpire1","Umpire2"
    };
    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>()
                .name("personItemReader")
                .resource(new ClassPathResource("matches.csv"))
                .delimited()
                .names(Field_names)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {{
                    setTargetType(MatchInput.class);
                }})
                .build();
    }

    @Bean
    public MatchDataProcessor processor() {
        return new MatchDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO match (id,city,date,season,match_number,team1,team2,venue,toss_winner,toss_decision,super_over,winning_team,won_by,result_margin,player_of_match,umpire1,umpire2) "
                        +"VALUES (:id,:city,:date,:season,:matchNumber,:team1,:team2,:venue,:tossWinner,:tossDecision,:superOver,:winningTeam,:wonBy,:resultMargin,:playerOfMatch,:umpire1,:umpire2)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, JobCompletionNotificationListener listener, Step step1) {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, JdbcBatchItemWriter<Match> writer) {
        return new StepBuilder("step1", jobRepository)
                .<MatchInput, Match> chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}




