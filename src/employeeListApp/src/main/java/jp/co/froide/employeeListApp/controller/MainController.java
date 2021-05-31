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

}
