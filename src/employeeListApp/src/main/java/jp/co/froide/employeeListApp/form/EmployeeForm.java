package jp.co.froide.employeeListApp.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class EmployeeForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private int employee_id;

    @NotBlank
    private int name;

    @NotBlank
    private int furigana;

    @NotBlank
    private int email;

    @NotBlank
    private int phone_number;

    @NotBlank
    private int joining_date;

    @NotBlank
    private int position_id;

    @NotBlank
    private int department_id;

    @NotBlank
    private int searchText;

}
