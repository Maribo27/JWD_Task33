package by.tc.task33.dao.impl;

import by.tc.task33.dao.DAO;
import by.tc.task33.dao.DAOException;
import by.tc.task33.dao.parser.ParserException;
import by.tc.task33.dao.parser.impl.SAXFile;
import by.tc.task33.entity.Medicine;

import java.net.URL;
import java.util.List;

public class DAOImpl implements DAO {
    private static final String FILE_NAME = "Medicines.xml";
    private static final String OPEN_SQUARE = "%5B";
    private static final String CLOSE_SQUARE = "%5D";
    private static final String OPEN = "[";
    private static final String CLOSE = "]";

    public List<Medicine> getMedicineList() throws DAOException{
        URL fileURL = this.getClass().getClassLoader().getResource(FILE_NAME);
        String filePath = null;
        if (fileURL != null) {
            filePath = fileURL.getPath();

            filePath = filePath.replaceAll(OPEN_SQUARE, OPEN);
            filePath = filePath.replaceAll(CLOSE_SQUARE, CLOSE);
        }
        SAXFile saxFile = new SAXFile(filePath);
        List<Medicine> medicines;
        try {
            saxFile.readFile();
            medicines = saxFile.getMedicines();
            return medicines;
        } catch (ParserException e) {
            throw new DAOException(e.getMessage());
        }
    }
}