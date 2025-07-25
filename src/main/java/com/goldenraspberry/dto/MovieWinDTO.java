package com.goldenraspberry.dto;

import java.util.Objects;

public class MovieWinDTO {

	private Integer followingWin;
	private String producer;
	private Integer interval;
	private Integer previousWin;

	public Integer getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(Integer followingWin) {
		this.followingWin = followingWin;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Integer getPreviousWin() {
		return previousWin;
	}

	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieWinDTO that = (MovieWinDTO) o;
        return Objects.equals(followingWin, that.followingWin) &&
               Objects.equals(producer, that.producer) &&
               Objects.equals(interval, that.interval) &&
               Objects.equals(previousWin, that.previousWin);
    }

}
