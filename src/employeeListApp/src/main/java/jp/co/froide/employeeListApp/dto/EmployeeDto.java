package jp.co.froide.employeeListApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@JsonPropertyOrder({"employee_id", "name", "furigana","phone_number","joining_date","department","position","period"})
public class EmployeeDto {

        @JsonProperty("employee_id")
        private int employee_id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("furigana")
        private String furigana;
        @JsonProperty("email")
        private String email;
        @JsonProperty("phone_number")
        private String phone_number;
        @JsonProperty("joining_date")
        private String joining_date;
        @JsonProperty("department")
        private String department;
        @JsonProperty("position")
        private String position;
        @JsonProperty("period")
        private String period;

}
