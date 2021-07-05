package service.impl;

import com.github.pagehelper.PageHelper;
import dao.intf.IUserDao;
import domain.Role;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.intf.IUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public List<User> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<User> result = userDao.findAll();
        return result;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Override
    public List<User> search(String text, int page, int size) {
        PageHelper.startPage(page,size);
        return userDao.search(text);
    }

    //springsecurity框架的方法
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User result = userDao.findByUsername(username);
        org.springframework.security.core.userdetails.User user=new org.springframework.security.core.userdetails.User(result.getUsername(),"{noop}"+result.getPassword(),result.getStatus()==0?false:true,true,true,true, getAuthority(result.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<SimpleGrantedAuthority>();
        for (Role role:roles) {
            list.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return list;
    }

    @Override
    public User details(int id) {
        return userDao.details(id);
    }

    @Override
    public User findById(int id) {
        User user = userDao.findById(id);
        return user;
    }

    @Override
    public List<Role> findOtherRoleByUserId(int userid) {
        List<Role> otherRoleByUserId = userDao.findOtherRoleByUserId(userid);
        return otherRoleByUserId;
    }

    @Override
    public void addRoleToUser(int userId, int[] ids) {
        for (int roleid:ids) {
            userDao.addRoleToUser(userId,roleid);
        }
    }

    @Override
    public void removeRoleFromUser(int[] ids, int userid) {
        for (int roleid:ids) {
            userDao.removeRoleFromUser(roleid,userid);
        }
    }

    @Override
    public void register(User user) {
        userDao.register(user);
    }
}
