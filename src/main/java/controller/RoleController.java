package controller;
import com.github.pagehelper.PageInfo;
import domain.Permission;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.intf.IRoleService;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @RequestMapping("findAll")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView findAll(int page,int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Role> result = roleService.findAll(page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("save")
    @RolesAllowed("ROLE_ROOT")
    public void save(Role role, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        roleService.save(role);
        request.getRequestDispatcher("/role/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("deleteById")
    @RolesAllowed("ROLE_ROOT")
    public void deleteById(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        roleService.deleteById(id);
        request.getRequestDispatcher("/role/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("/search")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView=new ModelAndView();
        String string = request.getParameter("text");
        String text="%"+string+"%";
        int page=1;
        int size=5;
        List<Role> result = roleService.search(text, page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("details")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView details(int id){
        ModelAndView modelAndView=new ModelAndView();
        Role result = roleService.details(id);
        modelAndView.addObject("result",result);
        modelAndView.setViewName("role-details");
        return modelAndView;
    }

    @RequestMapping("findRoleByIdWithAllPermission")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView findRoleByIdWithAllPermission(int id){
        ModelAndView modelAndView=new ModelAndView();
        //根据角色id查询角色信息。
        Role role = roleService.findById(id);
        modelAndView.addObject("role",role);
        //根据角色id查询权限信息（如果该角色拥有的权限信息则不查询）
        List<Permission> otherPermissionByRoleId = roleService.findOtherPermissionByRoleId(id);
        modelAndView.addObject("otherPermissionByRoleId",otherPermissionByRoleId);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }
    @RequestMapping("addPermissionToRole")
    @RolesAllowed("ROLE_ROOT")
    public void addPermissionToRole(int roleid,int[] ids,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        roleService.addPermissionToRole(roleid,ids);
        request.getRequestDispatcher("/role/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("removepermission")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView removepermission(int id){
        ModelAndView modelAndView=new ModelAndView();
        //根据角色id查询该角色并且包含旗下的权限信息
        Role role = roleService.details(id);
        modelAndView.addObject("role",role);
        modelAndView.setViewName("role-permission-remove");
        return modelAndView;
    }
}
