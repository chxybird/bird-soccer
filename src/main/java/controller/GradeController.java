package controller;

import domain.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.intf.IGradeService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("grade")
public class GradeController {

    @Autowired
    private IGradeService gradeService;

    @RequestMapping("findAll")
    @RolesAllowed({"ROLE_PLAYER","ROLE_USER","ROLE_ROOT","ROLE_COACH","ROLE_DOCTOR","ROLE_TEAM","ROLE_GROUND,ROLE_BATTLE"})
    public ModelAndView findAll(){
        ModelAndView modelAndView=new ModelAndView();
        List<Grade> result = gradeService.findAll();
        modelAndView.addObject("result",result);
        modelAndView.setViewName("grade-list");
        return modelAndView;
    }

}
