package tn.esprit.services;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    void add(T t) throws SQLException;

    void addd(T t) throws SQLException;
    void update(T t) throws  SQLException;

    void delete(int id) throws  SQLException;

    List<T> diplayList() throws  SQLException;
}
