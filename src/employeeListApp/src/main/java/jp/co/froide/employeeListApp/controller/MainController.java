package jp.co.froide.employeeListApp.controller;

import jp.co.froide.employeeListApp.dao.DepartmentDao;
import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.dao.PositionDao;
import jp.co.froide.employeeListApp.entity.All;
import jp.co.froide.employeeListApp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

//    public List<All> employeeList() {
////        List<All> all = new ArrayList<>();
////        List<Employee> employeeList = employeeDao.selectAll();
////        for (Employee employee : employeeList){
////            String  department = departmentDao.selectById(employee.getDepartment_id()).getDepartment();
////        }
//        return employeeDao.selectAll();
//    }
//    public List<Integer> joiningPeriod(){
//        return employeeDao.period();
//    }
//
//    @GetMapping("main")
//    public String hello(Model model) {
//        List<All> list = employeeList();
//        model.addAttribute("employeeList", list);
//        model.addAttribute("joiningPeriod", joiningPeriod());
//        return "main";
//    }
}
