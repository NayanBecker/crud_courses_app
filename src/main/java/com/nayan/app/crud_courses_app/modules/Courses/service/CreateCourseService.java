package com.nayan.app.crud_courses_app.modules.Courses.service;

import com.nayan.app.crud_courses_app.modules.Courses.dto.CoursesDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CreateCourseService {
    
    @Value("${host.api.crud.courses}")
    private String hostAPIcrudCourses;

    public void execute(CoursesDto coursesDto){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity <CoursesDto> request = new HttpEntity<>(coursesDto, headers);

        var url = hostAPIcrudCourses.concat("/courses/");
        var result = restTemplate.postForObject(url, request, String.class);

        System.out.println(result);
    }
}
