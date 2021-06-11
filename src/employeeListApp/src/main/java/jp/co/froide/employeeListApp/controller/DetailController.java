package jp.co.froide.employeeListApp.controller;

import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.entity.All;
import jp.co.froide.employeeListApp.entity.Employee;
import jp.co.froide.employeeListApp.form.EmployeeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class DetailController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    EmployeeForm employeeForm;

    All list;

    @GetMapping("detail/{id}")
    public String detailList(@RequestParam(name = "searchMethod", required = false) String searchMethod, @RequestParam(name = "word", required = false) String word, @PathVariable("id") String id, HttpServletRequest request, Model model) {
        try {
            for (All e : employeeDao.selectAll()) {
                if (Objects.equals(e.getEmployee_id(), id)) {
                    list = employeeDao.selectDetail(id);
                    model.addAttribute("list", list);
                    model.addAttribute("searchMethod", searchMethod);
                    model.addAttribute("word", word);
                    employeeForm.setFlg(true);
                    return "detail";
                }
            }
            return "error/404";
        }catch (TransientDataAccessResourceException t){
            if(employeeForm.isFlg()){
                model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
                model.addAttribute("message", "DBサーバーの接続に失敗しました。");
                model.addAttribute("button", "再接続する");
                model.addAttribute("method", request.getMethod());
                model.addAttribute("searchMethod", searchMethod);
                model.addAttribute("word", word);
                model.addAttribute("url",request.getRequestURI());
                System.out.println(request.getContextPath()+"1"+request.getPathInfo()+"2"+request.getPathTranslated()+"3"+request.getQueryString());
                return "error";
            }
            model.addAttribute("list",list);
            model.addAttribute("searchMethod", searchMethod);
            model.addAttribute("word", word);
            return "detail";
        }
    }

    @PostMapping("delete/{id}")
    public String delete(@RequestParam(name = "searchMethod", required = false) String searchMethod, @RequestParam(name = "word", required = false) String word,@PathVariable("id") String id, Employee employee,Model model) {
        try {
            employee.setEmployee_id(id);
            employeeDao.delete(employee);
            model.addAttribute("","ok");
            model.addAttribute("name",list.getName());
            return "redirect:/main";
        }catch (TransientDataAccessResourceException t){
            model.addAttribute("message","削除に失敗しました");
            model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
            model.addAttribute("button", "再接続する");
            model.addAttribute("method","get");
            model.addAttribute("url","/detail/"+id);
            model.addAttribute("searchMethod", searchMethod);
            model.addAttribute("word", word);
            employeeForm.setFlg(false);
            return "error";
        }
    }
}
