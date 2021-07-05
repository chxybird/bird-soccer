package service.intf;

import domain.Permission;
import domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll(int page, int size);

    public void save(Role role);

    public void deleteById(int id);

    public List<Role> search(String text, int page, int size);

    public Role details(int id);

    public Role findById(int id);

    public List<Permission> findOtherPermissionByRoleId(int roleid);

    public void addPermissionToRole(int roleid,int[] ids);
}
