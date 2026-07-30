package com.ssafy.trip.model.dao;

import java.util.*;

import com.ssafy.trip.model.dto.FestivalDto;

public class FestivalDaoImpl implements FestivalDao {

	private List<FestivalDto> festivalInfo;

	public FestivalDaoImpl() {
		festivalInfo = new ArrayList<>();
		loadData();
	}

	@Override
	public void loadData() {
		// Todo : 축제 파서 연동
//		FestivalSAXParser parser = new FestivalSAXParser();
//		festivalInfo = parser.getFestivalInfo();

		
		festivalInfo.add(new FestivalDto(1, "ㄴㄴ", "서울", "ㄴㄴ", "ㅇㅇ"));
	}

	@Override
	public List<FestivalDto> searchAll(String cityName) {

		return festivalInfo.stream().filter(item -> item.getCity().equals(cityName)).toList();
	}

}
