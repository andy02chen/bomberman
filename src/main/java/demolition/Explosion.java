package demolition;

import processing.core.PApplet;

import processing.core.PImage;

import java.util.ArrayList;

public class Explosion extends GameObject {

    private int frameCounter = 0;
    private int framesPerAnimation = 30;
    private boolean toRemove = false;

    /**
    * Constructor for an Explosion
    * @param x, the x coordinate
    * @param y, the y coordinate
    * @param sprite, the image for the Explosion
    */
    public Explosion(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }

    /**
    * Calculates what the explosion destroys
    */
    public void tick() {
        this.frameCounter++;       

        if(this.frameCounter == this.framesPerAnimation) {
            ArrayList<ArrayList<GameObject>> layout = Map.getMap();
            ArrayList<Person> people = App.mapClass.getPeople();
            int bombX = this.x / Map.getTileWidth();
            int bombY = (this.y / Map.getTileWidth()) - Map.getPixelOffset();
            GameObject wall = layout.get(bombY).get(bombX);

            if(((Tile)wall).isBreakable()) {
                App.mapClass.changeWall(bombY, bombX, new EmptyTile(this.x, this.y, App.mapClass.getEmptyTile()));
            }
            
            this.toRemove = true;
            App.mapClass.removeBooms();
        }
    }

    /**
    * Returns if the explosions should be removed
    * @return if it is to be removed
    */
    public boolean getRemove() {
        return this.toRemove;
    }
}