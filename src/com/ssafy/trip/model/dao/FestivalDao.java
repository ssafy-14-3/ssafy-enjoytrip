package com.ssafy.trip.model.dao;

import java.util.List;

import com.ssafy.trip.model.dto.FestivalDto;

public interface FestivalDao {

	/**
	 * 축제 정보를 json 파일에서 로딩하는 기능
	 */
	public void loadData();

	/**
	 * 검색 광역자치단체(word)에 해당하는 축제 정보(FestivalDto)를 검색해서 반환.
	 * 
	 * @param cityName 광역자치단체명 앞 2글자
	 * @return 조회한 축제 목록
	 */
	public List<FestivalDto> searchAll(String cityName);

}
