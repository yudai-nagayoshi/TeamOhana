package jp.co.froide.employeeListApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Getter
@Setter
@Entity
@Table(name = "positions")
public class Position{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer position_id;

    private String position;
}


