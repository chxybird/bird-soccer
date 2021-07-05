package service.impl;

import com.github.pagehelper.PageHelper;
import dao.intf.IBattleDao;
import domain.Battle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.intf.IBattleService;

import java.util.List;

@Service
public class BattleService implements IBattleService {

    @Autowired
    private IBattleDao battleDao;


    @Override
    public List<Battle> findAll(int page ,int size) {
        PageHelper.startPage(page,size);
        return battleDao.findAll();
    }

    @Override
    public void save(Battle battle) {
        battleDao.save(battle);
    }

    @Override
    public List<Battle> search(String text, int page, int size) {
        PageHelper.startPage(page,size);
        return battleDao.search(text);
    }

    @Override
    public void deleteById(int id) {
        battleDao.deleteById(id);
    }
}
