package com.ssafy.trip.model.dto;

public class FestivalDto {
	private int id;
	
	private String name;
	
	private String address;
 
	private String startDate;
	
	private String endDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	public String toString() {
		return "FestivalDto [num=" + this.id 
				+ ", name=" + this.name
				+ ", address=" + this.address
				+ ", startDate=" + this.startDate
				+ ", endDate=" + this.endDate + "]";
	}
}
