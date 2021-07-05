package controller;

import com.github.pagehelper.PageInfo;
import domain.Role;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.intf.IUserService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("findAll")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView findAll(int page,int size){
        ModelAndView modelAndView=new ModelAndView();
        List<User> result = userService.findAll(page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("save")
    @RolesAllowed("ROLE_ROOT")
    public void save(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.save(user);
        request.getRequestDispatcher("/user/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("deleteById")
    @RolesAllowed("ROLE_ROOT")
    public void deleteById(int id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.deleteById(id);
        request.getRequestDispatcher("/user/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("/search")
    @RolesAllowed("ROLE_ROOT")
    public  ModelAndView search(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView=new ModelAndView();
        String string = request.getParameter("text");
        String text="%"+string+"%";
        System.out.println(text);
        int page=1;
        int size=5;
        List<User> result = userService.search(text, page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("details")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView details(int id){
        ModelAndView modelAndView=new ModelAndView();
        User result = userService.details(id);
        modelAndView.addObject("user",result);
        modelAndView.setViewName("user-details");
        return modelAndView;
    }


    //查询用户以及用户可以添加的角色（用户有的角色信息不回被查询到）
    @RequestMapping("/findUserByIdWithAllRole")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView findUserByIdWithAllRole(int id){
        ModelAndView modelAndView=new ModelAndView();
        //根据用户id查询用户信息
        User user = userService.findById(id);
        //根据用户id查询可以添加的角色信息
        List<Role> otherRoleByUserId = userService.findOtherRoleByUserId(id);
        modelAndView.addObject("user",user);
        modelAndView.addObject("otherRoleByUserId",otherRoleByUserId);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    //给用户添加角色
    @RequestMapping("addRoleToUser")
    @RolesAllowed("ROLE_ROOT")
    public void addRoleToUser(int userId,int[] ids,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //向关联表中插入数据
        userService.addRoleToUser(userId,ids);
        request.getRequestDispatcher("/user/findAll?page=1&size=5").forward(request,response);
    }

    //移除拥有的角色
    @RequestMapping("removerole")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView removerole(int id){
        ModelAndView modelAndVie=new ModelAndView();
        User user = userService.details(id);
        modelAndVie.addObject("user",user);
        modelAndVie.setViewName("user-role-remove");
        return modelAndVie;
    }

    @RequestMapping("remove")
    @RolesAllowed("ROLE_ROOT")
    public void remove(int[] ids,int userid,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        userService.removeRoleFromUser(ids,userid);
        request.getRequestDispatcher("/user/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("register")
    public ModelAndView register(User user){
        ModelAndView modelAndView=new ModelAndView();
        userService.register(user);
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
