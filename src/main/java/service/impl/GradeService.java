package service.impl;

import dao.intf.IGradeDao;
import domain.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.intf.IGradeService;

import java.util.List;

@Service
public class GradeService implements IGradeService {
    @Autowired
    private IGradeDao gradeDao;

    @Override
    public List<Grade> findAll() {
        return gradeDao.findAll();
    }

    @Override
    public Grade findById(int id) {
        return gradeDao.findById(id);
    }

    @Override
    public void save(Grade grade) {
        gradeDao.save(grade);
    }

    @Override
    public void update(Grade grade) {
        gradeDao.update(grade);
    }
}
