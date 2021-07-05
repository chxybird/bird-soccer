package service.intf;


import domain.History;

import java.util.List;

public interface IHistoryService {
    public List<History> findAll(int page,int size);

    public History details(int id);

    public void save(History history);

    public List<History> search(String text, int page, int size);
}
