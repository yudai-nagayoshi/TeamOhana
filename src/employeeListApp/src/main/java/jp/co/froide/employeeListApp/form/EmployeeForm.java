package jp.co.froide.employeeListApp.form;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.io.Serializable;
@Configuration
@Data
public class EmployeeForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "※入力必須項目です。")
    @Pattern(regexp = "^\\d{1,10}$", message = "※10桁以内の半角数字を入力してください。")
    private String employee_id;

    @NotBlank(message = "※入力必須項目です。")
    @Pattern(regexp = "^[ぁ-んァ-ン一-龥a-zA-Z]+　+[ぁ-んァ-ン一-龥a-zA-Z]+$", message = "※名字と名前の間に全角スペースを入れてください。")
    private String name;

    @NotBlank(message = "※入力必須項目です。")
    @Pattern(regexp ="^[ア-ン゛゜ァ-ォャ-ョー]+　+[ア-ン゛゜ァ-ォャ-ョー]+$", message = "※名字と名前の間に全角スペースを入れてください。(全角カタカナ)")
    private String furigana;

    @NotBlank(message = "※入力必須項目です。")
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "※メールアドレスの形式が正しくありません。")
    private String email;

    @NotBlank(message = "※入力必須項目です。")
    @Pattern(regexp = "^\\d{10,11}$", message = "※10~11桁の半角数字を入力してください。")
    private String phone_number;

    @DateTimeFormat(pattern = "yyyyMMdd")
    @NotBlank(message = "※入社年月日を指定してください。")
    private String joining_date;

    @Digits(fraction = 0, integer = 1)
    private Integer position_id;

    @Digits(fraction = 0, integer = 1)
    private Integer department_id;

    private boolean flg;
}
