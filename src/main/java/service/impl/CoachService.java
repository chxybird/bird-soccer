package service.impl;

import com.github.pagehelper.PageHelper;
import dao.intf.ICoachDao;
import domain.Coach;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.intf.ICoachService;

import java.util.List;

@Service
public class CoachService implements ICoachService {
    @Autowired
    private ICoachDao coachDao;
    @Override
    public List<Coach> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        List<Coach> result = coachDao.findAll();
        return result;
    }

    @Override
    public void save(Coach coach) {
        coachDao.save(coach);
    }

    @Override
    public void deleteById(int id) {
        coachDao.deleteById(id);
    }

    @Override
    public List<Coach> search(String text,int page,int size) {
        PageHelper.startPage(page,size);
        List<Coach> result = coachDao.search(text);
        return result;
    }

    @Override
    public Coach details(int id) {
        return coachDao.details(id);
    }

    @Override
    public List<Coach> select(int page,int size) {
        return coachDao.select();
    }

    @Override
    public List<Team> findAllTeams() {
        return coachDao.findAllTeams();
    }

    @Override
    public void point(int teamid, int coachid) {
        coachDao.point(teamid,coachid);
    }

    @Override
    public void destory(int id) {
        coachDao.destory(id);
    }
}
