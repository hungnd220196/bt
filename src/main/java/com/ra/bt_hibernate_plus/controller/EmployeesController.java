package com.ra.bt_hibernate_plus.controller;

import com.ra.bt_hibernate_plus.Dao.IDepartmentDao;
import com.ra.bt_hibernate_plus.Dao.IEmployeeDao;
import com.ra.bt_hibernate_plus.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeesController {
    @Autowired
    private IEmployeeDao iEmployeeDao;

    @RequestMapping("/listEmployee")
    public String list(Model model) {
    // hiển thị danh sách
        model.addAttribute("employees", iEmployeeDao.getEmployee());
        return "listEmployee";
    }


}