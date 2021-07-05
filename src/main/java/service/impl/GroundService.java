package service.impl;

import com.github.pagehelper.PageHelper;
import dao.intf.IGroundDao;
import domain.Ground;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.intf.IGroundService;

import java.util.List;

@Service
public class GroundService implements IGroundService {

    @Autowired
    private IGroundDao groundDao;
    @Override
    public List<Ground> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Ground> result = groundDao.findAll();
        return result;
    }

    @Override
    public void save(Ground ground) {
        groundDao.save(ground);
    }

    @Override
    public void deleteById(int id) {
        groundDao.deleteById(id);
    }

    @Override
    public List<Ground> search(String text,int page,int size) {
        PageHelper.startPage(page,size);
        List<Ground> result = groundDao.search(text);
        return result;
    }

    @Override
    public Ground details(int id) {
        return groundDao.details(id);
    }

    @Override
    public List<Ground> select(int page,int size) {
        return groundDao.select();
    }

    @Override
    public List<Team> findAllTeams() {
        return groundDao.findAllTeams();
    }

    @Override
    public void point(int teamid, int groundid) {
        groundDao.point(teamid,groundid);
    }

    @Override
    public void destory(int id) {
        groundDao.destory(id);
    }

}
