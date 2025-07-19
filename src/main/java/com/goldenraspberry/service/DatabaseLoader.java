package com.goldenraspberry.service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.goldenraspberry.domain.Movie;
import com.goldenraspberry.repository.MovieRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);
	@Autowired
	private MovieRepository repository;

	@Override
	public void run(String... args) throws Exception {
		ClassPathResource resource = new ClassPathResource("movielist.csv");
		try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
			int count = 0;
			CSVFormat csvFormat = CSVFormat.Builder.create().setDelimiter(';')
					.setQuote('"')
					.setHeader()
					.setSkipHeaderRecord(true)
					.build();
            Iterable<CSVRecord> records = csvFormat.parse(reader);
            for (CSVRecord record : records) {
    			Movie movie = new Movie();

    			movie.setReleaseYear(Integer.parseInt(record.get("year")));
    			movie.setTitle(record.get("title"));

    			movie.setStudios(record.isMapped("studios") && record.isSet("studios") && !record.get("studios").isBlank()
    					? record.get("studios") : null);

    			movie.setProducers(record.isMapped("producers") && record.isSet("producers") && !record.get("producers").isBlank()
    					? record.get("producers") : null);

    			movie.setWinner(record.isMapped("winner") && record.isSet("winner") &&
    					"yes".equalsIgnoreCase(record.get("winner")));

    			repository.save(movie);
    			count++;
    		}
			logger.info("{} registros foram carregados do CSV para o banco de dados.", count);
		} catch (Exception e) {
			logger.error("Erro ao carregar dados do CSV: {}", e.getMessage(), e);
		}
	}
}