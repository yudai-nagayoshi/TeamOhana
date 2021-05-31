package jp.co.froide.employeeListApp.controller;

import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.entity.Employee;
import jp.co.froide.employeeListApp.entity.EmployeeAll;
import org.seasar.doma.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @GetMapping
    public List<EmployeeAll> employeeLis() {
        return employeeDao.selectAll();
    }


    @GetMapping("detail/{id}")
    public String test(@PathVariable("id") Integer id,Model model){
        Employee list = employeeDao.selectById(id);
        model.addAttribute("list",list);
        return "detail";
    }

//    @GetMapping("detail")
//    public String detail(Model model){
//        Employee detail = employeeDao.selectById(1);
//        model.addAttribute("detail",detail);
//        return "detail";
//    }

//    @PostMapping("employees")
//    public void insertEmployee(@RequestParam ("name") String name, @RequestParam ("furigana") String furigana,
//                               @RequestParam ("joining_date") String joining_date,
//                               @RequestParam ("position_id") Integer position_id, @RequestParam ("department_id") Integer department_id,
//                               @RequestParam ("pone_number") String phone_number, @RequestParam ("email") String email) {
//        Employee employee = new Employee();
//        employee.setName(name);
//        employee.setFurigana(furigana);
//        employee.setJoining_date(joining_date);
//        employee.setPosition_id(position_id);
//        employee.setDepartment_id(department_id);
//        employee.setPhone_number(phone_number);
//        employee.setEmail(email);
//        employeeDao.insert(employee);
//    }

//    @PutMapping("employee/{id}")
//    public void updateEmployee(@PathVariable("id") Integer id, @RequestParam ("name") String name, @RequestParam ("furigana") String furigana,
//                               @RequestParam ("joining_date") String joining_date,
//                               @RequestParam ("position_id") Integer position_id, @RequestParam ("department_id") Integer department_id,
//                               @RequestParam ("pone_number") String phone_number, @RequestParam ("email") String email) {
//        Employee employee = employeeDao.selectById(id);
//        employee.setName(name);
//        employee.setFurigana(furigana);
//        employee.setJoining_date(joining_date);
//        employee.setPosition_id(position_id);
//        employee.setDepartment_id(department_id);
//        employee.setPhone_number(phone_number);
//        employee.setEmail(email);
//        employeeDao.update(employee);
//    }

    @DeleteMapping("detail/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, Model model) {

        return "detail";
    }
}

