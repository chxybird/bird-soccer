package service.intf;

import domain.Battle;

import java.util.List;

public interface IBattleService {

    public List<Battle> findAll(int page,int size);

    public void save(Battle battle);

    public List<Battle> search(String text,int page,int size);

    public void deleteById(int id);

}
