package DataBase;

import java.util.List;

public interface DAO<E, K> {
    List<E> getAll();

    void create(E entity);

    void delete(E entity);
}
