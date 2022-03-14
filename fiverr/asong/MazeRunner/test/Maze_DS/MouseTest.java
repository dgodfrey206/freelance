package Maze_DS;

//import Exceptions.EmptyCollectionException;
import asset.Cheese;
import asset.StaticMaze;
import grid.Location;
import java.util.List;
import java.util.*;
//import org.junit.Test;
//import static org.junit.Assert.*;

/**
 *
 * @author Paul Franklin
 */



public class MouseTest {

static void assertEquals(Object a, Object b) {
  if (!a.equals(b)) {
    throw new NullPointerException();
  }
}

static void assertTrue(boolean b) {
  assertEquals(b, true);
}

static void assertFalse(boolean b) {
  assertEquals(b,false);
}

static void assertNotEquals(Object a, Object b) {
  if (a.equals(b)) {
    throw new NullPointerException();
  }
}

static void fail(String a) {
  System.out.println(a);
}
    
    private StaticMaze maze;
    private Mouse mouse;
    private final int TRAVERSABLE_EMPTY_LOCATIONS = 282;

    
    public MouseTest() {
        maze = new StaticMaze(9, 16);
        mouse = new Mouse();
        maze.printStaticWorld(mouse, new Cheese());
    }

    
    //@Test
    public void testMove() {
        for (int i = 0; i < 2 * TRAVERSABLE_EMPTY_LOCATIONS + 1; i++) {
            
            int crumbsSize = mouse.getCrumbs().size();
            List<Location> emptyLocations = mouse.getEmptyLocations();
            Location crumbsTop = null;
            
            // Get peek if exists
            if (!mouse.getCrumbs().isEmpty()) {
                try {
                    crumbsTop = mouse.getCrumbs().peek();
                }
                //EmptyCollectionException
                catch ( EmptyStackException e) {
                    fail("Unexpected ECE - testMove");
                }
            }
            
            mouse.act();
            
            if (mouse.hasFoundCheese()) {
                break;
            }
            
            // Case: Empty locations exist
            if (emptyLocations.size() > 0) {
                assertEquals(crumbsSize + 1, mouse.getCrumbs().size());
                assertTrue(emptyLocations.contains(mouse.getLocation()));
                assertNotEquals(mouse.getLocation(), crumbsTop);
            }
            
            // Case: No empty locations exist
            if (emptyLocations.isEmpty()) {
                assertEquals(crumbsSize - 1, mouse.getCrumbs().size());
                assertFalse(emptyLocations.contains(mouse.getLocation()));
                assertEquals(mouse.getLocation(), crumbsTop);
            }
        }
        
        assertTrue(mouse.hasFoundCheese());
    }
    public static void main(String[] args) {
      MouseTest t = new MouseTest();
      t.testMove();
    }
}
