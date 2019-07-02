package com.example.demo.domain;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

// オブジェクトとデータベースを紐づける役割を持つMapper

@Mapper
public interface DateCalcMapper {

    /**
     * 日付計算用データを検索して1件取得する
     */
    @Select("SELECT * FROM date_calc WHERE dateId = #{dateId}")
    public DateCalcData dateCalcSelectOne(int dateId);

    /**
     * 日付計算用データをすべて取得する
     */
    @Select("SELECT * FROM date_calc")
    public List<DateCalcData> dateCalcSelectAll();

    /**
     * 日付計算用データを登録する
     */
    @Insert("INSERT INTO date_calc(dateId, dateName, plusLessYear, plusLessMonth, plusLessDay) VALUES (null, #{dateName}, #{plusLessYear}, #{plusLessMonth}, #{plusLessDay});")
    public boolean dateCalcInsert(DateCalcData dcd);

    /**
     * 日付計算用データを更新する
     */
    @Update("UPDATE date_calc SET dateName = #{dateName}, plusLessYear = #{plusLessYear}, plusLessMonth = #{plusLessMonth}, plusLessDay = #{plusLessDay} WHERE dateId = #{dateId}")
    public boolean dateCalcUpdate(DateCalcData dcd);

    /**
     * 日付計算用データを削除する
     */
    @Delete("DELETE FROM date_calc WHERE dateId = #{dateId}")
    public boolean dateCalcDelete(int dateId);
}
