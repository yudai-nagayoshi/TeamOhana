package jp.co.froide.employeeListApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Getter
@Setter
@Entity
public class All {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer employee_id;
    String name;
    String furigana;
    String email;
    String phone_number;
    String joining_date;
    Integer position_id;
    Integer department_id;
    String department;
    String position;
    String period;
}
