package by.tc.task33.dao.impl;

import by.tc.task33.dao.DAO;
import by.tc.task33.dao.DAOFactory;
import by.tc.task33.entity.Medicine;
import entity.TestEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StAXParserTest {
	private String filePath;
	private List<Medicine> medicines;

	@Before
	public void setUp() throws Exception {
		filePath = TestEntity.getFile(this.getClass());
		medicines = TestEntity.getMedicines();
	}

	@Test
	public void parseFile() throws Exception {
		final List<Medicine> expected = medicines;
		final List<Medicine> actual;

		DAOFactory factory = DAOFactory.getInstance();
		DAO entityDAO = factory.getStAXParser();
		entityDAO.parseFile(filePath);
		actual = entityDAO.getMedicines();

		assertEquals(expected, actual);
	}
}