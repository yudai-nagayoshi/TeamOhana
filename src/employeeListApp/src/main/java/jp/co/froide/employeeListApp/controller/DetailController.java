package jp.co.froide.employeeListApp.controller;


import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.entity.All;
import jp.co.froide.employeeListApp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class DetailController {

    @Autowired
    EmployeeDao employeeDao;

//    @GetMapping
//    public List<All> test(){
//        return employeeDao.selectAll();
//    }

    @GetMapping("detail/{id}")
    public String detailList(@PathVariable("id") Integer id,Model model){
        All list = employeeDao.selectDetail(id);
        model.addAttribute("list",list);
        return "detail";
    }

    @GetMapping(value = "/delete",params = "delete")
    public String delete(@PathVariable("id")Integer id, Employee employee){
        employee.setEmployee_id(id);
        employeeDao.delete(employee);
        return "redirect:/main";
    }
}
