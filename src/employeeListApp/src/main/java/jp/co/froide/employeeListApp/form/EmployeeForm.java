package jp.co.froide.employeeListApp.form;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.io.Serializable;

@Data
public class EmployeeForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "名字と名前の間に全角スペースを入れてください" + "入力必須項目です")
    @Pattern(regexp = "/^(.+?)[\\s　]+(.+)$/")
    private String name;

    @NotBlank(message = "カタカナで入力してください" + "入力必須項目です")
    @Pattern(regexp = "/^[ア-ン゛゜ァ-ォャ-ョー「」、]+$/")
    private String furigana;

    @NotBlank(message = "半角英数字で入力してください" + "入力必須項目です")
    @Email
    @Pattern(regexp = "/\\A\\S+@\\S+\\.\\S+\\z/")
    private String email;

    @Digits(fraction = 0, integer = 11,message = "半角数字のみで入力してください" + "入力必須項目です")
    private Integer phone_number;
    private String email;

    @NotBlank(message = "年月日を指定してください" + "入力必須項目です")
    private String joining_date;

    @Digits(fraction = 0, integer = 1,message = "役職を選択してください" + "選択必須項目です")
    private Integer position_id;

    @Digits(fraction = 0, integer = 1,message = "所属部署を選択してください" + "選択必須項目です")
    private Integer department_id;

    @NotBlank
    private String searchText;

}
