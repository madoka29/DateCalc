package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.BaseDate;
import com.example.demo.domain.Result;
import com.example.demo.service.DateCalcService;

/**
 * Top画面用Controller
 * @author tomat
 *
 */

@Controller
@RequestMapping
public class TopController {

    @Autowired
    DateCalcService dateCalcService;

    /**
     * TOPページ
     */
    @GetMapping
    public String index(@ModelAttribute BaseDate baseDate) {
        return "index";
    }

    /**
     * 基準日を計算する式へと渡す
     */
    @PostMapping
    public String calculate(Model model, @ModelAttribute BaseDate baseDate) throws Exception {
        List<Result> results = dateCalcService.calcFormBaseDate(baseDate.getBaseDate());
        model.addAttribute("results", results);
        return "index";
    }

    /**
     * データを削除する
     */
    @GetMapping(value="delete/{dateId}")
    public String delete(@PathVariable("dateId") int dateId, Model model) {
        dateCalcService.dateCalcDelete(dateId);
        model.addAttribute("baseDate", new BaseDate());
        return "index";
    }
}
