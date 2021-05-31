package jp.co.froide.employeeListApp.dao;

import jp.co.froide.employeeListApp.entity.All;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.builder.SelectBuilder;
import jp.co.froide.employeeListApp.entity.Employee;

import java.util.List;

@ConfigAutowireable
@Dao
public interface EmployeeDao {
    default int count() {
        Config config = Config.get(this);
        SelectBuilder builder = SelectBuilder.newInstance(config);
        builder.sql("select count(*) from employees");
        return builder.getScalarSingleResult(int.class);
    }

    @Sql("SELECT employee_id, name, furigana, joining_date, TIMESTAMPDIFF(YEAR, joining_date, CURRENT_DATE)as period,employees.position_id,positions.position, employees.department_id,departments.department, " +
            "email, phone_number FROM employees " +
            "INNER JOIN positions ON employees.position_id = positions.position_id " +
            "INNER JOIN departments ON employees.department_id = departments.department_id " +
            "ORDER BY employee_id ASC")
    @Select
    List<All> selectAll();



    @Sql("select /*%expand*/* from employees where employee_id = /* id */0")
    @Select
    Employee selectById(Integer id);

    @Sql("SELECT employee_id, name, furigana, joining_date, TIMESTAMPDIFF(YEAR, joining_date, CURRENT_DATE)as period,employees.position_id,positions.position, employees.department_id,departments.department, " +
            "email, phone_number FROM employees " +
            "INNER JOIN positions ON employees.position_id = positions.position_id " +
            "INNER JOIN departments ON employees.department_id = departments.department_id " +
            "WHERE /*# item */ LIKE /*'%' + word + '%' */'a'"  +
            "ORDER BY employee_id ASC")
    @Select
    List<All> search(String item, String word);



    @Sql("SELECT employee_id, name, furigana, joining_date, TIMESTAMPDIFF(YEAR, joining_date, CURRENT_DATE)as period,employees.position_id,positions.position, employees.department_id,departments.department, " +
            "email, phone_number FROM employees " +
            "INNER JOIN positions ON employees.position_id = positions.position_id " +
            "INNER JOIN departments ON employees.department_id = departments.department_id " +
            "ORDER BY /*# item */ /*# way */")
    @Select
    List<Employee> sort(String item, String way);

//
//    @Insert
//    int insert(Employee employee);
//
    @Update
    int update(Employee employee);
//
//    @Delete
//    int delete(Employee employee);

    @Sql("SELECT TIMESTAMPDIFF(YEAR, joining_date, CURRENT_DATE) FROM employees")
    @Select
    List<Integer> period();
}


