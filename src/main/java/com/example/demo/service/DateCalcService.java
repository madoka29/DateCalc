package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.DateCalcData;
import com.example.demo.domain.DateCalcMapper;
import com.example.demo.domain.Result;

// 計算処理を行うService

@Service
public class DateCalcService implements DateCalcMapper {

    @Autowired
    DateCalcMapper dateCalcMapper;

    /**
     * データベースから1件取得
     */
    @Override
    public DateCalcData dateCalcSelectOne(int dateId) {
        return dateCalcMapper.dateCalcSelectOne(dateId);
    }

    /**
     * データベースから全件取得
     */
    @Override
    public List<DateCalcData> dateCalcSelectAll() {
        return dateCalcMapper.dateCalcSelectAll();
    }

    /**
     * データベースから日付計算式を取得して、日付基準日をもとに計算して結果を返す
     */
    public List<Result> calcFormBaseDate(String standardDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date time = sdf.parse(standardDate);
        LocalDateTime standardCalcDate = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());

        List<Result> results = new ArrayList<Result>();
        List<DateCalcData> calcDataList = dateCalcMapper.dateCalcSelectAll();

        for(DateCalcData calcData : calcDataList) {
            Result result = new Result();

            // 日付基準日をもとに日付計算
            LocalDateTime resultDate =
                    standardCalcDate.plusYears(calcData.getPlusLessYear())
                    .plusMonths(calcData.getPlusLessMonth())
                    .plusDays((calcData.getPlusLessDay()));
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            result.setCalcData(calcData);
            result.setCalcResult(resultDate.format(dateTimeFormatter));
            results.add(result);
        }

        return results;
    }

    /**
     * データベースに登録する
     */
    @Override
    public int dateCalcInsert(DateCalcData dcd) {
        return dateCalcMapper.dateCalcInsert(dcd);
    }

    /**
     * データベースを更新する
     */
    @Override
    public int dateCalcUpdate(DateCalcData dcd) {
        return dateCalcMapper.dateCalcUpdate(dcd);
    }

    /**
     * データベースから1件削除する
     */
    @Override
    public boolean dateCalcDelete(int dateId) {
        return dateCalcMapper.dateCalcDelete(dateId);
    }
}
