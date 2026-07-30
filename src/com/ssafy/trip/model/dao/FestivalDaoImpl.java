package com.ssafy.trip.model.dao;

import java.util.List;

import com.ssafy.trip.model.dto.FestivalDto;

public class FestivalDaoImpl implements FestivalDao {

	private List<FestivalDto> festivalInfo;

	public FestivalDaoImpl() {
		loadData();
	}

	@Override
	public void loadData() {
		// Todo : 축제 파서 연동
//		FestivalSAXParser parser = new FestivalSAXParser();
//		festivalInfo = parser.getFestivalInfo();

	}

	@Override
	public List<FestivalDto> searchAll(String cityName) {

		return festivalInfo.stream().filter(item -> item.getCity().equals(cityName)).toList();
	}

}
