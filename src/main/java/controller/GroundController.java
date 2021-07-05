package controller;

import com.github.pagehelper.PageInfo;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.intf.IGroundService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/ground")
public class GroundController {

    @Autowired
    private IGroundService groundService;


    @RequestMapping("findAll")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView findAll(int page, int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Ground> result = groundService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("ground-list");
        return modelAndView;
    }
    @RequestMapping("save")
    @RolesAllowed({"ROLE_GROUND","ROLE_ROOT"})
    public void save(Ground ground, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        groundService.save(ground);
        request.getRequestDispatcher("/ground/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("deleteById")
    @RolesAllowed({"ROLE_GROUND","ROLE_ROOT"})
    public void delete(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        groundService.deleteById(id);
        request.getRequestDispatcher("/ground/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("/search")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView=new ModelAndView();
        String string = request.getParameter("text");
        String text="%"+string+"%";
        int page=1;
        int size=5;
        List<Ground> result = groundService.search(text, page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("ground-list");
        return modelAndView;
    }

    @RequestMapping("details")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView details(int id){
        ModelAndView modelAndView=new ModelAndView();
        Ground ground = groundService.details(id);
        modelAndView.addObject("result",ground);
        modelAndView.setViewName("ground-details");
        return modelAndView;
    }

    @RequestMapping("select")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView select(int page,int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Ground> result = groundService.select(page,size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("ground-list");
        return modelAndView;
    }

    //指定球队，查询可以指定的球队
    @RequestMapping("/pointTeam")
    @RolesAllowed({"ROLE_GROUND","ROLE_ROOT"})
    public ModelAndView pointTeam(int id){
        ModelAndView modelAndView=new ModelAndView();
        //查找所有球队信息
        List<Team> allTeams = groundService.findAllTeams();
        modelAndView.addObject("result",allTeams);
        //根据id查询该球员信息
        Ground ground = groundService.details(id);
        modelAndView.addObject("ground",ground);
        modelAndView.setViewName("ground-team-point");
        return modelAndView;
    }

    //球队正式指定
    @RequestMapping("/point")
    @RolesAllowed({"ROLE_GROUND","ROLE_ROOT"})
    public void point(int teamid,int groundid,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        groundService.point(teamid,groundid);
        request.getRequestDispatcher("/ground/findAll?page=1&size=5").forward(request,response);
    }

    //球队注销
    @RequestMapping("destory")
    @RolesAllowed({"ROLE_GROUND","ROLE_ROOT"})
    public void destory(int id,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        groundService.destory(id);
        request.getRequestDispatcher("/ground/findAll?page=1&size=5").forward(request,response);
    }
}
