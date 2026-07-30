package com.ssafy.trip.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.trip.model.dto.FestivalDto;

public class TouristDestinationJSONParser {

	/** 축제 정보 JSON 파일 경로 */
	private static final String FESTIVAL_FILE_PATH = "res/2026_지역축제_개최계획.json";

	private List<FestivalDto> festivalInfo;
	private int size;

	private int num = 0;

	public TouristDestinationJSONParser() {
		this.loadData();

		festivalInfo.forEach(System.out::println);
	}


	private void loadData() {

		festivalInfo = new ArrayList<FestivalDto>();

		// try-with-resources: reader를 자동으로 close 해준다.
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(FESTIVAL_FILE_PATH), StandardCharsets.UTF_8))) {

			// 최상위가 배열([ ... ])이므로 JsonArray로 받는다.
			JsonArray records = JsonParser.parseReader(reader).getAsJsonArray();

			for (JsonElement element : records) {
				JsonObject record = element.getAsJsonObject();
				festivalInfo.add(parseFestival(record));
			}

			size = festivalInfo.size();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * JSON 레코드 하나를 FestivalDto로 변환한다.
	 */
	private FestivalDto parseFestival(JsonObject record) {

		FestivalDto festivalDto = new FestivalDto();

		festivalDto.setId(this.num++);

		festivalDto.setName(getString(record, "축제명"));

		JsonObject place = getObject(record, "개최장소");
		festivalDto.setAddress(getString(place, "장소명"));

		JsonObject period = getObject(record, "개최기간");
		festivalDto.setStartDate(getString(period, "시작일"));
		festivalDto.setEndDate(getString(period, "종료일"));

		return festivalDto;
	}

	private String makeAddress(JsonObject place) {
		if (place == null)
			return "";

		StringBuilder sb = new StringBuilder();
		appendIfPresent(sb, removeCode(getString(place, "시도")));
		appendIfPresent(sb, getString(place, "시군구"));
		appendIfPresent(sb, getString(place, "읍면동"));
		appendIfPresent(sb, getString(place, "장소명"));

		return sb.toString();
	}

	private void appendIfPresent(StringBuilder sb, String value) {
		if (value.isEmpty() || value.equals("-"))
			return;
		if (sb.length() > 0)
			sb.append(" ");
		sb.append(value);
	}

	/** "01. 서울" → "서울" */
	private String removeCode(String value) {
		int idx = value.indexOf(". ");
		return idx >= 0 ? value.substring(idx + 2) : value;
	}

	/**
	 * key가 없거나 값이 null인 경우에도 예외 없이 빈 문자열을 돌려준다.
	 */
	private String getString(JsonObject obj, String key) {
		if (obj == null)
			return "";
		JsonElement element = obj.get(key);
		if (element == null || element.isJsonNull())
			return "";
		return element.getAsString().trim();
	}

	/**
	 * 중첩 객체를 안전하게 꺼낸다.
	 */
	private JsonObject getObject(JsonObject obj, String key) {
		if (obj == null)
			return null;
		JsonElement element = obj.get(key);
		if (element == null || !element.isJsonObject())
			return null;
		return element.getAsJsonObject();
	}

	/**
	 * 숫자 항목을 안전하게 꺼낸다. (연번, 최초개최연도 등에 사용)
	 */
	@SuppressWarnings("unused")
	private int getInt(JsonObject obj, String key) {
		if (obj == null)
			return 0;
		JsonElement element = obj.get(key);
		if (element == null || element.isJsonNull())
			return 0;
		try {
			return element.getAsInt();
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public List<FestivalDto> getFestivalInfo() {
		return festivalInfo;
	}

	public void setFestivalInfo(List<FestivalDto> festivalInfo) {
		this.festivalInfo = festivalInfo;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public static void main(String[] args) {
		TouristDestinationJSONParser parser = new TouristDestinationJSONParser();
		System.out.println("총 축제 수 : " + parser.getSize());
	}
}