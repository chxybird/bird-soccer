package service.intf;

import domain.Grade;

import java.util.List;

public interface IGradeService {

    public List<Grade> findAll();

    public Grade findById(int id);

    public void save(Grade grade);

    public void update(Grade grade);
}
