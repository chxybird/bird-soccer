package service.impl;

import com.github.pagehelper.PageHelper;
import dao.intf.IPermissionDao;
import domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.intf.IPermissionService;

import java.util.List;

@Service
public class PermissionService implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Permission> result = permissionDao.findAll();
        return result;
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public void deleteById(int id) {
    permissionDao.deleteById(id);
    }

    @Override
    public List<Permission> search(String text, int page, int size) {
        PageHelper.startPage(page,size);
        List<Permission> result = permissionDao.search(text);
        return result;
    }

    @Override
    public Permission details(int id) {
        permissionDao.details(id);
        return permissionDao.details(id);
    }

    @Override
    public void remove(int[] ids, int roleid) {
        for (int permissionid:ids) {
            permissionDao.remove(roleid,permissionid);
        }
    }
}
