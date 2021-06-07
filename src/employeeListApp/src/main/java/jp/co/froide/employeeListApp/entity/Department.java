package jp.co.froide.employeeListApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer department_id;
    private String department;
}
