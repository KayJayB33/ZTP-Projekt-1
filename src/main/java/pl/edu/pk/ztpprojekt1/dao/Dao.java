package pl.edu.pk.ztpprojekt1.dao;

import java.util.List;
import java.util.Optional;

/**
 * Interfejs dla klas warstwy dostępu do danych
 * @param <K> typ klucza encji
 * @param <T> typ encji
 */
public interface Dao<K,T> {
    Optional<T> get(K id);
    List<T> getAll();
    void save(T t);
    void update(K id, T t);
    T delete(K id);
}
