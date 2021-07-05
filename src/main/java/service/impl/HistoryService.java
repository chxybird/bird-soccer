package service.impl;
import com.github.pagehelper.PageHelper;
import dao.intf.IHistoryDao;
import domain.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.intf.IHistoryService;

import java.util.List;

@Service
public class HistoryService implements IHistoryService {

    @Autowired
    private IHistoryDao historyDao;

    @Override
    public List<History> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<History> result = historyDao.findAll();
        return result;
    }

    @Override
    public History details(int id) {
        return historyDao.details(id);
    }

    @Override
    public void save(History history) {
        historyDao.save(history);
    }

    @Override
    public List<History> search(String text, int page, int size) {
        PageHelper.startPage(page,size);
        return historyDao.search(text);
    }
}
