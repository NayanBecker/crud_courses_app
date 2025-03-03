package com.nayan.app.crud_courses_app.modules.Courses.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeleteCourseService {

    @Value("${host.api.crud.courses}")
    private String hostAPIcrudCourses;

    public void execute(String courseId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<>(headers);

        var url = hostAPIcrudCourses.concat("/courses/delete/").concat(courseId);

        try {
            restTemplate.exchange(url, HttpMethod.DELETE, request, Void.class);
            System.out.println("Curso deletado com ID: " + courseId);
        } catch (Exception e) {
            System.out.println("Erro ao deletar o curso: " + e.getMessage());
        }
    }
}
