package jp.co.froide.employeeListApp.dao;

import jp.co.froide.employeeListApp.entity.Department;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.boot.ConfigAutowireable;
import java.util.List;

@ConfigAutowireable
@Dao
public interface DepartmentDao {

    @Sql("SELECT /*%expand*/* FROM departments")
    @Select
    List<Department> selectAll();

    @Sql("SELECT /*%expand*/* FROM departments WHERE department_id = /* id */0")
    @Select
    Department selectById(Integer id);
}
