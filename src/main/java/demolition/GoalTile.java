package demolition;

import processing.core.PApplet;

import processing.core.PImage;

public class GoalTile extends Tile {

    /**
    * Constructor for a Goal Wall
    * @param x, the x coordinate
    * @param y, the y coordinate
    * @param sprite, the image for the Goal Wall
    */
    public GoalTile(int x, int y, PImage sprite) {
        super(x, y, sprite, false, true, true);
    }
}