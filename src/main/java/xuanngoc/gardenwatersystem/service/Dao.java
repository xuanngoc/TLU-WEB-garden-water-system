package xuanngoc.gardenwatersystem.service;

import java.util.List;

public interface Dao<T> {
    T getById(Integer id);
    T saveOrUpdate(T t);
    void delete(Integer id);
    List<T> getAll();
}