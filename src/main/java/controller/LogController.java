package controller;


import com.github.pagehelper.PageInfo;
import domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.intf.ILogService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("log")
public class LogController {

    @Autowired
    private ILogService logService;

    @RequestMapping("findAll")
    @RolesAllowed("ROLE_ROOT")
    public ModelAndView findAll(Integer page,Integer size){
        ModelAndView modelAndView=new ModelAndView();
        List<SysLog> result = logService.findAll(page, size);
        PageInfo pageInfo=new PageInfo(result);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("log-list");
        return modelAndView;
    }
}
