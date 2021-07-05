package service.intf;

import domain.SysLog;

import java.util.List;

public interface ILogService {

    public List<SysLog> findAll(int page,int size);

    public void save(SysLog log);
}
