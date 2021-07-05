package service.impl;

import com.github.pagehelper.PageHelper;
import dao.intf.IRoleDao;
import domain.Permission;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.intf.IRoleService;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Role> result = roleDao.findAll();
        return result;
    }

    @Override
    public void save(Role role) {
    roleDao.save(role);
    }

    @Override
    public void deleteById(int id) {
        roleDao.deleteById(id);
    }

    @Override
    public List<Role> search(String text, int page, int size) {
        PageHelper.startPage(page,size);
        List<Role> result = roleDao.search(text);
        return result;
    }

    @Override
    public Role details(int id) {
        return roleDao.details(id);
    }

    @Override
    public Role findById(int id) {
        Role role = roleDao.findById(id);
        return role;
    }

    @Override
    public List<Permission> findOtherPermissionByRoleId(int roleid) {
        List<Permission> otherPermissionByRoleId = roleDao.findOtherPermissionByRoleId(roleid);
        return otherPermissionByRoleId;
    }

    @Override
    public void addPermissionToRole(int roleid, int[] ids) {
        for (int permissionid:ids) {
            roleDao.addPermissionToRole(roleid,permissionid);
        }
    }
}
