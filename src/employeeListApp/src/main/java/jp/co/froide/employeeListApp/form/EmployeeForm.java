package jp.co.froide.employeeListApp.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

import java.io.Serializable;

@Data
public class EmployeeForm implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer employee_id;

    @NotBlank(message = "※入力必須項目です。")
    @Pattern(regexp = "^[ぁ-んァ-ン一-龥a-zA-Z]*+　+[ぁ-んァ-ン一-龥a-zA-Z]*+$", message = "※名字と名前の間に全角スペースを入れてください。")
    private String name;

    @NotBlank(message = "※入力必須項目です。")
    @Pattern(regexp ="^[ア-ン゛゜ァ-ォャ-ョー]*+　+[ア-ン゛゜ァ-ォャ-ョー]*+$", message = "※名字と名前の間に全角スペースを入れてください。(全角カタカナ)")
    private String furigana;

    @NotBlank(message = "※入力必須項目です。")
    @Pattern(regexp = "^([a-zA-Z0-9\\._-])*@([a-zA-Z0-9])+([a-zA-Z0-9\\._-]+)+$", message = "※メールアドレスの形式が正しくありません。")
    private String email;

    @NotBlank(message = "※入力必須項目です。")
    @Pattern(regexp = "^\\d{10,11}$", message = "※10~11桁の半角数字のみで入力してください。")
    private String phone_number;

    @DateTimeFormat(pattern = "yyyyMMdd")
    @NotBlank(message = "※入社年月日を指定してください。")
    private String joining_date;

    @Digits(fraction = 0, integer = 1, message = "※役職を選択してください。")
    private Integer position_id;

    @Digits(fraction = 0, integer = 1, message = "※所属部署を選択してください。")
    private Integer department_id;
}
