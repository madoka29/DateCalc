package com.example.demo.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.DateCalcData;

// Mapper: オブジェクトとデータベースを紐づける役割
@Mapper
public interface DateCalcMapper {

	// 日付計算用データを登録する
	@Insert("INSERT INTO date_calc(dateId, dateName, plusLessYear, plusLessMonth, plusLessDay) VALUES (#{dateId}, #{dateName}, #{plusLessYear}, #{plusLessMonth}, #{plusLessDay});")
	public boolean dateCalcInsert(DateCalcData dcd);

	// 日付計算用データを検索して1件取得する
	@Select("SELECT * FROM date_calc WHERE dateId = #{dateId}")
	public DateCalcData dateCalcSelectOne(int dateId);
}
