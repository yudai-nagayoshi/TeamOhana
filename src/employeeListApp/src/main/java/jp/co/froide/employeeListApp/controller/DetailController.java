package jp.co.froide.employeeListApp.controller;

import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.entity.All;
import jp.co.froide.employeeListApp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DetailController {

    @Autowired
    EmployeeDao employeeDao;

    @GetMapping("detail/{id}")
    public String detailList(@RequestParam(name = "searchMethod", required = false) String searchMethod, @RequestParam(name = "word", required = false) String word,@PathVariable("id") String id, Model model) {
            All list = employeeDao.selectDetail(id);
            model.addAttribute("list", list);
            model.addAttribute("searchMethod", searchMethod);
            model.addAttribute("word", word);
            return "detail";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") String id, Employee employee) {
            employee.setEmployee_id(id);
            employeeDao.delete(employee);
            return "redirect:/main";
    }
}
