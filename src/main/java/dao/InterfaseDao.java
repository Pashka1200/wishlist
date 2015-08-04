package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pavlo on 18.07.15.
 */
public interface InterfaseDao<T> {
    public void add(T t) throws SQLException;
    public void delete(T t) throws SQLException;
    public T get(long id) throws SQLException;
    public List<T> getAll() throws SQLException;
    public void update(T t) throws SQLException;
}
