package com.goldenraspberry.controller;

import com.goldenraspberry.dto.MovieWinListDTO;
import com.goldenraspberry.service.AwardsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/awards")
public class AwardsController {

	@Autowired
    private AwardsService awardsService;


    @GetMapping("/intervals")
    public MovieWinListDTO getAwardIntervals() {
        return awardsService.getAwardIntervals();
    }
}