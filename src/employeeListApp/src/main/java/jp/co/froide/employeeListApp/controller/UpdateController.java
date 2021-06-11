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
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UpdateController {

    @Autowired
    EmployeeDao dao;

    @Autowired
    DepartmentDao d_dao;

    @Autowired
    PositionDao p_dao;

    @Autowired
    EmployeeForm employeeForm;

    List<All> box;
    Employee list;
    List<Department> dp;
    List<Position> ps;

    @GetMapping("update/{id}")
    public String update(@RequestParam(name = "searchMethod", required = false) String searchMethod, @RequestParam(name = "word", required = false) String word,@PathVariable("id")String id, HttpServletRequest request,Model model){
        try {
            box = dao.selectAll();
            dp = d_dao.selectAll();
            ps = p_dao.selectAll();
            list = dao.selectById(id);
            employeeForm.setFlg(false);
        }catch (TransientDataAccessResourceException t){
            if(employeeForm.isFlg()){
                model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
                model.addAttribute("message", "DBサーバーの接続に失敗しました。");
                model.addAttribute("button", "再接続する");
                model.addAttribute("method", request.getMethod());
                System.out.println(request.getRequestURI()+" "+request.getMethod()+" "+searchMethod+" "+word);
                model.addAttribute("url",request.getRequestURI());
                model.addAttribute("searchMethod", searchMethod);
                model.addAttribute("word", word);
                return "error";
            }
            t.getMessage();
        }
        model.addAttribute("position", ps);
        model.addAttribute("department", dp);
        employeeForm.setEmployee_id(id);
        employeeForm.setName(list.getName());
        employeeForm.setEmail(list.getEmail());
        employeeForm.setFurigana(list.getFurigana());
        employeeForm.setPhone_number(list.getPhone_number());
        employeeForm.setDepartment_id(list.getDepartment_id());
        employeeForm.setPosition_id(list.getPosition_id());
        employeeForm.setJoining_date(list.getJoining_date());
        model.addAttribute("searchMethod", searchMethod);
        model.addAttribute("word", word);
        model.addAttribute("EmployeeForm",employeeForm);
        model.addAttribute("id",id);
        return "update";
    }

    @PostMapping("update/{id}")
    public String update(@RequestParam(name = "searchMethod", required = false) String searchMethod, @RequestParam(name = "word", required = false) String word,@PathVariable("id")String id,@Validated @ModelAttribute("EmployeeForm") EmployeeForm form, BindingResult br,Model model){
        model.addAttribute("position",ps);
        model.addAttribute("department",dp);
        Employee employee = new Employee();
        employee.setEmployee_id(form.getEmployee_id());
        employee.setName(form.getName());
        employee.setEmail(form.getEmail());
        employee.setFurigana(form.getFurigana());
        employee.setPhone_number(form.getPhone_number());
        employee.setDepartment_id(form.getDepartment_id());
        employee.setPosition_id(form.getPosition_id());
        employee.setJoining_date(form.getJoining_date());

        boolean over = false;
        for(All e : box){
            if(form.getEmployee_id().equals(e.getEmployee_id())){
                if(!form.getEmployee_id().equals(id)) {
                    model.addAttribute("error", "※登録されている社員番号です。");
                    over = true;
                    break;
                }
            }
            if(over == true){
                break;
            }
        }

        if(br.hasErrors() || over){
            return "update";
        }
        try {
            if (!form.getEmployee_id().equals(id) && over == false) {
                dao.updateId(id, form.getEmployee_id());
            }
            dao.update(employee);
            All list = dao.selectDetail(employee.getEmployee_id());
            model.addAttribute("list", list);
            return "redirect:/detail/" + employee.getEmployee_id();
        }catch (TransientDataAccessResourceException t) {
            model.addAttribute("message", "更新に失敗しました");
            model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
            model.addAttribute("button", "再接続する");
            model.addAttribute("method", "get");
            model.addAttribute("url", "/update/" + id);
            model.addAttribute("searchMethod", searchMethod);
            model.addAttribute("word", word);
            employeeForm.setFlg(false);
            return "error";
        }
    }
}
