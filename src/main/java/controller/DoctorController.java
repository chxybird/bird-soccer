package controller;

import com.github.pagehelper.PageInfo;
import domain.Coach;
import domain.Doctor;
import domain.Player;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.intf.IDoctorService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;

    @RequestMapping("findAll")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView findAll(int page, int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Doctor> result = doctorService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("doctor-list");
        return modelAndView;
    }
    @RequestMapping("save")
    @RolesAllowed({"ROLE_DOCTOR","ROLE_ROOT"})
    public void save(Doctor doctor, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doctorService.save(doctor);
        request.getRequestDispatcher("/doctor/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("deleteById")
    @RolesAllowed({"ROLE_DOCTOR","ROLE_ROOT"})
    public void delete(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doctorService.deleteById(id);
        request.getRequestDispatcher("/doctor/findAll?page=1&size=5").forward(request,response);
    }

    @RequestMapping("/search")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView=new ModelAndView();
        String string = request.getParameter("text");
        String text="%"+string+"%";
        int page=1;
        int size=5;
        List<Doctor> result = doctorService.search(text, page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("doctor-list");
        return modelAndView;
    }

    @RequestMapping("details")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView details(int id){
        ModelAndView modelAndView=new ModelAndView();
        Doctor doctor = doctorService.details(id);
        modelAndView.addObject("result",doctor);
        modelAndView.setViewName("doctor-details");
        return modelAndView;
    }

    @RequestMapping("select")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView select(int page,int size){
        ModelAndView modelAndView=new ModelAndView();
        List<Doctor> result = doctorService.select(page,size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("doctor-list");
        return modelAndView;
    }

    //指定球队，查询可以指定的球队
    @RequestMapping("/pointTeam")
    @RolesAllowed({"ROLE_DOCTOR","ROLE_ROOT"})
    public ModelAndView pointTeam(int id){
        ModelAndView modelAndView=new ModelAndView();
        //查找所有球队信息
        List<Team> allTeams = doctorService.findAllTeams();
        modelAndView.addObject("result",allTeams);
        //根据id查询该球员信息
        Doctor doctor = doctorService.details(id);
        modelAndView.addObject("doctor",doctor);
        modelAndView.setViewName("doctor-team-point");
        return modelAndView;
    }

    //球队正式指定
    @RequestMapping("/point")
    @RolesAllowed({"ROLE_DOCTOR","ROLE_ROOT"})
    public void point(int teamid,int doctorid,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doctorService.point(teamid,doctorid);
        request.getRequestDispatcher("/doctor/findAll?page=1&size=5").forward(request,response);
    }

    //球队注销
    @RequestMapping("destory")
    @RolesAllowed({"ROLE_DOCTOR","ROLE_ROOT"})
    public void destory(int id,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doctorService.destory(id);
        request.getRequestDispatcher("/doctor/findAll?page=1&size=5").forward(request,response);
    }
}
