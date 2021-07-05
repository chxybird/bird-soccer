package domain;

import org.springframework.format.annotation.DateTimeFormat;
import utils.DateUtils;

import java.util.Date;

public class Battle {
    private int id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private String timeStr;
    private int type;
    private String typeStr;
    private int firstteamid;
    private int secondteamid;

    public int getFirstteamid() {
        return firstteamid;
    }

    public void setFirstteamid(int firstteamid) {
        this.firstteamid = firstteamid;
    }

    public int getSecondteamid() {
        return secondteamid;
    }

    public void setSecondteamid(int secondteamid) {
        this.secondteamid = secondteamid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
//        if(timeStr!=null){
//            time = DateUtils.StringToDate(timeStr, "yyyy-MM-dd");
//        }
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTimeStr() {
        if (time!=null){
            timeStr= DateUtils.DateToString(time,"yyyy-MM-dd ");
        }
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeStr() {
        if (type==0){
            typeStr="友谊赛";
        }else if (type==1){
            typeStr="杯赛";
        }else if (type==2){
            typeStr="联赛";
        }
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
}
