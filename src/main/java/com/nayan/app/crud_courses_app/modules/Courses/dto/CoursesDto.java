package com.nayan.app.crud_courses_app.modules.Courses.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursesDto {
    private String name;
    private String category;
    private String description;
    private Status status;

    public enum Status {
        ACTIVE,
        INACTIVE,
        COMPLETED
    }
}
