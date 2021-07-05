package dao.intf;

import domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {

    @Select("select * from permission")
    public List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission);

    @Delete("delete from permission where id=#{id}")
    public void deleteById(int id);

    @Select("select * from permission where permissionName like #{text}")
    public List<Permission> search(String text);

    @Select("select * from permission where id in(select permissionid from role_permission where roleid=#{roleid} )")
    public List<Permission> findByRoleId(int roleid);

    @Select("select * from permission where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissionName",column = "permissionName"),
            @Result(property = "url",column = "url"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "dao.intf.IRoleDao.findByPermissionId"))
    })
    public Permission details(int id);

    @Delete("delete from role_permission where roleid=#{roleid} and permissionid=#{permissionid}")
    public void remove(@Param("roleid") int roleid,@Param("permissionid") int permissionid);
}
