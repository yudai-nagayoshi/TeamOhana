package jp.co.froide.employeeListApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employee_id;

    private String name;

    private String furigana;

    private String email;

    private String phone_number;

    private String joining_date;

    private Integer position_id;

    private Integer department_id;

    private String adding_date;

    private String last_update_date;
}
