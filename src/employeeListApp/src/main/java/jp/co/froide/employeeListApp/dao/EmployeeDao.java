package jp.co.froide.employeeListApp.dao;

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
        builder.sql("select count(*) from employee");
        return builder.getScalarSingleResult(int.class);
    }

    @Sql("SELECT /*%expand*/* FROM employee " +
            "INNER JOIN positions ON employee.position_id = positions.position_id　" +
            "INNER JOIN departments ON employee.department_id = departments.department_id")
    @Select
    List<Employee> selectAll();

    @Sql("select /*%expand*/* from employee" +
            "INNER JOIN positions ON employee.position_id = positions.position_id　" +
            "INNER JOIN departments ON employee.department_id = departments.department_id" +
            "where id = /* id */0")
    @Select
    Employee selectById(Integer id);

    @Sql("SELECT /*%expand*/* FROM employee " +
            "INNER JOIN positions ON employee.position_id = positions.position_id　" +
            "INNER JOIN departments ON employee.department_id = departments.department_id" +
            "where /* item */ LIKE %/* word */%")
    @Select
    List<Employee> search(String item, String word);

    @Sql("SELECT /*%expand*/* FROM employee ORDER BY /* item */ /* way */")
    @Select
    List<Employee> sort(Employee employee, String item, String way);


    @Insert
    int insert(Employee employee);

    @Update
    int update(Employee employee);

    @Delete
    int delete(Employee employee);
}
