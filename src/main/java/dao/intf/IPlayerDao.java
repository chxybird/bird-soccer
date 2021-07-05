package dao.intf;

import domain.Player;
import domain.Team;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlayerDao {

    @Select("select * from player")
    public List<Player> findAll();

    @Insert("insert into player(name,sex,age,salary,country,height,weight,location,foot) values(#{name},#{sex},#{age},#{salary},#{country},#{height},#{weight},#{location},#{foot})")
    public void save(Player player);

    @Delete("delete from player where id=#{id}")
    public void deleteById(int id);

    @Select("select * from player where name like #{text}")
    public List<Player> search(String text);

    @Select("select * from player where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property ="id" ),
            @Result(column = "name",property = "name"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "age",property = "age"),
            @Result(column = "salary",property = "salary"),
            @Result(column = "country",property = "country"),
            @Result(column = "height",property = "height"),
            @Result(column = "weight",property = "weight"),
            @Result(column = "location",property = "location"),
            @Result(column = "foot",property = "foot"),
            @Result(column = "teamid",property = "team",javaType =Team.class,one = @One(select = "dao.intf.ITeamDao.findById"))
    })
    public Player details(int id);


    @Select("select * from player where teamid=#{teamid}")
    public List<Player> findByTeamId(int teamid);

    @Select("select * from player where teamid is null")
    public List<Player> select();

    @Select("select * from team")
    public List<Team> findAllTeams();

    @Update("update player set teamid=#{teamid} where id=#{playerid}")
    public void point(@Param("teamid") int teamid,@Param("playerid") int playerid);

    @Update("update player set teamid=null where id=#{id}")
    public void destory(int id);

}