package controller;

import com.github.pagehelper.PageInfo;
import domain.Player;
import domain.Team;
import org.apache.ibatis.annotations.Many;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.intf.IPlayerService;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    IPlayerService playerService;

    @RequestMapping("/findAll")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView findAll(int page,int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Player> result = playerService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("player-list");
        return modelAndView;
    }

    @RequestMapping("save")
    @RolesAllowed({"ROLE_PLAYER","ROLE_ROOT"})
    public void save(Player player, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        playerService.save(player);
        request.getRequestDispatcher("/player/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("deleteById")
    @RolesAllowed({"ROLE_PLAYER","ROLE_ROOT"})
    public void delete(int id,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        playerService.deleteById(id);
        request.getRequestDispatcher("/player/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("/search")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public  ModelAndView search(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView=new ModelAndView();
        String string = request.getParameter("text");
        String text="%"+string+"%";
        int page=1;
        int size=5;
        List<Player> result = playerService.search(text, page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("player-list");
        return modelAndView;
    }

    @RequestMapping("details")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView details(int id){
        ModelAndView modelAndView=new ModelAndView();
        Player player = playerService.details(id);
        modelAndView.addObject("result",player);
        modelAndView.setViewName("player-details");
        return modelAndView;
    }

    @RequestMapping("select")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView select(int page,int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Player> result = playerService.select(page,size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("player-list");
        return modelAndView;
    }

    //指定球队，查询可以指定的球队
    @RequestMapping("/pointTeam")
    @RolesAllowed({"ROLE_PLAYER","ROLE_ROOT"})
    public ModelAndView pointTeam(int id){
        ModelAndView modelAndView=new ModelAndView();
        //查找所有球队信息
        List<Team> allTeams = playerService.findAllTeams();
        modelAndView.addObject("result",allTeams);
        //根据id查询该球员信息
        Player player = playerService.details(id);
        modelAndView.addObject("palyer",player);
        modelAndView.setViewName("player-team-point");
        return modelAndView;
    }

    //球队正式指定
    @RequestMapping("/point")
    @RolesAllowed({"ROLE_PLAYER","ROLE_ROOT"})
    public void point(int teamid,int playerid,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        playerService.point(teamid,playerid);
        request.getRequestDispatcher("/player/findAll?page=1&size=5").forward(request,response);
    }

    //球队注销
    @RequestMapping("destory")
    @RolesAllowed({"ROLE_PLAYER","ROLE_ROOT"})
    public void destory(int id,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        playerService.destory(id);
        request.getRequestDispatcher("/player/findAll?page=1&size=5").forward(request,response);
    }
}
