package com.nayan.app.crud_courses_app.modules.Courses.service;

import java.util.List;
import java.util.Map;

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
public class ListAllCursesService {
    
    @Value("${host.api.crud.courses}")
    private String hostAPIcrudCourses;

    public List<CoursesDto> execute(String filter){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();


        HttpEntity<Map<String, String>> request = (new HttpEntity<>(headers));
        
        var url = hostAPIcrudCourses.concat("/courses/");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("filter", filter);

        ParameterizedTypeReference<List<CoursesDto>> responseType = new ParameterizedTypeReference<List<CoursesDto>>() {
        };
        try {
            System.out.println(filter);
            var result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, responseType);
            System.out.println(result);
            return result.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "ERRROOO");
            return null;
        }
    }
}
