package jp.co.froide.employeeListApp.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import jp.co.froide.employeeListApp.dao.EmployeeDao;
import jp.co.froide.employeeListApp.entity.All;
import jp.co.froide.employeeListApp.form.EmployeeForm;
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

    @Autowired
    EmployeeForm employeeForm;

    List<All> list = new ArrayList<>();

    public List<All> employeeList() {
            return employeeDao.selectAll();
    }

    @GetMapping("main")
    public String search(@RequestParam(name = "searchMethod", required = false) String searchMethod, @RequestParam(name = "word", required = false) String word, Model model) {
        employeeForm.setFlg(true);
        String error = "";
        model.addAttribute("count",list.size());
        if ( employeeDao.selectAll().size() == 0) {
            error = "社員が登録されていません";
        }
        if (searchMethod == null && word == null || word.equals("")) {
            word = null;
            this.list = employeeList();
            model.addAttribute("count",list.size());
            model.addAttribute("employeeList", list);
            model.addAttribute("searchMethod", searchMethod);
            model.addAttribute("word", word);
            model.addAttribute("error", error);
            return "main";
        }
        this.list = employeeDao.search(searchMethod, word);
        model.addAttribute("count",list.size());
        if (searchMethod.equals("name")) { //名前検索時、かなカナ検索に対応
            List<All> listEx = employeeDao.search("furigana", word);
            for (All i : listEx) {
                boolean match = false;
                for (All j : this.list) {
                    if (i.getEmployee_id().equals(j.getEmployee_id())) {
                        match = true;
                        break;
                    }
                }
                if (match == false) this.list.add(i);
            }
        }
        if (list.size() == 0) {
            error = "検索結果がありません";
        }
        model.addAttribute("employeeList", list);
        model.addAttribute("searchMethod", searchMethod);
        model.addAttribute("word", word);
        model.addAttribute("error", error);
        return "main";
    }

    //メイン機能CSVダウンロード
    @GetMapping(value = "/*.csv", params = "downloadFile",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8; Content-Disposition: attachment")
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
    @JsonPropertyOrder({"社員番号", "社員名", "フリガナ","入社日", "役職", "所属部署", "携帯番号", "メールアドレス"})
    public class EmployeeCsv {
        @JsonProperty("社員番号")
        private String id;
        @JsonProperty("社員名")
        private String name;
        @JsonProperty("フリガナ")
        private String furigana;
        @JsonProperty("入社日")
        private String joining_date;
        @JsonProperty("役職")
        private String position;
        @JsonProperty("所属部署")
        private String department;
        @JsonProperty("携帯番号")
        private String phone_number;
        @JsonProperty("メールアドレス")
        private String email;
    }
}
