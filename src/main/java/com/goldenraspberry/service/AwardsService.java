package com.goldenraspberry.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldenraspberry.domain.Movie;
import com.goldenraspberry.dto.MovieWinDTO;
import com.goldenraspberry.dto.MovieWinListDTO;
import com.goldenraspberry.repository.MovieRepository;

@Service
public class AwardsService {

	@Autowired
    private MovieRepository movieRepository;

    public MovieWinListDTO getAwardIntervals() {
        List<Movie> winners = movieRepository.findByWinnerTrue();
        Map<String, List<Integer>> producerWins = new HashMap<>();

        for (Movie movie : winners) {
            for (String producer : movie.getProducers().split(",| and ")) {
                producer = producer.trim();
                producerWins.computeIfAbsent(producer, k -> new ArrayList<>())
                		.add(movie.getReleaseYear());
            }
        }

        List<MovieWinDTO> intervals = new ArrayList<>();

        for (Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            List<Integer> years = entry.getValue().stream().sorted().toList();
            for (int i = 1; i < years.size(); i++) {
                int interval = years.get(i) - years.get(i - 1);
                MovieWinDTO dto = new MovieWinDTO();
                dto.setProducer(entry.getKey());
                dto.setInterval(interval);
                dto.setPreviousWin(years.get(i - 1));
                dto.setFollowingWin( years.get(i));
                intervals.add(dto);
            }
        }

        int min = intervals.stream().mapToInt(i -> i.getInterval()).min().orElse(0);
        int max = intervals.stream().mapToInt(i -> i.getInterval()).max().orElse(0);

        MovieWinListDTO response = new MovieWinListDTO();
        response.setMax(intervals.stream().filter(i -> i.getInterval() == max).toList());
        response.setMin(intervals.stream().filter(i -> i.getInterval() == min).toList());
        return response;
    }
}