package demolition;

import processing.core.PApplet;

import processing.core.PImage;

public class EmptyTile extends Tile {

    /**
    * Constructor for a Empty Wall
    * @param x, the x coordinate
    * @param y, the y coordinate
    * @param sprite, the image for the Empty Wall
    */
    public EmptyTile(int x, int y, PImage sprite) {
        super(x, y, sprite, false, true, false);
    }
}