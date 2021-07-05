package dao.intf;

import domain.Permission;
import domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {

    @Select("select * from role where id in(select roleid from user_role where userid=#{userid})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "description",property = "description"),
            @Result(column = "id",property = "permissions",
                    javaType = List.class,many = @Many(select = "dao.intf.IPermissionDao.findByRoleId"))
    })
    public List<Role> findByUserId(int userid);

    @Select("select * from role")
    public List<Role> findAll();

    @Insert("insert into role(roleName,description) values(#{roleName},#{description})")
    public void save(Role role);

    @Delete("delete from role where id=#{id}")
    public void deleteById(int id);

    @Select("select * from role where roleName like #{text}")
    public List<Role> search(String text);

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "description",property = "description"),
            @Result(column = "id",property = "permissions",javaType = List.class,many = @Many(select = "dao.intf.IPermissionDao.findByRoleId")),
            @Result(column = "id",property = "users",javaType = List.class,many = @Many(select = "dao.intf.IUserDao.findByRoleId"))
    })
    public Role details(int id);

    @Select("select * from role where id in(select roleid from role_permission where permissionid=#{permissionid})")
    public List<Role> findByPermissionId(int permissionid);


    @Select("select * from role where id=#{id}")
    public Role findById(int id);

    @Select("select * from permission where id not in(select permissionid from role_permission where roleid=#{roleid})")
    public List<Permission> findOtherPermissionByRoleId(int roleid);

    @Insert("insert into role_permission(roleid,permissionid) values(#{roleid},#{permissionid})")
    public void addPermissionToRole(@Param("roleid") int roleid,@Param("permissionid") int permissionid);
}
