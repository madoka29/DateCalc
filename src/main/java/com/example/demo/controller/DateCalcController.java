package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.DateCalcData;
import com.example.demo.domain.DateForm;
import com.example.demo.domain.StandardDate;
import com.example.demo.service.DateCalcService;

//画面と処理の橋渡しをするController

@Controller
@RequestMapping
public class DateCalcController {

    @Autowired
    DateCalcService dateCalcService;

    /**
     * TOPページ
     */
    @GetMapping("/")
    public String index(Model model, @ModelAttribute("modelMap") ModelMap modelMap) {
        List<DateCalcData> dcd = dateCalcService.dateCalcSelectAll();
        model.addAttribute("dateCalcDataList", dcd);
        model.addAttribute("StandardDate", new StandardDate());
        model.addAttribute("resultList", modelMap.get("resultList"));
        return "index";
    }

    /**
     * 基準日を計算する式へと渡す
     */
    @PostMapping("calculate")
    public String calculate(Model model, @ModelAttribute StandardDate standardDate, RedirectAttributes redirectAttributes) throws Exception {
        List<Map<String, Object>> resultList = dateCalcService.calcDateFromStandardDate(standardDate.getStandardDate());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("resultList", resultList);
        redirectAttributes.addFlashAttribute("modelMap", modelMap);
        return "redirect:/";
    }

    /**
     * 登録ページ
     * @param model
     * @return
     */
    @GetMapping("new")
    public String newDate(Model model) {
        model.addAttribute("NewForm", new DateForm());
        return "newForm";
    }

    /**
     * フォームの値を登録メソッドに渡す
     */
    @PostMapping("create")
    public String insert(DateForm newForm, Model model) {
        DateCalcData calcData = new DateCalcData();
        calcData.setDateName(newForm.getDateName());
        calcData.setPlusLessYear(newForm.getPlusLessYear());
        calcData.setPlusLessMonth(newForm.getPlusLessMonth());
        calcData.setPlusLessDay(newForm.getPlusLessDay());

        dateCalcService.dateCalcInsert(calcData);
        return "redirect:/";
    }

    /**
     * 更新ページ
     */
    @GetMapping(path="update/{dateId}")
    public String updateDate(@PathVariable("dateId")int dateId, Model model, @ModelAttribute DateForm updateForm) {
        DateCalcData calcData = dateCalcService.dateCalcSelectOne(dateId);
        model.addAttribute("dateCalcData", calcData);
        updateForm.setDateId(dateId);
        updateForm.setDateName(calcData.getDateName());
        updateForm.setPlusLessYear(calcData.getPlusLessYear());
        updateForm.setPlusLessMonth(calcData.getPlusLessMonth());
        updateForm.setPlusLessDay(calcData.getPlusLessDay());
        model.addAttribute("UpdateForm", updateForm);
        return "updateForm";
    }

    /**
     * フォームの値を更新メソッドに渡す
     */
    @PostMapping("update")
    public String update(DateForm updateForm, Model model) {
        DateCalcData calcData = new DateCalcData();
        calcData.setDateId(updateForm.getDateId());
        calcData.setDateName(updateForm.getDateName());
        calcData.setPlusLessYear(updateForm.getPlusLessYear());
        calcData.setPlusLessMonth(updateForm.getPlusLessMonth());
        calcData.setPlusLessDay(updateForm.getPlusLessDay());

        dateCalcService.dateCalcUpdate(calcData);

        return "redirect:/";
    }

    /**
     * データを削除する
     */
    @GetMapping(path="delete/{dateId}")
    public String delete(@PathVariable("dateId") int dateId, Model model) {
        dateCalcService.dateCalcDelete(dateId);
        return "redirect:/";
    }
}
