package demolition;

import processing.core.PApplet;

import processing.core.PImage;

public class BrokenWall extends Tile {

    /**
    * Constructor for a Broken Wall
    * @param x, the x coordinate
    * @param y, the y coordinate
    * @param sprite, the image for the Broken Wall
    */
    public BrokenWall(int x, int y, PImage sprite) {
        super(x, y, sprite, true, false, false);
    }
}