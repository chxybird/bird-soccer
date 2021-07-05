package dao.intf;

import domain.Ground;
import domain.Player;
import domain.Team;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGroundDao {

    @Select("select * from ground")
    public List<Ground> findAll();

    @Insert("insert into ground(name,city,count,time,type) values(#{name},#{city},#{count},#{time},#{type})")
    public void save(Ground ground);

    @Delete("delete from ground where id=#{id}")
    public void deleteById(int id);

    @Select("select * from ground where name like #{text}")
    public List<Ground> search(String text);


    @Select("select * from ground where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property ="id" ),
            @Result(column = "name",property = "name"),
            @Result(column = "city",property = "city"),
            @Result(column = "count",property = "count"),
            @Result(column = "time",property = "time"),
            @Result(column = "type",property = "type"),
            @Result(column = "teamid",property = "team",javaType = Team.class,one = @One(select = "dao.intf.ITeamDao.findById"))
    })
    public Ground details(int id);


    @Select("select * from ground where teamid=#{teamid}")
    public List<Ground> findByTeamId(int teamid);

    @Select("select * from ground where teamid is null")
    public List<Ground> select();

    @Select("select * from team")
    public List<Team> findAllTeams();

    @Update("update ground set teamid=#{teamid} where id=#{groundid}")
    public void point(@Param("teamid") int teamid,@Param("groundid") int groundid);

    @Update("update ground set teamid=null where id=#{id}")
    public void destory(int id);
}
