package dao.intf;


import domain.Grade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGradeDao {

    @Select("select * from grade order by grade desc ")
    public List<Grade> findAll();

    @Select("select * from grade where teamid=#{id}")
    public Grade findById(int id);

    @Update("update grade set teamid=#{teamid},grade=#{grade}+grade,get=#{get}+get,lose=#{lose}+lose,name=#{name} where teamid=#{teamid}")
    public void update(Grade grade);

    @Insert("insert into grade values(#{teamid},#{grade},#{get},#{lose},#{name})")
    public void save(Grade grade);

}
