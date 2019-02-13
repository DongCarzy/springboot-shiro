package com.example.springbootshiro.business.web;

import com.example.springbootshiro.business.domain.Student;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author carzy.
 * @date 13:53 2018/12/25
 */
@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping
    @RequiresPermissions("student:read")
    public List<Student> findAll() {
        return new ArrayList<>();
    }

    @PostMapping
    @RequiresPermissions("student:add")
    public Student add() {
        return new Student();
    }

}
