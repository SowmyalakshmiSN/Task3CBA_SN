package com.cba.coivdgame.API_Regression.DTOs;

import java.util.HashMap;
import java.util.Map;


public class CovidgameApplication
{
	
	private Integer userId;
	private String username;
	private Integer score;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Integer getUserId() {
	return userId;
	}

	public void setUserId(Integer userId) {
	this.userId = userId;
	}

	public String getUsername() {
	return username;
	}

	public void setUsername(String username) {
	this.username = username;
	}

	public Integer getScore() {
	return score;
	}

	public void setScore(Integer score) {
	this.score = score;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}

}


