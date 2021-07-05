package domain;

import org.springframework.format.annotation.DateTimeFormat;
import utils.DateUtils;

import java.util.Date;

public class Ground {
    private int id;
    private String name;
    private String city;
    private Long count;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private String timeStr;
    private int type;
    private String typeStr;
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getTimeStr() {
        if (time!=null){
           timeStr= DateUtils.DateToString(time,"yyyy-MM-dd");
        }
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeStr() {
        if (type==0){
            typeStr="小型";
        }else if (type==1){
            typeStr="中型";
        }else if (type==2){
            typeStr="大型";
        }else if (type==3){
            typeStr="巨型";
        }
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
}
