/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thelogicals;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author prawal
 */
@Controller
@RequestMapping("/account")
public class DashboardController {

    @RequestMapping("/dashboard")
    public ModelAndView welcomePage() {
        System.out.println("welcomePage Called");
        ModelAndView model = new ModelAndView();
        model.setViewName("SPA");
        return model;

    }
    @RequestMapping()
    public ModelAndView defaultPage() {
        System.out.println("welcomePage Called");
        ModelAndView model = new ModelAndView();
        model.setViewName("SPA");
        return model;

    }

    @RequestMapping("/account/index.html")
    public ModelAndView accountPage() {
        System.out.println("Account Called");
        ModelAndView model = new ModelAndView();
        model.setViewName("account");
        return model;

    }

    @RequestMapping("/Home")
    public ModelAndView homePage() {
        System.out.println("Home Called");
        ModelAndView model = new ModelAndView();
        model.setViewName("Home");
        return model;

    }
    @RequestMapping("/Exam")
    public ModelAndView examPage() {
        System.out.println("Exam Called");
        ModelAndView model = new ModelAndView();
        model.setViewName("Exam");
        return model;

    }
    @RequestMapping("/MyQuestions")
    public ModelAndView myQuestionsPage() {
        System.out.println("My Questions Called");
        ModelAndView model = new ModelAndView();
        model.setViewName("MyQuestions");
        return model;

    }

}
