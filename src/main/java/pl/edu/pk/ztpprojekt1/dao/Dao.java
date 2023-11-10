package pl.edu.pk.ztpprojekt1.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K,T> {
    Optional<T> get(K id);
    List<T> getAll();
    void save(T t);
    void update(K id, T t);
    T delete(K id);
}
