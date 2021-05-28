package jp.co.froide.employeeListApp.dao;

import jp.co.froide.employeeListApp.entity.Department;
import jp.co.froide.employeeListApp.entity.Position;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface PositionDao {
    @Sql("select /*%expand*/* from positions")
    @Select
    List<Position> selectAll();

    @Sql("select /*%expand*/* from positions where position_id = /* id */0")
    @Select
    Department selectById(Integer id);
}
