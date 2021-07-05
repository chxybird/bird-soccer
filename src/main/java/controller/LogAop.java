package controller;

import domain.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import service.intf.ILogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/*      spring中关于aop的概念
        1.连接点：业务层接口中的方法就叫连接点。
        2.切入点: 业务层接口中被增强的方法就叫做切入点。
        3.通知：拦截过后进行的处理，也就是方法的增强。
        4.织入 :创建代理对象，增强方法的过程。
        5.切面：切入点和通知的结合。

        @Aspect 的作用就是直接将此类作为一个切面。

        execution（表达式）  表达式：访问修饰符 返回值 包名.类名.方法名（参数列表） 其中访问权限修饰符可以省略。
        execution(public void service.Impl.MoneyServiceImpl.save())
        参数类型如果是对象类型需要些全限定名
        比如int类型直接写int  String类型就需要些 java.lang.String
        全通配写法：省略了访问修饰符，表示当前的类的所有方法都会被通知。
        * *..*.*（..）

        JoinPoint对象封装了SpringAop中切面方法的信息
*/


@Component
@Aspect
public class LogAop {


    @Autowired
    private ILogService logService;

    @Autowired
    private HttpServletRequest request;

    private Date visittime;//访问的开始时间。


    //前置通知 获取开始时间 执行的类是哪一个，执行的是哪一个方法。
    @Before("execution(* controller.*.*(..))")
    public void doBefore() throws Exception {
        //获取操作的时间
        visittime=new Date();
    }

    //后置通知
    @After("execution(* controller.*.*(..))")
    public void doAfter(){
        //获取操作的时长
        long time=new Date().getTime()-visittime.getTime();

        //获取ip
        String ip = request.getRemoteAddr();

        //获取当前操作的用户
        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
        User user =(User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //讲得到的信息封装到日志类中
        SysLog log=new SysLog();
        log.setExecutiontime(time);
        log.setIp(ip);
        log.setUsername(username);
        log.setVisittime(visittime);

        //调用service完成数据库的操作
        logService.save(log);
    }

}
