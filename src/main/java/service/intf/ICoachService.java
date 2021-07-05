package service.intf;

import domain.Coach;
import domain.Team;

import java.util.List;

public interface ICoachService {

    public List<Coach> findAll(int page,int size);

    public void save(Coach coach);

    public void deleteById(int id);

    public List<Coach> search(String text,int page,int size);

    public Coach details(int id);

    public List<Coach> select(int page,int size);

    public List<Team> findAllTeams();

    public void point(int teamid,int coachid);

    public void destory(int id);
}
