package jp.co.froide.employeeListApp.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.entity.All;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    EmployeeDao employeeDao;

    List<All> list = new ArrayList<>();

    public List<All> employeeList() {
        return employeeDao.selectAll();
    }

    @GetMapping("main")
    public String search(@RequestParam(name = "searchmethod", required = false) String searchmethod, @RequestParam(name = "word", required = false) String word, Model model) {
        String error = "";
        if (employeeDao.selectAll().size() == 0){
            error = "社員が登録されていません";
        }
        if(searchmethod == null && word == null || word.equals("")) {
            word = null;
            this.list = employeeList();
            model.addAttribute("employeeList", list);
            model.addAttribute("searchmethod", searchmethod);
            model.addAttribute("word", word);
            model.addAttribute("error", error);
            return "main";
        }

        this.list = employeeDao.search(searchmethod, word);

        if(searchmethod.equals("name")){ //名前検索時、かなカナ検索に対応
            List<All> listex = employeeDao.search("furigana", word);
            for (All i : listex){
                boolean match = false;
                for (All j : this.list){
                    if(i.getEmployee_id() == j.getEmployee_id()){
                        match = true;
                        break;
                    }
                }
                if(match == false) this.list.add(i);
            }
        }
        if(list.size() == 0){
            error = "検索結果がありません";
        }

        model.addAttribute("employeeList", list);
        model.addAttribute("searchmethod", searchmethod);
        model.addAttribute("word", word);
        model.addAttribute("error", error);
        return "main";

    }

    //メイン機能CSVダウンロード
    @GetMapping(value = "/*.csv", params = "download_file",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8; Content-Disposition: attachment"
    )
    @ResponseBody
    public Object index() throws JsonProcessingException {
        // User名取得
        // DBから取得
        List<All> todos = this.list;

        // CSVファイル用のDTOに詰め直す
        List<EmployeeCsv> csvs = todos.stream().map(
                e -> new EmployeeCsv(e.getEmployee_id(), e.getName(), e.getFurigana(), e.getJoining_date(), e.getPosition(), e.getDepartment(), e.getPhone_number(), e.getEmail())
        ).collect(Collectors.toList());
        // ファイルをダウンロードさせる
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(EmployeeCsv.class).withHeader();
        return mapper.writer(schema).writeValueAsString(csvs);
    }

    @Value
    @AllArgsConstructor
    @JsonPropertyOrder({"employee_id", "name", "furigana","joining_date", "position", "department", "phone_number", "email"})
    public class EmployeeCsv {
        @JsonProperty("employee_id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("furigana")
        private String furigana;
        @JsonProperty("joining_date")
        private String joining_date;
        @JsonProperty("position")
        private String position;
        @JsonProperty("department")
        private String department;
        @JsonProperty("phone_number")
        private String phone_number;
        @JsonProperty("email")
        private String email;

    }
}
