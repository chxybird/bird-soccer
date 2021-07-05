package dao.intf;


import domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILogDao {

    @Select("select * from syslog order by id desc")
    public List<SysLog> findAll();

    @Insert("insert into syslog(visittime,username,ip,executiontime) values(#{visittime},#{username},#{ip},#{executiontime})")
    public void save(SysLog log);
}
