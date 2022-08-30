package demolition;

import processing.core.PApplet;

import processing.core.PImage;

import java.util.ArrayList;

public class Bomb extends GameObject {

    private int frameCounter = 0;
    private int framesPerAnimation = 15;
    private int animationFrame = 1;
    private boolean toRemove = false;

    private boolean upBoom1 = true;
    private boolean upBoom2 = true;
    private boolean downBoom1 = true;
    private boolean downBoom2 = true;
    private boolean leftBoom1 = true;
    private boolean leftBoom2 = true;
    private boolean rightBoom1 = true;
    private boolean rightBoom2 = true;
 
    /**
    * Constructor for a bomb
    * @param x, the x coordinate
    * @param y, the y coordinate
    * @param sprite, the image for the bomb
    */
    public Bomb(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }

    /**
    * Draws the bomb on the screen
    * @param app, the app that displays the bomb
    */
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }

    /**
    * Handles the calculations for the bomb
    */
    public void tick() {
        frameCounter++;

        if(this.frameCounter == this.framesPerAnimation) {
            this.sprite = App.mapClass.getBombAnimation().get(this.animationFrame);
            this.animationFrame++;
            this.frameCounter = 0;

            if(this.animationFrame == 9) {
                this.toRemove = true;
                findBoom();
                App.mapClass.removeBombs();
                addExplosions();
            }
        }
    }

    /**
    * Returns whether the bomb should be removed
    * @return if it should be removed
    */
    public boolean getRemove() {
        return this.toRemove;
    }

    /**
    * Finds where the bomb's explosions should go
    */
    public void findBoom() {
        ArrayList<ArrayList<GameObject>> layout = Map.getMap();

        int bombX = this.x / Map.getTileWidth();
        int bombY = (this.y / Map.getTileWidth()) - Map.getPixelOffset();

        GameObject left1 = layout.get(bombY).get(bombX - 1);
        GameObject right1 = layout.get(bombY).get(bombX + 1);
        GameObject up1 = layout.get(bombY - 1).get(bombX);
        GameObject down1 = layout.get(bombY + 1).get(bombX);        

        try{
            GameObject left2 = layout.get(bombY).get(bombX - 2);

            if(((Tile)left2).isEmpty() == false && ((Tile)left2).isBreakable() == false) {
                this.leftBoom2 = false;
            }
        } catch (IndexOutOfBoundsException e) {
            this.leftBoom2 = false;
        }
        
        try{
            GameObject right2 = layout.get(bombY).get(bombX + 2);

            if(((Tile)right2).isEmpty() == false && ((Tile)right2).isBreakable() == false) {
                this.rightBoom2 = false;
            }
        } catch (IndexOutOfBoundsException e) {
            this.rightBoom2 = false;
        }
        
        try {
            GameObject up2 = layout.get(bombY - 2).get(bombX);

            if(((Tile)up2).isEmpty() == false && ((Tile)up2).isBreakable() == false) {
                this.upBoom2 = false;
            }
        } catch (IndexOutOfBoundsException e) {
            this.upBoom2 = false;
        }
        
        try{
            GameObject down2 = layout.get(bombY + 2).get(bombX);

            if(((Tile)down2).isEmpty() == false && ((Tile)down2).isBreakable() == false) {
                this.downBoom2 = false;
            }
        } catch (IndexOutOfBoundsException e) {
            this.downBoom2 = false;
        }
        
        if(((Tile)left1).isEmpty() == false) {
            this.leftBoom1 = false;
            this.leftBoom2 = false;

            if(((Tile)left1).isBreakable()) {
                this.leftBoom1 = true;
                this.leftBoom2 = false;
            }
        }

        if(((Tile)right1).isEmpty() == false) {
            this.rightBoom1 = false;
            this.rightBoom2 = false;

            if(((Tile)right1).isBreakable()) {
                this.rightBoom1 = true;
                this.rightBoom2 = false;
            }
        }

        if(((Tile)down1).isEmpty() == false) {
            this.downBoom1 = false;
            this.downBoom2 = false;

            if(((Tile)down1).isBreakable()) {
                this.downBoom1 = true;
                this.downBoom2 = false;
            }
        }

        if(((Tile)up1).isEmpty() == false) {
            this.upBoom1 = false;
            this.upBoom2 = false;

            if(((Tile)up1).isBreakable()) {
                this.upBoom1 = true;
                this.upBoom2 = false;
            }
        }
    }

    /**
    * Adds the explosions
    */
    public void addExplosions() {
        App.mapClass.addBoom(new Explosion(this.x, this.y, App.mapClass.getCentreBoom()));

        if(this.leftBoom1) {
            App.mapClass.addBoom(new Explosion(this.x - Map.getTileWidth(), this.y, App.mapClass.getHorizontalBoom()));
        }

        if(this.leftBoom2) {
            App.mapClass.addBoom(new Explosion(this.x - (2 * Map.getTileWidth()), this.y, App.mapClass.getHorizontalBoom()));
        }

        if(this.upBoom1) {
            App.mapClass.addBoom(new Explosion(this.x, this.y - Map.getTileWidth(), App.mapClass.getVerticalBoom()));
        }

        if(this.upBoom2) {
            App.mapClass.addBoom(new Explosion(this.x, this.y - (2 * Map.getTileWidth()), App.mapClass.getVerticalBoom()));
        }

        if(this.rightBoom1) {
            App.mapClass.addBoom(new Explosion(this.x + Map.getTileWidth(), this.y, App.mapClass.getHorizontalBoom()));
        }

        if(this.rightBoom2) {
            App.mapClass.addBoom(new Explosion(this.x + (2 * Map.getTileWidth()), this.y, App.mapClass.getHorizontalBoom()));
        }

        if(this.downBoom1) {
            App.mapClass.addBoom(new Explosion(this.x, this.y + Map.getTileWidth(), App.mapClass.getVerticalBoom()));
        }

        if(this.downBoom2) {
            App.mapClass.addBoom(new Explosion(this.x, this.y + (2 * Map.getTileWidth()), App.mapClass.getVerticalBoom()));
        }
    }
}