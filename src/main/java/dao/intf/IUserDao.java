package dao.intf;

import domain.Role;
import domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {
    @Select("select * from user")
    public List<User> findAll();

    @Insert("insert into user(username,password,phone,status,email) values(#{username},#{password},#{phone},#{status},#{email})")
    public void save(User user);

    @Delete("delete from user where id=#{id}")
    public void deleteById(int id);

    @Select("select * from user where username like #{text}")
    public List<User> search(String text);

    @Select("select * from user where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",
                    javaType = List.class,many =@Many(select = "dao.intf.IRoleDao.findByUserId") )
    })
    public User details(int id);

    @Select("select * from user where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many =@Many(select = "dao.intf.IRoleDao.findByUserId") )
    })
    public User findByUsername(String username);


    @Select("select * from user where id in(select userid from user_role where roleid=#{roleid})")
    public List<User> findByRoleId(int roleid);

    @Select("select * from user where id=#{id}")
    public User findById(int id);

    @Select("select * from role where id not in(select roleid from user_role where userid=#{userid})")
    public List<Role> findOtherRoleByUserId(int userid);


    @Insert("insert into user_role(userid,roleid) values(#{userId},#{roleid})")
    public void addRoleToUser(@Param("userId") int userId,@Param("roleid") int roleid);


    @Delete("delete from user_role where userid=#{userid} and roleid=#{roleid}")
    public void removeRoleFromUser(@Param("roleid") int roleid, @Param("userid") int userid);

    @Insert("insert into user(username,password,phone,email,status) values(#{username},#{password},#{phone},#{email},#{status})")
    public void register(User user);

}
