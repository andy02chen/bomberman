package demolition;

import processing.core.PApplet;

import processing.core.PImage;

public abstract class Tile extends GameObject {

    /**
    * Whether the tile is breakable
    */
    protected boolean breakable;

    /**
    * Variable for is the tile is empty
    */
    protected boolean empty;

    /**
    * Whether or not the tile is goal
    */
    protected boolean goal;

    /**
    * Constuctor for a Tile
    * @param x, the x value
    * @param y, the y value
    * @param sprite, the image of the tile
    * @param breakable, is the tile able to be broken
    * @param empty, is the tile empty
    * @param goal, is the tile the goal of the level
    */
    public Tile(int x, int y, PImage sprite, boolean breakable, boolean empty, boolean goal){
        super(x, y, sprite);
        this.breakable = breakable;
        this.empty = empty;
        this.goal = goal;
    }
    
    /**
    * Returns whether the tile is empty
    * @return Is it empty
    */
    public boolean isEmpty() {
        return this.empty;
    }

    /**
    * Returns whether the tile is breakable
    * @return is it breakable
    */
    public boolean isBreakable() {
        return this.breakable;
    }
}