package com.goldenraspberry.raspberry_awards;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.goldenraspberry.dto.MovieWinDTO;
import com.goldenraspberry.dto.MovieWinListDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RaspberryAwardsApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldReturnMinAndMaxAwardIntervals() {
		String url = "http://localhost:" + port + "/awards/intervals";
		ResponseEntity<MovieWinListDTO> response = restTemplate.getForEntity(url, MovieWinListDTO.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertNotNull(response.getBody());

		List<MovieWinDTO> minList = response.getBody().getMin();
		List<MovieWinDTO> maxList = response.getBody().getMax();

		assertTrue(!minList.isEmpty());
		assertTrue(!maxList.isEmpty());
	}

}
