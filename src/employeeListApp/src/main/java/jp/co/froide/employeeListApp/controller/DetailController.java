package jp.co.froide.employeeListApp.controller;


import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.entity.All;
import jp.co.froide.employeeListApp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DetailController {

    @Autowired
    EmployeeDao employeeDao;
    Model model;
    All all;

    @GetMapping("detail/{id}")
    public String detailList(@PathVariable("id") Integer id){
        All detail = employeeDao.selectDetail(id);
        model.addAttribute("list",detail);
        return "detail";
    }

    @DeleteMapping("detail/{id}")
    public String delete(@PathVariable("id") Integer id,Employee employee){
        employee.setEmployee_id(id);
        employeeDao.delete(employee);
        return "detail";
    }


//    メイン機能CSVダウンロード
//    @GetMapping(value = "/*.csv", params = "download_file",
//            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8; Content-Disposition: attachment"
//    )
//    @ResponseBody
//    public Object index() throws JsonProcessingException {
//        // User名取得
//        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
//        Authentication authentication = securityContext.getAuthentication();
//        AuthTargetUser principal = (AuthTargetUser) authentication.getPrincipal();
//        String username = principal.getUsername();
//        // DBから取得
//        List<All> todos = todoRepository.findList(username);
//
//        // CSVファイル用のDTOに詰め直す
//        List<TodoCsv> csvs = todos.stream().map(
//                e -> new TodoCsv(e.getId(), e.getUserId(), e.getAction())
//        ).collect(Collectors.toList());
//        // ファイルをダウンロードさせる
//        CsvMapper mapper = new CsvMapper();
//        CsvSchema schema = mapper.schemaFor(TodoCsv.class).withHeader();
//        return mapper.writer(schema).writeValueAsString(csvs);
//    }

//    参考サイトhttps://nainaistar.hatenablog.com/entry/2020/12/20/083000

}
