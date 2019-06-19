package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.DateCalcData;
import com.example.demo.domain.DateCalcMapper;

// 計算処理を行うService

@Service
public class DateCalcService implements DateCalcMapper {

    @Autowired
    DateCalcMapper dateCalcMapper;

    @Override
    public DateCalcData dateCalcSelectOne(int dateId) {
        return dateCalcMapper.dateCalcSelectOne(dateId);
    }


    @Override
    public List<DateCalcData> dateCalcSelectAll() {
        return dateCalcMapper.dateCalcSelectAll();
    }

    @Override
    public boolean dateCalcInsert(DateCalcData dcd) {
        return dateCalcMapper.dateCalcInsert(dcd);
    }

    @Override
    public boolean dateCalcUpdate(DateCalcData dcd) {
        return dateCalcMapper.dateCalcUpdate(dcd);
    }

    @Override
    public boolean dateCalcDelete(int dateId) {
        return dateCalcMapper.dateCalcDelete(dateId);
    }
}
