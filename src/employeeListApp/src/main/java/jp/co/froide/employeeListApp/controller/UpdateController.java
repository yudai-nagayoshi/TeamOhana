package jp.co.froide.employeeListApp.controller;

import jp.co.froide.employeeListApp.dao.DepartmentDao;
import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.dao.PositionDao;
import jp.co.froide.employeeListApp.entity.All;
import jp.co.froide.employeeListApp.entity.Department;
import jp.co.froide.employeeListApp.entity.Employee;
import jp.co.froide.employeeListApp.entity.Position;
import jp.co.froide.employeeListApp.form.EmployeeForm;
import org.hibernate.sql.Select;
import org.seasar.doma.jdbc.criteria.context.SetOperationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UpdateController {

    @Autowired
    EmployeeDao dao;

    @Autowired
    DepartmentDao d_dao;

    @Autowired
    PositionDao p_dao;

    @GetMapping("update/{id}")
    public String update(@PathVariable("id")Integer id ,Model model){
        List<Department> dp  = d_dao.selectAll();
        List<Position> ps = p_dao.selectAll();
        model.addAttribute("position",ps);
        model.addAttribute("department",dp);
        Employee list = dao.selectById(id);
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setName(list.getName());
        employeeForm.setEmail(list.getEmail());
        employeeForm.setFurigana(list.getFurigana());
        employeeForm.setPhone_number(list.getPhone_number());
        employeeForm.setDepartment_id(list.getDepartment_id());
        employeeForm.setPosition_id(list.getPosition_id());
        employeeForm.setJoining_date(list.getJoining_date());
        model.addAttribute("EmployeeForm",employeeForm);
        model.addAttribute("id",id);
        return "update";
    }


    @PostMapping("update/{id}")
    public String update(@PathVariable("id")Integer id,@Validated @ModelAttribute("EmployeeForm") EmployeeForm form, BindingResult br,Model model){
        List<Department> dp  = d_dao.selectAll();
        List<Position> ps = p_dao.selectAll();
        model.addAttribute("position",ps);
        model.addAttribute("department",dp);
        Employee employee = dao.selectById(id);
        employee.setName(form.getName());
        employee.setEmail(form.getEmail());
        employee.setFurigana(form.getFurigana());
        employee.setPhone_number(form.getPhone_number());
        employee.setDepartment_id(form.getDepartment_id());
        employee.setPosition_id(form.getPosition_id());
        employee.setJoining_date(form.getJoining_date());
        All list = dao.selectDetail(id);
        model.addAttribute("list",list);
        if(br.hasErrors()){
            return "update";
        }
        dao.update(employee);
        return "redirect:/detail/"+id;
    }
}