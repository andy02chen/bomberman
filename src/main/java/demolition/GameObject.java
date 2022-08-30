package demolition;

import processing.core.PApplet;

import processing.core.PImage;

public abstract class GameObject {
    /**
    * The image of the GameObject
    */
    protected PImage sprite;

    /**
    * The x value
    */
    protected int x;

    /**
    * The y value
    */
    protected int y;

    /**
    * Constructor for the GameObject
    * @param x, the x value
    * @param y, the y value
    * @param sprite, the Object's image
    */
    public GameObject(int x, int y, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    /**
    * Returns the x value
    * @return x
    */
    public int getX(){
        return this.x;
    }

    /**
    * Returns the y value
    * @return y
    */
    public int getY() {
        return this.y;
    }

    /**
    * Draws the GameObject
    * @param app, the app drawing the object
    */
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }

    /**
    * Returns the width of the object
    * @return width
    */
    public int getWidth() {
        return this.sprite.width;
    }

    /**
    * Returns the height of the object
    * @return height
    */
    public int getHeight() {
        return this.sprite.height;
    }
}