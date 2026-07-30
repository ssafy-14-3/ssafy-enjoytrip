package com.ssafy.trip.model.dto;

import java.util.Objects;

public class FestivalDto {
	/** 연번 */
	private int num;
	/** 축제명 */
	private String festivalName;
	/** 시, 도 */
	private String city;
	/** 시작일 */
	private String startDate;
	/** 종료일 */
	private String endDate;
	
	public FestivalDto() {
		
	}
	

	public FestivalDto(int num, String festivalName, String city, String startDate, String endDate) {
		this.num = num;
		this.festivalName = festivalName;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getFestivalName() {
		return festivalName;
	}

	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(num));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FestivalDto other = (FestivalDto) obj;
		return num == other.num;
	}

	@Override
	public String toString() {
		return "FestivalDto [num=" + this.num 
				+ ", name=" + this.festivalName
				+ ", address=" + this.city
				+ ", startDate=" + this.startDate
				+ ", endDate=" + this.endDate + "]";
	}
}
