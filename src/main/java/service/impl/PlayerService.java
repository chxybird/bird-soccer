package service.impl;

import com.github.pagehelper.PageHelper;
import dao.intf.IPlayerDao;
import domain.Player;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.intf.IPlayerService;

import java.util.List;
@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private IPlayerDao playerDao;

    @Override
    public List<Player> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        List<Player> result = playerDao.findAll();
        return result;
    }

    @Override
    public void save(Player player) {
        playerDao.save(player);
    }

    @Override
    public void deleteById(int id) {
        playerDao.deleteById(id);
    }

    @Override
    public List<Player> search(String text,int page,int size) {
        PageHelper.startPage(page,size);
        List<Player> result = playerDao.search(text);
        return result;
    }

    @Override
    public Player details(int id) {
        Player player = playerDao.details(id);
        return player;
    }

    @Override
    public List<Player> select(int page,int size) {
        PageHelper.startPage(page,size);
        return playerDao.select();
    }

    @Override
    public List<Team> findAllTeams() {
        List<Team> allTeams = playerDao.findAllTeams();
        return allTeams;
    }

    @Override
    public void point(int teamid, int playerid) {
        playerDao.point(teamid,playerid);
    }

    @Override
    public void destory(int id) {
        playerDao.destory(id);
    }
}
