package com.glodon.mycommunity.controller;

import com.glodon.mycommunity.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;

@Controller
@RequestMapping("/alpha") //类的访问路径
public class AlphaController {

    @Autowired
    AlphaService alphaService;

    @RequestMapping("/data") //方法的访问路径
    @ResponseBody  //表示返回字符串而不是网页
    public String getData(){
        alphaService.find();
        return "hibernate";
    }

    @RequestMapping("/hello") //方法的访问路径
    @ResponseBody  //表示返回字符串而不是网页
    public String hello(){
        return "hello,Spring boot.";
    }

    @RequestMapping("http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> names = request.getHeaderNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            System.out.println(name+": "+request.getHeader(name));
        }
        System.out.println(request.getParameter("code"));

        //返回响应
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                writer.close();
            }
        }
    }

    //student?name=zhangsan&age=20
    @RequestMapping(path = "/student",method = RequestMethod.GET)
    @ResponseBody
    public String student(@RequestParam(name = "name",defaultValue = "张三") String name,
                          @RequestParam(name = "age",defaultValue = "25") int age){
        System.out.println(name + ": "+age);
        return "a student";
    }

    @RequestMapping(path = "/teacher/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String teacherById(@PathVariable(name = "id") String id){
        return id;
    }

    //提交表单
    @RequestMapping(path = "/saveStu",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name + ": "+age);
        return "success";
    }

    //返回动态网页
    @RequestMapping(path = "/dynamicTeacher",method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","里斯");
        modelAndView.addObject("age","23");
        modelAndView.addObject("salary","1000");
        modelAndView.setViewName("/demo/teacher.html");
        return modelAndView;
    }
    //返回动态网页
    @RequestMapping(path = "/dynamicStudent",method = RequestMethod.GET)
    public String getStudent(Model model){
        model.addAttribute("name","里斯");
        model.addAttribute("age","90");
        model.addAttribute("salary","1000");
        return "/demo/teacher";
    }

    //返回json(异步请求)
    @RequestMapping(path = "/json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Integer> getJson(){
        Map<String,Integer> map = new HashMap<>();
        map.put("zhangsan",23);
        map.put("lisi",78);
        return map;
    }

    @RequestMapping(path = "/jsons",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Integer>> getJsons(){
        List<Map<String,Integer>> list = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
        map.put("zhangsan",23);
        map.put("lisi",78);
        list.add(map);

        map = new HashMap<>();
        map.put("lisi",30);
        map.put("age",78);
        list.add(map);

        return list;
    }

}
