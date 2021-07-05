package domain;

import org.springframework.format.annotation.DateTimeFormat;
import utils.DateUtils;

import java.util.Date;
import java.util.List;

public class Team {
    private int id;
    private String name;
    private String city;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private String timeStr;
    private String boss;
    private String address;

    private List<Player> playes;
    private List<Coach> coaches;
    private List<Doctor> doctors;
    private List<Ground> grounds;

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Ground> getGrounds() {
        return grounds;
    }

    public void setGrounds(List<Ground> grounds) {
        this.grounds = grounds;
    }

    public List<Player> getPlayes() {
        return playes;
    }

    public void setPlayes(List<Player> playes) {
        this.playes = playes;
    }

    public String getTimeStr() {
        if(time!=null){
           timeStr = DateUtils.DateToString(time, "yyyy-MM-dd");
        }
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }


    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", time=" + time +
                ", boss='" + boss + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
