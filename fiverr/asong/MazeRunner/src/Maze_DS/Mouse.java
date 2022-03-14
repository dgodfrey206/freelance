package Maze_DS;

//import Exceptions.EmptyCollectionException;
import asset.AbstractMouse;
import grid.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Mouse extends AbstractMouse {

    private ArrayListStack<Location> crumbs;

    /**
     * Default Constructor of Mouse Object.
     */
    public Mouse() {
        super();
        crumbs = new ArrayListStack<>();
    }

    /**
     * Constructor used for the purposes of unit testing.
     *
     * @param crumbs a previously existing stack of crumbs
     */
    public Mouse(ArrayListStack<Location> crumbs) {
        this.crumbs = crumbs;
    }

    /**
     * Determines which adjacent locations are empty for the mouse to traverse.
     *
     * @return a list of traversable empty locations
     */
    @Override
    protected List<Location> getEmptyLocations() {
        List<Location> emptyLocations = new ArrayList<>(4);

        // Create a list of locations adjacent to the mouse
        List<Location> adjacentLocations = getAdjacentLocations();

        // Examine each location adjacent to the mouse
        Location location;
        for (int i = 0; i < adjacentLocations.size(); i++) {
            location = adjacentLocations.get(i);

            // The location is valid if it can be traversed
            if (canTraverse(location)) {
                // If adjacent to cheese, go directly to it and find cheese
                if (seesCheeseAt(location)) {
                    moveTo(location);
                    foundCheese();
                    return emptyLocations;
                }

                /* Add the location to the empty locations list if it is not a 
                rock and has not been visited */
                if (!seesRockAt(location) && !hasVisited(location)) {
                    emptyLocations.add(location);
                }
            } else {
                /* Visited locations and rocks are considered invalid and 
                should be removed from the list of adjacent locations */
                adjacentLocations.remove(location);
            }
        }
        return emptyLocations;
    }

    /**
     * Controls the logic of how the mouse will move.
     *
     * @param emptyLocations a list of traversable empty locations
     */
    @Override
    public void move(List<Location> emptyLocations) {
        /*
        Check the number of empty locations available adjacent to the mouse's 
        current location.
        If any option exists, push the current location to the crumbs and choose 
        a new location to move to randomly.
        Otherwise, move to the last location visited and pop it from the crumbs.
         */
        if (emptyLocations.isEmpty()) {
          moveTo(crumbs.pop());
        } else {
          crumbs.push(getLocation());
          Random rand = new Random();
          moveTo(emptyLocations.get(rand.nextInt(emptyLocations().size())));
        }
    }

    /**
     * Determines whether the mouse has already visited this location.
     *
     * @param location the location to check
     * @return true if the mouse has visited this location
     */
    @Override
    public boolean hasVisited(Location location) {
        boolean visited = false;
        try {
            Location l = crumbs.peek(); // pop()
            if (l == location) {
                visited = true;
            }
        } catch (/*EmptyCollectionException*/ EmptyStackException e) {
            e.printStackTrace();
        }
        return visited;
    }

    /**
     * Instructs the mouse to move to the specified location.
     *
     * @param location the location to move to
     */
    @Override
    public void moveTo(Location location) {
        super.moveTo(location);
    }

    /**
     * Accessor method for crumbs field for the purposes of unit testing.
     *
     * @return the mouse's stack of crumbs
     */
    public ArrayListStack<Location> getCrumbs() {
        return crumbs;
    }

    /**
     * Generates a list of adjacent locations.
     *
     * @return the list of adjacent locations
     */
    @Override
    protected List<Location> getAdjacentLocations() {
        return super.getAdjacentLocations();
    }

    /**
     * Gets the current location of the mouse.
     *
     * @return the current location of the mouse
     */
    @Override
    public Location getLocation() {
        return super.getLocation();
    }

    /**
     * Checks whether the mouse can traverse a location.
     *
     * @param location the location to check
     * @return whether the mouse can traverse the location
     */
    @Override
    protected boolean canTraverse(Location location) {
        return super.canTraverse(location);
    }

    /**
     * Checks whether the mouse sees cheese at a location.
     *
     * @param location the location to check
     * @return whether the mouse sees cheese at the location
     */
    @Override
    protected boolean seesCheeseAt(Location location) {
        return super.seesCheeseAt(location);
    }

    /**
     * Checks whether the mouse sees a rock at a location.
     *
     * @param location the location to check
     * @return whether the mouse sees a rock at the location
     */
    @Override
    protected boolean seesRockAt(Location location) {
        return super.seesRockAt(location);
    }

    /**
     * Performed when cheese has been found by the mouse to complete the maze
     * run.
     */
    private void foundCheese() {
        super.setFoundCheese(true);
    }

}
