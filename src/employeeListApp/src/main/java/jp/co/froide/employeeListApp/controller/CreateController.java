package jp.co.froide.employeeListApp.controller;

import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.entity.All;
import jp.co.froide.employeeListApp.entity.Employee;
import jp.co.froide.employeeListApp.form.EmployeeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CreateController {

    @Autowired
    EmployeeDao dao;


    @GetMapping("/create")
    public String create(@ModelAttribute("employeeForm") EmployeeForm employeeForm,Model model){
        List<All> dp  = dao.selectDepartment();
        List<All> ps = dao.selectPosition();
        model.addAttribute("position",ps);
        model.addAttribute("department",dp);
        model.addAttribute("EmployeeForm",new EmployeeForm());
        return "create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("employeeForm") EmployeeForm form, BindingResult br,Employee employee, RedirectAttributes attributes, Model model){
        List<All> dp  = dao.selectDepartment();
        List<All> ps = dao.selectPosition();
        model.addAttribute("position",ps);
        model.addAttribute("department",dp);
        model.addAttribute("EmployeeForm",new EmployeeForm());
        System.out.print(form.getPosition_id());
        if(br.hasErrors()){
            return "create";
        }
        dao.insert(employee);
        return "redirect:/main";
    }

}

