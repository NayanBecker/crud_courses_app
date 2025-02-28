package com.nayan.app.crud_courses_app.modules.Courses.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.nayan.app.crud_courses_app.modules.Courses.dto.CoursesDto;

@Service
public class GetCourseDetailsService {

    @Value("${host.api.crud.courses}")
    private String hostAPIcrudCourses;

    public CoursesDto execute(String courseId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        
        HttpEntity<String> request = new HttpEntity<>(headers);
        
        var url = hostAPIcrudCourses.concat("/courses/").concat(courseId);

        ParameterizedTypeReference<CoursesDto> responseType = new ParameterizedTypeReference<CoursesDto>() {
        };

        try {
            var result = restTemplate.exchange(url, HttpMethod.GET, request, responseType);
            return result.getBody();  
        } catch (Exception e) {
            System.out.println("Error fetching course details: " + e.getMessage());
            return null; 
        }
    }
}
