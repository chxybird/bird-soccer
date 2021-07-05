package dao.intf;

import domain.History;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHistoryDao {

    @Select("select * from history")
    public List<History> findAll();

    @Select("select * from history where id=#{id}")
    public History details(int id);

    @Insert("insert into history(firstteamname,secondteamname,firstgrade,secondgrade,description,state,time,title,type) values(#{firstteamname},#{secondteamname},#{firstgrade},#{secondgrade},#{description},#{state},#{time},#{title},#{type})")
    public void save(History history);

    @Select("select * from history where title like #{text}")
    public List<History> search(String text);

}
