package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import domain.Battle;
import domain.Coach;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.intf.IBattleService;
import service.intf.ITeamService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("battle")
public class BattleController {
    @Autowired
    private IBattleService battleService;

    @Autowired
    private ITeamService teamService;

    @RequestMapping("findAll")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView findAll(int page, int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Battle> result = battleService.findAll(page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("battle-list");
        return modelAndView;
    }

    @RequestMapping("findteam")
    @RolesAllowed({"ROLE_ROOT","ROLE_BATTLE"})
    public ModelAndView findteam(){
        ModelAndView modelAndView=new ModelAndView();
        //查出数据库拥有的球队信息
        List<Team> teams = teamService.findAll();
        modelAndView.addObject("teams",teams);
        modelAndView.setViewName("battle-add");
        return modelAndView;
    }

    //确定一个比赛的两只球队

    @RequestMapping("save")
    @RolesAllowed({"ROLE_ROOT","ROLE_BATTLE"})
    public void save(Battle battle, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //建立两个球队产生一个比赛的关系。
        battleService.save(battle);
        request.getRequestDispatcher("/battle/findAll?page=1&size=20").forward(request,response);
    }

    //结束赛事产生战绩

    @RequestMapping("product")
    @RolesAllowed({"ROLE_ROOT","ROLE_BATTLE"})
    public ModelAndView product(Battle battle){
        ModelAndView modelAndView=new ModelAndView();
        //根据拿到的主队id和客队id查找出主队和客队
        Team firstTeam = teamService.details(battle.getFirstteamid());
        Team secondTeam = teamService.details(battle.getSecondteamid());
        modelAndView.addObject("firstTeam",firstTeam);
        modelAndView.addObject("secondTeam",secondTeam);
        modelAndView.addObject("battle",battle);
        modelAndView.setViewName("battle-history-add");
        return modelAndView;
    }

    @RequestMapping("/search")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public  ModelAndView search(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView=new ModelAndView();
        String string = request.getParameter("text");
        String text="%"+string+"%";
        int page=1;
        int size=20;
        List<Battle> result = battleService.search(text, page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("battle-list");
        return modelAndView;
    }

    @RequestMapping("deleteById")
    @RolesAllowed({"ROLE_ROOT","ROLE_BATTLE"})
    public void deleteById(int id,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        battleService.deleteById(id);
        request.getRequestDispatcher("/battle/findAll?page=1&size=20").forward(request,response);
    }
}
