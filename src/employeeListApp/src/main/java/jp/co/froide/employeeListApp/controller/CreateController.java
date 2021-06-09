package jp.co.froide.employeeListApp.controller;

import jp.co.froide.employeeListApp.dao.DepartmentDao;
import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.dao.PositionDao;
import jp.co.froide.employeeListApp.entity.All;
import jp.co.froide.employeeListApp.entity.Department;
import jp.co.froide.employeeListApp.entity.Employee;
import jp.co.froide.employeeListApp.entity.Position;
import jp.co.froide.employeeListApp.form.EmployeeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String create(@RequestParam(name = "searchMethod", required = false) String searchMethod, @RequestParam(name = "word", required = false) String word, Model model){
        List<Department> dp = d_Dao.selectAll();
        List<Position> ps = p_Dao.selectAll();
        model.addAttribute("position",ps);
        model.addAttribute("department",dp);
        model.addAttribute("EmployeeForm",new EmployeeForm());
        model.addAttribute("searchMethod", searchMethod);
        model.addAttribute("word", word);
        model.addAttribute("error","");
        return "create";
    }

    @PostMapping("/create")
    public String create(@RequestParam(name = "searchMethod", required = false) String searchMethod, @RequestParam(name = "word", required = false) String word, @Validated @ModelAttribute("EmployeeForm") EmployeeForm form, BindingResult br,Model model){
        List<Department> dp = d_Dao.selectAll();
        List<Position> ps = p_Dao.selectAll();
        model.addAttribute("error","");
        model.addAttribute("position", ps);
        model.addAttribute("department", dp);
        model.addAttribute("searchMethod", searchMethod);
        model.addAttribute("word", word);

        boolean over = false;
        for(All e : dao.selectAll()){
            if(form.getEmployee_id().equals(""+e.getEmployee_id())){
                model.addAttribute("error","※登録されている社員番号です。");
                over = true;
                break;
            }
        }

        if (br.hasErrors() || over) {
            return "create";
        }

        Employee employee = new Employee();
        employee.setEmployee_id(form.getEmployee_id());
        employee.setName(form.getName());
        employee.setEmail(form.getEmail());
        employee.setFurigana(form.getFurigana());
        employee.setPhone_number(form.getPhone_number());
        employee.setDepartment_id(form.getDepartment_id());
        employee.setPosition_id(form.getPosition_id());
        employee.setJoining_date(form.getJoining_date());

        dao.insert(employee);
        return "redirect:/detail/"+form.getEmployee_id();

    }
}
