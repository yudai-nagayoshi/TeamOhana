package jp.co.froide.employeeListApp.controller;

import jp.co.froide.employeeListApp.dao.DepartmentDao;
import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.dao.PositionDao;
import jp.co.froide.employeeListApp.entity.Department;
import jp.co.froide.employeeListApp.entity.Employee;
import jp.co.froide.employeeListApp.entity.Position;
import jp.co.froide.employeeListApp.form.EmployeeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class CreateController {

    @Autowired
    EmployeeDao dao;

    @Autowired
    DepartmentDao d_Dao;

    @Autowired
    PositionDao p_Dao;

    @GetMapping("/create")
    public String create(Model model){
            List<Department> dp = d_Dao.selectAll();
            List<Position> ps = p_Dao.selectAll();
            model.addAttribute("position",ps);
            model.addAttribute("department",dp);
            model.addAttribute("EmployeeForm",new EmployeeForm());
            return "create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("EmployeeForm") EmployeeForm form, BindingResult br,Model model){
            List<Department> dp = d_Dao.selectAll();
            List<Position> ps = p_Dao.selectAll();
            model.addAttribute("position", ps);
            model.addAttribute("department", dp);
            if (br.hasErrors()) {
                return "create";
            }
            Employee employee = new Employee();
            employee.setName(form.getName());
            employee.setEmail(form.getEmail());
            employee.setFurigana(form.getFurigana());
            employee.setPhone_number(form.getPhone_number());
            employee.setDepartment_id(form.getDepartment_id());
            employee.setPosition_id(form.getPosition_id());
            employee.setJoining_date(form.getJoining_date());
            dao.insert(employee);
            return "redirect:/main";
    }
}
