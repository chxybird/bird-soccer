package domain;


import org.springframework.format.annotation.DateTimeFormat;
import utils.DateUtils;

import java.util.Date;

public class History {
    private int id;
    private String firstteamname;
    private String secondteamname;
    private int firstgrade;
    private int secondgrade;
    private String description;
    private int state;
    private String stateStr;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private String timeStr;
    private String title;
    private int type;
    private String typeStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstteamname() {
        return firstteamname;
    }

    public void setFirstteamname(String firstteamname) {
        this.firstteamname = firstteamname;
    }

    public String getSecondteamname() {
        return secondteamname;
    }

    public void setSecondteamname(String secondteamname) {
        this.secondteamname = secondteamname;
    }

    public int getFirstgrade() {
        return firstgrade;
    }

    public void setFirstgrade(int firstgrade) {
        this.firstgrade = firstgrade;
    }

    public int getSecondgrade() {
        return secondgrade;
    }

    public void setSecondgrade(int secondgrade) {
        this.secondgrade = secondgrade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTimeStr() {
        if (time!=null){
            timeStr=DateUtils.DateToString(time,"yyyy-MM-dd");
        }
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStateStr() {
        if (state==0){
            stateStr="主队胜";
        }else if (state==1){
            stateStr="客队胜";
        }else if (state==2){
            stateStr="平局";
        }
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }
}
