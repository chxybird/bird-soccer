package service.impl;

import com.github.pagehelper.PageHelper;
import dao.intf.IDoctorDao;
import domain.Doctor;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.intf.IDoctorService;

import java.util.List;

@Service
public class DoctorService implements IDoctorService {

    @Autowired
    private IDoctorDao doctorDao;

    @Override
    public List<Doctor> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        List<Doctor> result = doctorDao.findAll();
        return result;
    }

    @Override
    public void save(Doctor doctor) {
        doctorDao.save(doctor);
    }

    @Override
    public void deleteById(int id) {
        doctorDao.deleteById(id);
    }

    @Override
    public List<Doctor> search(String text, int page, int size) {
        PageHelper.startPage(page,size);
        List<Doctor> result = doctorDao.search(text);
        return result;
    }

    @Override
    public Doctor details(int id) {
        return doctorDao.details(id);
    }

    @Override
    public List<Doctor> select(int page,int size) {
        return doctorDao.select();
    }

    @Override
    public List<Team> findAllTeams() {
        return doctorDao.findAllTeams();
    }

    @Override
    public void point(int teamid, int doctorid) {
        doctorDao.point(teamid,doctorid);
    }

    @Override
    public void destory(int id) {
        doctorDao.destory(id);
    }
}
