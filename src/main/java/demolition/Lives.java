package demolition;

import processing.core.PApplet;

import processing.core.PImage;

import processing.core.PFont;

public class Lives extends GameObject {

    private int lives;

    /**
    * Constructor for the lives
    * @param x, the x coordinate
    * @param y, the y coordinate
    * @param sprite, the image for the lives
    * @param lives, number of lives
    */
    public Lives(int x, int y, PImage sprite, int lives) {
        super(x,y,sprite);
        this.lives = lives;
    }

    /**
    * Draws the live icon
    * @param app, the app drawing
    */
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
        
        String display = Integer.toString(this.lives);
        app.text(display,165,43);
    }

    /**
    * Checks if the player is out of lives
    */
    public void tick() {
        if(this.lives == 0) {
            App.lose = true;
        }
    }

    /**
    * Decreases the player's lives
    */
    public void decreaseLives() {
        if(this.lives > 0) {
          this.lives--;
          App.reset = true;  
        }
    }

    /**
    * Returns the number of lives
    * @return number of lives
    */
    public int getLives() {
        return this.lives;
    }
}