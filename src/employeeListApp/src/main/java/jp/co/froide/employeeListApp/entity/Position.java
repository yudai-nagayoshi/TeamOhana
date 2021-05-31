package jp.co.froide.employeeListApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.FetchType;

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


