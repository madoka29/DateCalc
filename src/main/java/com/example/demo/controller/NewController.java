package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.BaseDate;
import com.example.demo.domain.DateCalcData;
import com.example.demo.domain.DateForm;
import com.example.demo.service.DateCalcService;

/**
 * 新規登録画面用Contoroller
 *
 * @author mnomura
 *
 */

@Controller
@RequestMapping("new")
public class NewController {

    @Autowired
    DateCalcService dateCalcService;

    /**
     * 登録ページ
     * @param model
     * @return
     */
    @GetMapping
    public String newDate(Model model) {
        model.addAttribute("newForm", new DateForm());
        return "newForm";
    }

    /**
     * フォームの値を登録メソッドに渡す
     */
    @PostMapping
    public String insert(DateForm newForm, Model model) {
        DateCalcData calcData = new DateCalcData();
        calcData.setDateName(newForm.getDateName());
        calcData.setPlusLessYear(newForm.getPlusLessYear());
        calcData.setPlusLessMonth(newForm.getPlusLessMonth());
        calcData.setPlusLessDay(newForm.getPlusLessDay());

        dateCalcService.dateCalcInsert(calcData);
        model.addAttribute("baseDate", new BaseDate());
        return "index";
    }
}
