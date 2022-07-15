package com.xln.spring.controller;

import com.xln.spring.po.User;
import com.xln.spring.po.UserWithPhones;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("rm")
public class RequestMapController {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
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

    /**
     * 如果参数类型为基本类型，需要设置默认值；包装类型则不需要。
     * name/value属性设置参数的别名，也就是request使用的名称，解决请求参数名同实际参数名不一致问题
     * required属性设置参数是否为必须项，default属性设置参数的默认值
     * @param name ：对象类型参数
     * @param age : 基本类型参数
     * @param hobby : 数组类型参数
     */
    @RequestMapping(value = "baseparam",method = RequestMethod.GET)
    public ModelAndView baseparam(@RequestParam(name = "u_name",required=true) String name,
                                  @RequestParam(defaultValue="20") int age,
                                  String[]  hobby){
        ModelAndView modelAndView = new ModelAndView();
        String u_hobby=" Hobby:";
        for (String val:hobby) {
            u_hobby+=val;
            u_hobby+=",";
        }
        modelAndView.addObject("msg",name+":" + age + u_hobby);
        modelAndView.setViewName("sayhello");
        return  modelAndView;
    }

    /**
     * 参数类型为用户定义对象类型
     * @param user ：用户定义对象类型参数
     */
    @RequestMapping(value = "objectparam",method = RequestMethod.GET)
    public ModelAndView objecparam(User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg",user.toString());
        modelAndView.setViewName("sayhello");
        return  modelAndView;
    }

    /**
     * 参数类型为用户定义对象类型
     * @param user ：用户定义对象类型参数
     */
    @RequestMapping(value = "userinfo",method = RequestMethod.POST)
    public ModelAndView objectParamWithList(UserWithPhones user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg",user.toString());
        modelAndView.setViewName("sayhello");
        return  modelAndView;
    }
}
