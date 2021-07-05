package controller;

import com.github.pagehelper.PageInfo;
import domain.Grade;
import domain.Ground;
import domain.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.intf.IBattleService;
import service.intf.IGradeService;
import service.intf.IHistoryService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("history")
public class HistoryController {
    @Autowired
    private IHistoryService historyService;

    @Autowired
    private IBattleService battleService;

    @Autowired
    private IGradeService gradeService;

    @RequestMapping("findAll")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView findAll(int page,int size){
        ModelAndView modelAndView=new ModelAndView();
        List<History> result = historyService.findAll(page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("history-list");
        return modelAndView;
    }

    @RequestMapping("details")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView details(int id){
        ModelAndView modelAndView=new ModelAndView();
        History history = historyService.details(id);
        modelAndView.addObject("result",history);
        modelAndView.setViewName("history-details");
        return modelAndView;
    }

    @RequestMapping("save")
    @RolesAllowed({"ROLE_ROOT","ROLE_BATTLE"})
    public void save(History history, int battleid,int firstteamid,int secondteamid,String firstteamname,String secondteamname,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //产生历史战绩
        historyService.save(history);
        //删除该条赛事信息。
        battleService.deleteById(battleid);
        //生成积分信息
        int firstgrade = history.getFirstgrade();
        int secondgrade = history.getSecondgrade();
        int state = history.getState();
        Grade grade=new Grade();
        //查找grade表中是否存在该数据
        Grade grade1 = gradeService.findById(firstteamid);
        System.out.println(grade1);
        Grade grade2 = gradeService.findById(secondteamid);
        System.out.println(grade2);
        if(grade1!=null&&grade2!=null) {
            System.out.println(1);
            if (state == 0) {
                //主队胜
                //更新胜利者
                grade.setTeamid(firstteamid);
                grade.setName(firstteamname);
                grade.setGet(history.getFirstgrade());
                grade.setLose(history.getSecondgrade());
                grade.setGrade(3);
                gradeService.update(grade);
                //更新失败者队伍
                grade.setTeamid(secondteamid);
                grade.setName(secondteamname);
                grade.setGet(history.getSecondgrade());
                grade.setLose(history.getFirstgrade());
                grade.setGrade(0);
                gradeService.update(grade);
            }

            if (state == 1) {
                //客队胜
                //更新胜利者
                grade.setTeamid(secondteamid);
                grade.setName(secondteamname);
                grade.setGet(history.getSecondgrade());
                grade.setLose(history.getFirstgrade());
                grade.setGrade(3);
                gradeService.update(grade);
                //更新失败者队伍
                grade.setTeamid(firstteamid);
                grade.setName(firstteamname);
                grade.setGet(history.getFirstgrade());
                grade.setLose(history.getSecondgrade());
                grade.setGrade(0);
                gradeService.update(grade);
            }
            if (state == 2) {
                //平局
                grade.setTeamid(firstteamid);
                grade.setName(firstteamname);
                grade.setGet(history.getFirstgrade());
                grade.setLose(history.getSecondgrade());
                grade.setGrade(1);
                gradeService.update(grade);
                grade.setTeamid(secondteamid);
                grade.setName(secondteamname);
                grade.setGet(history.getSecondgrade());
                grade.setLose(history.getFirstgrade());
                grade.setGrade(1);
                gradeService.update(grade);
            }

        }



            if (grade1!=null&&grade2==null){

                System.out.println(2);
            if (state == 0) {
                //主队胜
                //更新胜利者
                grade.setTeamid(firstteamid);
                grade.setName(firstteamname);
                grade.setGet(history.getFirstgrade());
                grade.setLose(history.getSecondgrade());
                grade.setGrade(3);
                gradeService.update(grade);
                //更新失败者队伍
                grade.setTeamid(secondteamid);
                grade.setName(secondteamname);
                grade.setGet(history.getSecondgrade());
                grade.setLose(history.getFirstgrade());
                grade.setGrade(0);
                gradeService.save(grade);
            }
            if (state == 1) {
                //客队胜
                //更新胜利者
                grade.setTeamid(secondteamid);
                grade.setName(secondteamname);
                grade.setGet(history.getSecondgrade());
                grade.setLose(history.getFirstgrade());
                grade.setGrade(3);
                gradeService.save(grade);
                //更新失败者队伍
                grade.setTeamid(firstteamid);
                grade.setName(firstteamname);
                grade.setGet(history.getFirstgrade());
                grade.setLose(history.getSecondgrade());
                grade.setGrade(0);
                gradeService.update(grade);
            }
            if (state == 2) {
                //平局
                grade.setTeamid(firstteamid);
                grade.setName(firstteamname);
                grade.setGet(history.getFirstgrade());
                grade.setLose(history.getSecondgrade());
                grade.setGrade(1);
                gradeService.update(grade);
                grade.setTeamid(secondteamid);
                grade.setName(secondteamname);
                grade.setGet(history.getSecondgrade());
                grade.setLose(history.getFirstgrade());
                grade.setGrade(1);
                gradeService.save(grade);
            }
        }
            if (grade1==null&&grade2!=null) {
                System.out.println(3);
                if (state == 0) {
                    //主队胜
                    //更新胜利者
                    grade.setTeamid(firstteamid);
                    grade.setName(firstteamname);
                    grade.setGet(history.getFirstgrade());
                    grade.setLose(history.getSecondgrade());
                    grade.setGrade(3);
                    gradeService.save(grade);
                    //更新失败者队伍
                    grade.setTeamid(secondteamid);
                    grade.setName(secondteamname);
                    grade.setGet(history.getSecondgrade());
                    grade.setLose(history.getFirstgrade());
                    grade.setGrade(0);
                    gradeService.update(grade);
                }
                if (state == 1) {
                    //客队胜
                    //更新胜利者
                    grade.setTeamid(secondteamid);
                    grade.setName(secondteamname);
                    grade.setGet(history.getSecondgrade());
                    grade.setLose(history.getFirstgrade());
                    grade.setGrade(3);
                    gradeService.update(grade);
                    //更新失败者队伍
                    grade.setTeamid(firstteamid);
                    grade.setName(firstteamname);
                    grade.setGet(history.getFirstgrade());
                    grade.setLose(history.getSecondgrade());
                    grade.setGrade(0);
                    gradeService.save(grade);
                }
                if (state == 2) {
                    //平局
                    grade.setTeamid(firstteamid);
                    grade.setName(firstteamname);
                    grade.setGet(history.getFirstgrade());
                    grade.setLose(history.getSecondgrade());
                    grade.setGrade(1);
                    gradeService.save(grade);
                    grade.setTeamid(secondteamid);
                    grade.setName(secondteamname);
                    grade.setGet(history.getSecondgrade());
                    grade.setLose(history.getFirstgrade());
                    grade.setGrade(1);
                    gradeService.update(grade);
                }
            }

            if (grade1 == null && grade2 == null) {
                System.out.println(4);
                if (state == 0) {
                    //主队胜
                    //更新胜利者
                    grade.setTeamid(firstteamid);
                    grade.setName(firstteamname);
                    grade.setGet(history.getFirstgrade());
                    grade.setLose(history.getSecondgrade());
                    grade.setGrade(3);
                    gradeService.save(grade);
                    //更新失败者队伍
                    grade.setTeamid(secondteamid);
                    grade.setName(secondteamname);
                    grade.setGet(history.getSecondgrade());
                    grade.setLose(history.getFirstgrade());
                    grade.setGrade(0);
                    gradeService.save(grade);
                }
                if (state == 1) {
                    //客队胜
                    //更新胜利者
                    grade.setTeamid(secondteamid);
                    grade.setName(secondteamname);
                    grade.setGet(history.getSecondgrade());
                    grade.setLose(history.getFirstgrade());
                    grade.setGrade(3);
                    gradeService.save(grade);
                    //更新失败者队伍
                    grade.setTeamid(firstteamid);
                    grade.setName(firstteamname);
                    grade.setGet(history.getFirstgrade());
                    grade.setLose(history.getSecondgrade());
                    grade.setGrade(0);
                    gradeService.save(grade);
                }
                if (state == 2) {
                    //平局
                    grade.setTeamid(firstteamid);
                    grade.setName(firstteamname);
                    grade.setGet(history.getFirstgrade());
                    grade.setLose(history.getSecondgrade());
                    grade.setGrade(1);
                    gradeService.save(grade);
                    grade.setTeamid(secondteamid);
                    grade.setName(secondteamname);
                    grade.setGet(history.getSecondgrade());
                    grade.setLose(history.getFirstgrade());
                    grade.setGrade(1);
                    gradeService.save(grade);
                }
            }
        request.getRequestDispatcher("/history/findAll?page=1&size=20").forward(request,response);
    }

    @RequestMapping("search")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView=new ModelAndView();
        String string = request.getParameter("text");
        String text="%"+string+"%";
        int page=1;
        int size=20;
        List<History> result = historyService.search(text, page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("history-list");
        return modelAndView;
    }
}
