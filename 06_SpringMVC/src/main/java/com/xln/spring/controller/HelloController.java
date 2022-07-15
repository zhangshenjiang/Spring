package com.xln.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    /**
     * 控制器中的方法hello
     *      通过sayhello访问
     * @return
     */
    @RequestMapping("hello")
    public ModelAndView hello(){

        ModelAndView modelAndView = new ModelAndView();
        /**
         * 设置模型中的数据对象，在视图中可通过属性名称访问
         */
        modelAndView.addObject("msg","Hello Spring MVC!");

        /**
         * 设置视图名称
         */
        modelAndView.setViewName("sayhello");
        return  modelAndView;
    }
}
