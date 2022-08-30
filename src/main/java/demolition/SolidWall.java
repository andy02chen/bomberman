package demolition;

import processing.core.PApplet;

import processing.core.PImage;

public class SolidWall extends Tile {

    /**
    * Constructor for a Solid Wall
    * @param x, the x value
    * @param y, the y value
    * @param sprite, the image of the SolidWall
    */
    public SolidWall(int x, int y, PImage sprite) {
        super(x, y, sprite, false, false, false);
    }
}