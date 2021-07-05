package service.intf;

import domain.Role;
import domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface IUserService extends UserDetailsService {

    public List<User> findAll(int page, int size);

    public void save(User user);

    public void deleteById(int id);

    public List<User> search(String text, int page, int size);

    public User details(int id);

    public User findById(int id);

    List<Role> findOtherRoleByUserId(int userid);

    public void addRoleToUser(int userId,int[] ids);

    public void removeRoleFromUser(int[] ids,int userid);

    public void register(User user);

}
