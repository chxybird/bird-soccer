package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.intf.ITeamDao;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.intf.ITeamService;

import java.util.List;

@Service
public class TeamService implements ITeamService {
    @Autowired
    private ITeamDao teamDao;
    @Override
    public List<Team> findAll() {
        List<Team> teams = teamDao.findAll();
        return teams;
    }
    //分页查询
    @Override
    public List<Team> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Team> result = teamDao.findAll();
        return result;
    }

    @Override
    public void save(Team team) {
        teamDao.save(team);
    }

    @Override
    public void delete(int id) {
        teamDao.delete(id);
    }

    @Override
    public List<Team> search(String string,int page, int size) {
        PageHelper.startPage(page,size);
        List<Team> result = teamDao.search(string);
        return result;
    }

    @Override
    public Team details(int id) {
        return teamDao.details(id);
    }
}
