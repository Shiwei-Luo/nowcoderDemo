package com.nowcoder.community.controller;


import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot!";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getdat() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {//不需要返回，原因是要显示内容通过response返回
        // 获取请求数据
        System.out.println(request.getMethod());//请求行
        System.out.println(request.getServletPath());//请求行
        Enumeration<String> enumeration = request.getHeaderNames();//请求头,返回key-value结构，用迭代器遍历
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name +  ":" + value);
        }
        System.out.println(request.getParameter("code"));//请求体

        // 返回相应数据
        response.setContentType("text/html;charset=utf-8");
        try (//java7后在try后放置小括号()，编译后能够自动增加finally close操作，前提是类的对象必须自带close方法
            PrintWriter writer = response.getWriter();
            ) {
            writer.write("<h1>nowcoder</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GET请求,
    // GET请求传参是显示传参，并且参数长度有限制

    // /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // /students/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    // POST
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "limbo");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");
        return mav;
    }

    //
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name","SEU");
        model.addAttribute("age", 20);
        return "/demo/view";
    }

    // 相应JSON数据（异步请求）
    // Java对象 -> JSON字符串 -> JS对象

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "ink");
        emp.put("age", 2);
        emp.put("salary", 12314142);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "ink");
        emp.put("age", 2);
        emp.put("salary", 12314142);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "ank");
        emp.put("age", 3);
        emp.put("salary", 33333342);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "unk");
        emp.put("age", 5);
        emp.put("salary", 44444442);
        list.add(emp);

        return list;
    }



}
