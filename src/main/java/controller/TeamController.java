package controller;

import com.github.pagehelper.PageInfo;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.intf.ITeamService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private ITeamService teamService;
    @RequestMapping("/find")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView findAll(){
        ModelAndView modelAndView=new ModelAndView();
        List<Team> teams = teamService.findAll();
        modelAndView.addObject("teams",teams);
        modelAndView.setViewName("team-list");
        return modelAndView;
    }

    @RequestMapping("/findAll")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView findAll(int page,int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Team> result = teamService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("team-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    @RolesAllowed({"ROLE_TEAM","ROLE_ROOT"})
    public void save(Team team, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        teamService.save(team);
        request.getRequestDispatcher("/team/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("deleteById")
    @RolesAllowed({"ROLE_TEAM","ROLE_ROOT"})
    public void delete(int id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        teamService.delete(id);
        request.getRequestDispatcher("/team/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("/search")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public  ModelAndView search(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView=new ModelAndView();
        String string = request.getParameter("text");
        String text="%"+string+"%";
        int page=1;
        int size=5;
        List<Team> result = teamService.search(text, page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("team-list");
        return modelAndView;
    }

    @RequestMapping("details")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView details(int id){
        ModelAndView modelAndView=new ModelAndView();
        Team team = teamService.details(id);
        modelAndView.addObject("result",team);
        modelAndView.setViewName("team-details");
        return modelAndView;
    }
}
