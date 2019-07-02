package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.DateCalcData;
import com.example.demo.domain.DateCalcMapper;

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
    public List<Map<String, Object>> calcDateFromStandardDate(String standardDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date time = sdf.parse(standardDate);
        LocalDateTime standardCalcDate = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());

        List<Map<String, Object>> resultList = new ArrayList<>();
        List<DateCalcData> calcDataList = dateCalcMapper.dateCalcSelectAll();

        for(DateCalcData calcData : calcDataList) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            LocalDateTime resultDate =
                    standardCalcDate.plusYears(calcData.getPlusLessYear())
                    .plusMonths(calcData.getPlusLessMonth())
                    .plusDays((calcData.getPlusLessDay()));
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            resultMap.put("resultCalc", resultDate.format(dateTimeFormatter));
            resultMap.put("dateCalcData", calcData);
            resultList.add(resultMap);
        }

        return resultList;
    }

    /**
     * データベースに登録する
     */
    @Override
    public boolean dateCalcInsert(DateCalcData dcd) {
        return dateCalcMapper.dateCalcInsert(dcd);
    }

    /**
     * データベースを更新する
     */
    @Override
    public boolean dateCalcUpdate(DateCalcData dcd) {
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
