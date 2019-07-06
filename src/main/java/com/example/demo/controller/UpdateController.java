package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.BaseDate;
import com.example.demo.domain.DateCalcData;
import com.example.demo.domain.DateForm;
import com.example.demo.service.DateCalcService;

/**
 * 更新画面用Contoroller
 *
 * @author mnomura
 *
 */

@Controller
@RequestMapping("update")
public class UpdateController {

    @Autowired
    DateCalcService dateCalcService;

    /**
     * 更新ページ
     */
    @GetMapping("/{dateId}")
    public String updateDate(@PathVariable("dateId")int dateId, Model model, @ModelAttribute DateForm updateForm) {
        DateCalcData calcData = dateCalcService.dateCalcSelectOne(dateId);
        model.addAttribute("dateCalcData", calcData);
        updateForm.setDateId(dateId);
        updateForm.setDateName(calcData.getDateName());
        updateForm.setPlusLessYear(calcData.getPlusLessYear());
        updateForm.setPlusLessMonth(calcData.getPlusLessMonth());
        updateForm.setPlusLessDay(calcData.getPlusLessDay());
        model.addAttribute("updateForm", updateForm);
        return "updateForm";
    }

    /**
     * フォームの値を更新メソッドに渡す
     */
    @PostMapping
    public String update(DateForm updateForm, Model model) {
        DateCalcData calcData = new DateCalcData();
        calcData.setDateId(updateForm.getDateId());
        calcData.setDateName(updateForm.getDateName());
        calcData.setPlusLessYear(updateForm.getPlusLessYear());
        calcData.setPlusLessMonth(updateForm.getPlusLessMonth());
        calcData.setPlusLessDay(updateForm.getPlusLessDay());

        dateCalcService.dateCalcUpdate(calcData);
        model.addAttribute("baseDate", new BaseDate());
        return "index";
    }

}
