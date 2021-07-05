package domain;

import org.springframework.format.annotation.DateTimeFormat;
import utils.DateUtils;

import java.util.Date;

public class SysLog {
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visittime;
    private String visittimeStr;
    private String username;
    private String ip;
    private Long executiontime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getVisittime() {
        return visittime;
    }

    public void setVisittime(Date visittime) {
        this.visittime = visittime;
    }

    public String getVisittimeStr() {
        if (visittime!=null){
            visittimeStr= DateUtils.DateToString(visittime,"yyyy-MM-dd HH:mm:ss");
        }
        return visittimeStr;
    }

    public void setVisittimeStr(String visittimeStr) {
        this.visittimeStr = visittimeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public Long getExecutiontime() {
        return executiontime;
    }

    public void setExecutiontime(Long executiontime) {
        this.executiontime = executiontime;
    }


}
