package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.DateCalcData;
import com.example.demo.domain.DateForm;
import com.example.demo.service.DateCalcService;

//画面と処理の橋渡しをするController

@Controller
public class DateCalcController {

    @Autowired
    DateCalcService dateCalcService;

    @RequestMapping("/")
    public String index(Model model) {
        List<DateCalcData> dcd = dateCalcService.dateCalcSelectAll();
        model.addAttribute("dateCalcDataList", dcd);

        return "index";
    }

    /**
     * 登録ページ
     * @param model
     * @return
     */
    @RequestMapping("new")
    public void newDate(Model model) {
        model.addAttribute("DateForm", new DateForm());
    }

    /**
     * フォームの値を登録メソッドに渡す
     */
    @RequestMapping("create")
    public String insert(DateForm dateForm, Model model) {
        DateCalcData dcd = new DateCalcData();
        dcd.setDateName(dateForm.getDateName());
        dcd.setPlusLessYear(dateForm.getPlusLessYear());
        dcd.setPlusLessMonth(dateForm.getPlusLessMonth());
        dcd.setPlusLessDay(dateForm.getPlusLessDay());

        dateCalcService.dateCalcInsert(dcd);
        return "redirect:/";
    }

    /**
     * 更新ページ
     * 初期値としてフォームにデータベースへ登録してある値をいれたい
     * @param model
     */
    @RequestMapping(path="update/{dateId}", method = RequestMethod.GET)
    public String updateDate(@PathVariable("dateId")int dateId, Model model, @ModelAttribute DateForm updateForm) {
        DateCalcData dcd = dateCalcService.dateCalcSelectOne(dateId);
        model.addAttribute("dateCalcData", dcd);
        updateForm.setDateId(dateId);
        updateForm.setDateName(dcd.getDateName());
        updateForm.setPlusLessYear(dcd.getPlusLessYear());
        updateForm.setPlusLessMonth(dcd.getPlusLessMonth());
        updateForm.setPlusLessDay(dcd.getPlusLessDay());
        model.addAttribute("UpdateForm", updateForm);
        return "updateForm";
    }

    /**
     * フォームの値を更新メソッドに渡す
     */
    @RequestMapping(path="update", method=RequestMethod.POST)
    public String update(DateForm updateForm, Model model) {
        DateCalcData dcd = new DateCalcData();
        dcd.setDateId(updateForm.getDateId());
        dcd.setDateName(updateForm.getDateName());
        dcd.setPlusLessYear(updateForm.getPlusLessYear());
        dcd.setPlusLessMonth(updateForm.getPlusLessMonth());
        dcd.setPlusLessDay(updateForm.getPlusLessDay());

        dateCalcService.dateCalcUpdate(dcd);

        return "redirect:/";
    }

    /**
     * データを削除する
     */
    @RequestMapping(path="delete/{dateId}", method = RequestMethod.GET)
    public String delete(@PathVariable("dateId") int dateId, Model model) {
        dateCalcService.dateCalcDelete(dateId);
        return "redirect:/";
    }
}
