package controller;

import com.github.pagehelper.PageInfo;
import domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.intf.IPermissionService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("findAll")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView findAll(int page,int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Permission> result = permissionService.findAll(page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    @RequestMapping("save")
    @RolesAllowed("ROLE_ROOT")
    public void save(Permission permission, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        permissionService.save(permission);
        request.getRequestDispatcher("/permission/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("deleteById")
    @RolesAllowed("ROLE_ROOT")
    public void delete(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        permissionService.deleteById(id);
        request.getRequestDispatcher("/permission/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("/search")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView=new ModelAndView();
        String string = request.getParameter("text");
        String text="%"+string+"%";
        int page=1;
        int size=5;
        List<Permission> result = permissionService.search(text, page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    @RequestMapping("details")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView details(int id){
        ModelAndView modelAndView=new ModelAndView();
        Permission result = permissionService.details(id);
        modelAndView.addObject("result",result);
        modelAndView.setViewName("permission-details");
        return modelAndView;
    }

    @RequestMapping("remove")
    @RolesAllowed("ROLE_ROOT")
    public void remove(int[] ids,int roleid,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        permissionService.remove(ids,roleid);
        request.getRequestDispatcher("/role/findAll?page=1&size=5").forward(request,response);
    }

}
