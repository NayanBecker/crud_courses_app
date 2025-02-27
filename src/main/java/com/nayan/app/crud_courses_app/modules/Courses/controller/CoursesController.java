package com.nayan.app.crud_courses_app.modules.Courses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nayan.app.crud_courses_app.modules.Courses.dto.CoursesDto;
import com.nayan.app.crud_courses_app.modules.Courses.service.CreateCourseService;
import com.nayan.app.crud_courses_app.utils.FormatErrorMessage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;



@Controller
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CreateCourseService createCourseService;



    @PostMapping("/create")
    public String save(Model model, CoursesDto coursesDto) {
        try{
            this.createCourseService.execute(coursesDto);
        }
        catch(HttpClientErrorException e)
        {
            model.addAttribute("error_message", FormatErrorMessage.formatErrorMessage(e.getResponseBodyAsString()));
        }
        model.addAttribute("courses", coursesDto);
        return "courses/create";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("courses", new CoursesDto());
        return "courses/create";
    }
    
}
