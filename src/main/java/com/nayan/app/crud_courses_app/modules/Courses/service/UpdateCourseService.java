package com.nayan.app.crud_courses_app.modules.Courses.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nayan.app.crud_courses_app.modules.Courses.dto.CoursesDto;

@Service
public class UpdateCourseService {
        
    @Value("${host.api.crud.courses}")
    private String hostAPIcrudCourses;

    public void execute(CoursesDto coursesDto, String courseId){

        
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity <CoursesDto> request = new HttpEntity<>(coursesDto, headers);
        
        var url = hostAPIcrudCourses.concat("/courses/").concat(courseId);

        var result = restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

        System.out.println(result);
    }

}
