package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.DateCalcData;

@Service
public class DateCalcService implements DateCalcMapper {

	@Autowired
	DateCalcMapper dateCalcMapper;

	@Override
	public boolean dateCalcInsert(DateCalcData dcd) {
		return dateCalcMapper.dateCalcInsert(dcd);
	}

	@Override
	public DateCalcData dateCalcSelectOne(int dateId) {
		return dateCalcMapper.dateCalcSelectOne(dateId);
	}
}
