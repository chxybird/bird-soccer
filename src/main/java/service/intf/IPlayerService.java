package service.intf;

import domain.Player;
import domain.Team;

import java.util.List;

public interface IPlayerService {
    public List<Player> findAll(int page,int size);

    public void save(Player player);

    public void deleteById(int id);

    public List<Player> search(String text,int page,int size);

    public Player details(int id);

    public List<Player> select(int page,int size);

    public List<Team> findAllTeams();

    public void point(int teamid,int playerid);

    public void destory(int id);
}
