package service.impl;

import com.github.pagehelper.PageHelper;
import dao.intf.ILogDao;
import domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.intf.ILogService;

import java.util.List;

@Service
public class LogService implements ILogService {

    @Autowired
    private ILogDao logDao;

    @Override
    public List<SysLog> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        return logDao.findAll();
    }

    @Override
    public void save(SysLog log) {
        logDao.save(log);
    }
}
