package by.tc.task33.service;

import by.tc.task33.entity.Medicine;

import java.util.List;

public interface Service {
    List<Medicine> getMedicineList(String filePath) throws ServiceException;
}