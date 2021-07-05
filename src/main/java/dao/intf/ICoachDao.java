package dao.intf;

import domain.Coach;
import domain.Player;
import domain.Team;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICoachDao {

    @Select("select * from coach")
    public List<Coach> findAll();

    @Insert("insert into coach(name,sex,salary,ability,type,age,country) values(#{name},#{sex},#{salary},#{ability},#{type},#{age},#{country})")
    public void save(Coach coach);

    @Delete("delete from coach where id=#{id}")
    public void deleteById(int id);

    @Select("select * from coach where name like #{text}")
    public List<Coach> search(String text);


    @Select("select * from coach where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property ="id" ),
            @Result(column = "name",property = "name"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "salary",property = "salary"),
            @Result(column = "ability",property = "ability"),
            @Result(column = "type",property = "type"),
            @Result(column = "age",property = "age"),
            @Result(column = "country",property = "country"),
            @Result(column = "teamid",property = "team",javaType = Team.class,one = @One(select = "dao.intf.ITeamDao.findById"))
    })
    public Coach details(int id);


    @Select("select * from coach where teamid=#{teamid}")
    public List<Coach> findByTeamId(int teamid);

    @Select("select * from coach where teamid is null")
    public List<Coach> select();

    @Select("select * from team")
    public List<Team> findAllTeams();

    @Update("update coach set teamid=#{teamid} where id=#{coachid}")
    public void point(@Param("teamid") int teamid,@Param("coachid") int coachid);

    @Update("update coach set teamid=null where id=#{id}")
    public void destory(int id);

}
