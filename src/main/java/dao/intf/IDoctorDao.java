package dao.intf;

import domain.Doctor;
import domain.Player;
import domain.Team;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IDoctorDao {

    @Select("select * from doctor")
    public List<Doctor> findAll();

    @Insert("insert into doctor(name,age,salary,country,type,sex) values(#{name},#{age},#{salary},#{country},#{type},#{sex})")
    public void save(Doctor doctor);

    @Delete("delete from doctor where id=#{id}")
    public void deleteById(int id);

    @Select("select * from doctor where name like #{text}")
    public List<Doctor> search(String text);

    @Select("select * from doctor where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property ="id" ),
            @Result(column = "name",property = "name"),
            @Result(column = "age",property = "age"),
            @Result(column = "salary",property = "salary"),
            @Result(column = "country",property = "country"),
            @Result(column = "type",property = "type"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "teamid",property = "team",javaType = Team.class,one = @One(select = "dao.intf.ITeamDao.findById"))
    })
    public Doctor details(int id);


    @Select("select * from doctor where teamid=#{teamid}")
    public List<Doctor> findByTeamId(int teamid);

    @Select("select * from doctor where teamid is null")
    public List<Doctor> select();

    @Select("select * from team")
    public List<Team> findAllTeams();

    @Update("update doctor set teamid=#{teamid} where id=#{doctorid}")
    public void point(@Param("teamid") int teamid,@Param("doctorid") int doctorid);

    @Update("update doctor set teamid=null where id=#{id}")
    public void destory(int id);
}

