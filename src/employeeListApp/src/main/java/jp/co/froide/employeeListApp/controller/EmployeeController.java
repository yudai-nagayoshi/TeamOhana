package jp.co.froide.employeeListApp.controller;

import jp.co.froide.employeeListApp.dao.DepartmentDao;
import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.dao.PositionDao;
import jp.co.froide.employeeListApp.entity.Employee;
import jp.co.froide.employeeListApp.form.EmployeeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    PositionDao positionDao;

    @GetMapping("employees")
    public List<Employee> employeeList() {
        return employeeDao.selectAll();
    }

//
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
//
//    @PutMapping("employee/{id}")
//    public void updateEmployee(@PathVariable("id") Integer id, @RequestParam("name") String name, @RequestParam("furigana") String furigana,
//                               @RequestParam("joining_date") String joining_date,
//                               @RequestParam("position_id") Integer position_id, @RequestParam("department_id") Integer department_id,
//                               @RequestParam("pone_number") String phone_number, @RequestParam("email") String email) {
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
//
//    @DeleteMapping("employee/{id}")
//    public void deleteEmployee(@PathVariable("id") Integer id) {
//        Employee employee = employeeDao.selectById(id);
//        employeeDao.delete(employee);
//    }

    @GetMapping("create")//create画面
    public String create(Model model) {
        model.addAttribute("EmployeeForm", new EmployeeForm());
        model.addAttribute("position", positionDao.selectAll());
        model.addAttribute("department", departmentDao.selectAll());
        return "create";
    }

    @PostMapping("create")//create画面
    public String create(@Validated EmployeeForm form, BindingResult br, RedirectAttributes attributes){
        if (br.hasErrors()) {
            return "redirect:/create";
        }
        return "redirect:/main";
    }


    @GetMapping("/update/{id}") //update画面
    public String update(Model model, @PathVariable("id") Integer id) {
        Employee employee = employeeDao.selectById(id);
        model.addAttribute("position", positionDao.selectAll());
        model.addAttribute("department", departmentDao.selectAll());
        model.addAttribute("employeeForm", new EmployeeForm());
        model.addAttribute("employee", employee);
        return "update";
    }

    @PostMapping("/update/{id}")//update画面
    public String update(@Validated @ModelAttribute EmployeeForm form, BindingResult br, RedirectAttributes attributes){
        if (br.hasErrors()) {
            return "redirect:/update";
        }
        return "update";
    }
}
