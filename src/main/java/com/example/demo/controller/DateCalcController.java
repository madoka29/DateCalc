package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.DateCalcData;
import com.example.demo.service.DateCalcService;

@Controller
public class DateCalcController {

	@Autowired
	DateCalcService dateCalcService;

	@RequestMapping
	public String index(Model model) {
		int num = 1;
		DateCalcData dcd = dateCalcService.dateCalcSelectOne(num);
		model.addAttribute("dateCalcData", dcd);
		return "index";
	}
}
