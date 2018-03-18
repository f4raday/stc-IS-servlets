package model.DataInterfaces.interfaces;

import java.util.List;

public interface IDefaultDAO<T, NUM> {
    T getByID(NUM id);

    List<T> getAll();

    NUM insert(T entry);

    NUM update(T entry);

    NUM delete(T entry);
}
