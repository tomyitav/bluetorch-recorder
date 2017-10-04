package iaf.bluetorch.trackstore;

import static org.junit.Assert.*;
import iaf.bluetorch.db.entities.TrackEntity;
import iaf.bluetorch.entitystore.EntityStore;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntityStoreTest {

	static EntityStore t;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t = new EntityStore();
	}
	
	@Before
	public void setUpBeforeTest() throws Exception {
		t.clear();
	}
	
	@Test
	public void testBasicAdd() {
		assertEquals(t.size(), 0);
		t.add(new TrackEntity(1, 1));
		assertEquals(t.size(), 1);
	}
	
	@Test
	public void testClone() {
		assertEquals(t.size(), 0);
		t.add(new TrackEntity(1, 1));
		try {
			EntityStore cloneT = (EntityStore) t.clone();
			assertNotEquals(t, cloneT);
			assertNotEquals(t.iterator(), cloneT.iterator());
			assertEquals(t.size(), cloneT.size());
		} catch (CloneNotSupportedException e) {
			fail("Clone exceptions!!!");
		}
	}
	
	@Test
	public void testAddSameKey() {
		assertEquals(t.size(), 0);
		t.add(new TrackEntity(1, 1));
		assertEquals(t.size(), 1);
		t.add(new TrackEntity(2, 1));
		assertEquals(t.size(), 2);
		t.add(new TrackEntity(2, 3));
		assertEquals(t.size(), 2);
	}
	
}
