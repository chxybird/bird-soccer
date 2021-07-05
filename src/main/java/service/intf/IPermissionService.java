package service.intf;

import domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll(int page, int size);

    public void save(Permission permission);

    public void deleteById(int id);

    public List<Permission> search(String text, int page, int size);

    public Permission details(int id);

    public void remove(int[] ids,int roleid);
}
