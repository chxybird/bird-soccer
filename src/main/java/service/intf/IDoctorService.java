package service.intf;

import domain.Doctor;
import domain.Team;

import java.util.List;

public interface IDoctorService {

    public List<Doctor> findAll(int page,int size);

    public void save(Doctor doctor);

    public void deleteById(int id);

    public List<Doctor> search(String text,int page,int size);

    public Doctor details(int id);

    public List<Doctor> select(int page,int size);

    public List<Team> findAllTeams();

    public void point(int teamid,int doctorid);

    public void destory(int id);
}
