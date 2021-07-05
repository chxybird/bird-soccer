package controller;

import com.github.pagehelper.PageInfo;
import domain.Coach;
import domain.Player;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.intf.ICoachService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private ICoachService coachService;

    @RequestMapping("findAll")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView findAll(int page, int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Coach> result = coachService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("coach-list");
        return modelAndView;
    }
    @RequestMapping("save")
    @RolesAllowed({"ROLE_COACH","ROLE_ROOT"})
    public void save(Coach coach, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        coachService.save(coach);
        request.getRequestDispatcher("/coach/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("deleteById")
    @RolesAllowed({"ROLE_COACH","ROLE_ROOT"})
    public void delete(int id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        coachService.deleteById(id);
        request.getRequestDispatcher("/coach/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("/search")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public  ModelAndView search(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView=new ModelAndView();
        String string = request.getParameter("text");
        String text="%"+string+"%";
        int page=1;
        int size=5;
        List<Coach> result = coachService.search(text, page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("coach-list");
        return modelAndView;
    }

    @RequestMapping("details")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView details(int id){
        ModelAndView modelAndView=new ModelAndView();
        Coach coach = coachService.details(id);
        modelAndView.addObject("result",coach);
        modelAndView.setViewName("coach-details");
        return modelAndView;
    }

    @RequestMapping("select")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView select(int page,int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Coach> result = coachService.select(page,size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("coach-list");
        return modelAndView;
    }

    @RequestMapping("pointTeam")
    @RolesAllowed({"ROLE_COACH","ROLE_ROOT"})
    public ModelAndView pointTeam(int id){
        ModelAndView modelAndView=new ModelAndView();
        //查询所有球队的信息
        List<Team> allTeams = coachService.findAllTeams();
        //根据教练的id查询该教练的信息。
        Coach coach = coachService.details(id);
        modelAndView.addObject("result",allTeams);
        modelAndView.addObject("coach",coach);
        modelAndView.setViewName("coach-team-point");
        return modelAndView;
    }

    //球队正式指定
    @RequestMapping("/point")
    @RolesAllowed({"ROLE_COACH","ROLE_ROOT"})
    public void point(int teamid,int coachid,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        coachService.point(teamid,coachid);
        request.getRequestDispatcher("/coach/findAll?page=1&size=5").forward(request,response);
    }

    //球队注销
    @RequestMapping("destory")
    @RolesAllowed({"ROLE_COACH","ROLE_ROOT"})
    public void destory(int id,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        coachService.destory(id);
        request.getRequestDispatcher("/coach/findAll?page=1&size=5").forward(request,response);
    }
}
