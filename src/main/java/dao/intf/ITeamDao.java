package dao.intf;

import domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITeamDao {
    @Select("select * from team")
    public List<Team> findAll();

    @Insert("insert into team(name,city,time,boss,address) values(#{name},#{city},#{time},#{boss},#{address})")
    public void save(Team team);

    @Delete("delete from team where id=#{id}")
    public void delete(int id);

    @Select("select * from team where name like #{string}")
    public List<Team> search(String string);

    @Select("select * from team where id=#{id}")
    public Team findById(int id);

    @Select("select * from team where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "age",column = "age"),
            @Result(property = "salary",column = "salary"),
            @Result(property = "country",column = "country"),
            @Result(property = "height",column = "height"),
            @Result(property = "weight",column = "weight"),
            @Result(property = "location",column = "location"),
            @Result(property = "foot",column = "foot"),
            @Result(property ="playes",column = "id" ,javaType = List.class,many = @Many(select = "dao.intf.IPlayerDao.findByTeamId")),
            @Result(property = "coaches",column = "id" ,javaType = List.class,many = @Many(select = "dao.intf.ICoachDao.findByTeamId")),
            @Result(property = "doctors",column = "id" ,javaType = List.class,many = @Many(select = "dao.intf.IDoctorDao.findByTeamId")),
            @Result(property = "grounds",column = "id" ,javaType = List.class,many = @Many(select = "dao.intf.IGroundDao.findByTeamId"))
    })
    public Team details(int id);
}
