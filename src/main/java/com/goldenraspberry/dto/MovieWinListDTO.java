package com.goldenraspberry.dto;

import java.util.ArrayList;
import java.util.List;

public class MovieWinListDTO {

	private List<MovieWinDTO> min;
	private List<MovieWinDTO> max;

	public List<MovieWinDTO> getMin() {
		return min == null ? new ArrayList<MovieWinDTO>(): min;
	}

	public void setMin(List<MovieWinDTO> min) {
		this.min = min;
	}

	public List<MovieWinDTO> getMax() {
		return max == null? new ArrayList<MovieWinDTO>() : max;
	}

	public void setMax(List<MovieWinDTO> max) {
		this.max = max;
	}

}
