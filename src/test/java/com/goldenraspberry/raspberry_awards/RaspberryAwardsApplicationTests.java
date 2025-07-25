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
		assertTrue(response.getBody() != null);
		
		MovieWinDTO expectedMinEntry = new MovieWinDTO();
		expectedMinEntry.setFollowingWin(2009);
		expectedMinEntry.setInterval(1);
		expectedMinEntry.setPreviousWin(2008);
		expectedMinEntry.setProducer("Producer 1");
		
		MovieWinDTO expectedMaxEntry = new MovieWinDTO();
		expectedMaxEntry.setFollowingWin(2010);
		expectedMaxEntry.setInterval(20);
		expectedMaxEntry.setPreviousWin(1990);
		expectedMaxEntry.setProducer("Producer 2");


        
        assertTrue(response.getBody().getMin().contains(expectedMinEntry));
        assertTrue(response.getBody().getMax().contains(expectedMaxEntry));

	}

}
