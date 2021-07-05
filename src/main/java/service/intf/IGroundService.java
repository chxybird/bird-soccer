package service.intf;

import domain.Ground;
import domain.Team;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IGroundService {

    public List<Ground> findAll(int page, int size);

    public void save(Ground doctor);

    public void deleteById(int id);

    public List<Ground> search(String text,int page,int size);

    public Ground details(int id);

    public List<Ground> select(int page,int size);

    public List<Team> findAllTeams();

    public void point(int teamid,int groundid);

    public void destory(int id);
}
