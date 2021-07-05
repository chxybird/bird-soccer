package dao.intf;

import domain.Battle;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBattleDao {

    @Select("select * from battle")
    public List<Battle> findAll();

    @Insert("insert into battle(firstteamid,secondteamid,time,title,type) values(#{firstteamid},#{secondteamid},#{time},#{title},#{type})")
    public void save(Battle battle);

    @Select("select * from battle where title like #{text}")
    public List<Battle> search(String text);

    @Delete("delete from battle where id=#{id}")
    public void deleteById(int id);
}
