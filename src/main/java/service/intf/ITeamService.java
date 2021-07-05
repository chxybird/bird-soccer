package service.intf;

import domain.Team;

import java.util.List;

public interface ITeamService {

    public List<Team> findAll();

    public List<Team> findAll(int page,int size);

    public void save(Team team);

    public void delete(int id);

    public List<Team> search(String string,int page,int size);

    public Team details(int id);
}
