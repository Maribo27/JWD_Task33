package by.tc.task33.dao;

import by.tc.task33.entity.Medicine;

import java.util.List;

public interface DAO {
    List<Medicine> getMedicineList() throws DAOException;
}