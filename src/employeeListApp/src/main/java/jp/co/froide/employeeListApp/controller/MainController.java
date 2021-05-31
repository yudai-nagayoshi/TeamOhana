package jp.co.froide.employeeListApp.controller;

import jp.co.froide.employeeListApp.dao.DepartmentDao;
import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.dao.PositionDao;
import jp.co.froide.employeeListApp.entity.All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    PositionDao positionDao;

    public List<All> employeeList() {
        return employeeDao.selectAll();
    }

//    @GetMapping("main")
//    public String main(Model model) {
//        List<All> list = employeeList();
//        model.addAttribute("employeeList", list);
//        return "main";
//    }

    @GetMapping("main")
    public String search(@RequestParam(name = "searchmethod", required = false) String searchmethod, @RequestParam(name = "word", required = false) String word, Model model) {
        if(searchmethod == null && word == null || word == "") {
            List<All> list = employeeList();
            model.addAttribute("employeeList", list);
            return "main";
        }

        List<All> list = employeeDao.search(searchmethod, word);
        model.addAttribute("employeeList", list);
        return "main";

    }
}
