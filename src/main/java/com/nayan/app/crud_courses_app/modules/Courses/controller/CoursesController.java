package com.nayan.app.crud_courses_app.modules.Courses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nayan.app.crud_courses_app.modules.Courses.dto.CoursesDto;
import com.nayan.app.crud_courses_app.modules.Courses.service.CreateCourseService;
import com.nayan.app.crud_courses_app.modules.Courses.service.DeleteCourseService;
import com.nayan.app.crud_courses_app.modules.Courses.service.GetCourseDetailsService;
import com.nayan.app.crud_courses_app.modules.Courses.service.ListAllCursesService;
import com.nayan.app.crud_courses_app.modules.Courses.service.UpdateCourseService;
import com.nayan.app.crud_courses_app.utils.FormatErrorMessage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CreateCourseService createCourseService;

    @Autowired
    private ListAllCursesService listAllCursesService;

    @Autowired
    private GetCourseDetailsService getCourseDetailsService;

    @Autowired
    private DeleteCourseService deleteCourseService;

    @Autowired
    private UpdateCourseService updateCourseService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("courses", new CoursesDto());
        return "courses/create";
    }

    @PostMapping("/create")
    public String save(Model model, CoursesDto coursesDto) {
        try {
            this.createCourseService.execute(coursesDto);
        } catch (HttpClientErrorException e) {
            model.addAttribute("error_message", FormatErrorMessage.formatErrorMessage(e.getResponseBodyAsString()));
        }
        model.addAttribute("courses", coursesDto);
        return "redirect:/courses/create";
    }

    @GetMapping("/list")
    public String list(Model model, String filter) {
        try {
            var list = this.listAllCursesService.execute(filter != null ? filter : "");
            model.addAttribute("list", list);
        } catch (HttpClientErrorException e) {
            return "/courses/home";
        }
        return "courses/list";
    }

    @GetMapping("/home")
    public String home() {
        return "courses/home";
    }

    @GetMapping("/{id}")
    public String getCourse(@PathVariable String id, Model model) {
        var course = getCourseDetailsService.execute(id);

        if (course != null) {
            model.addAttribute("course", course);
            return "courses/getCourse";
        } else {

            return "error";
        }
    }

    @PutMapping("/{id}")
    public String updateCourse(@PathVariable String id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            Model model) {
        try {
            // Crie o DTO a partir dos dados recebidos
            CoursesDto coursesDto = new CoursesDto();
            coursesDto.setName(name);
            coursesDto.setDescription(description);
            coursesDto.setCategory(category);

            // Chama o serviço de atualização
            updateCourseService.execute(coursesDto, id);

            model.addAttribute("course", coursesDto);
            return "redirect:/courses/{id}";
        } catch (HttpClientErrorException e) {
            model.addAttribute("error_message", FormatErrorMessage.formatErrorMessage(e.getResponseBodyAsString()));
            return "/courses/getCourse";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable String id, RedirectAttributes redirectAttributes) {
        deleteCourseService.execute(id);
        redirectAttributes.addFlashAttribute("message", "Curso deletado com sucesso!");
        return "redirect:/courses/list";
    }

}
