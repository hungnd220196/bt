package com.ra.bt_hibernate_plus.controller;

import com.ra.bt_hibernate_plus.Dao.IDepartmentDao;

import com.ra.bt_hibernate_plus.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentController {
    @Autowired
    private IDepartmentDao iDepartmentDao;


    @RequestMapping(value = {"/", "/listDepartment"})
    public String list(Model model) {
// hiển thị danh sách
        model.addAttribute("departments", iDepartmentDao.getDepartment());
        return "list";
    }


    @GetMapping("/add")
    public String showAddDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "addDepartment";

    }

    @PostMapping("/addDepartment")
    public String addDepartment(@Validated @ModelAttribute("department") Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addDepartment";
        }
        boolean bl = iDepartmentDao.insertDepartment(department);
        if (bl) {
            return "redirect:/list";
        } else {
            model.addAttribute("department", department);
            return "list";
        }
    }

    @GetMapping("/edit")
    public String showEditDepartment(Model model, @RequestParam("id") int id) {
        model.addAttribute("department", iDepartmentDao.getDepartmentById(id));
        return "edit";
    }


    @PostMapping("/editDepartment")
    public String editDepartment(@ModelAttribute("department") Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit";
        }
        boolean bl = iDepartmentDao.updateDepartment(department);
        if (bl) {
            return "redirect:/list";
        } else {
            model.addAttribute("department", department);
            return "edit";
        }
    }
}
