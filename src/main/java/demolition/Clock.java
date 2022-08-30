package demolition;

import processing.core.PApplet;

import processing.core.PImage;

import processing.core.PFont;

public class Clock extends GameObject {

    private int timer;
    private int timerFPS;

    /**
    * The Constuctor for a Clock
    * @param x, the x value
    * @param y, the y value
    * @param sprite, the clock image
    * @param time, the time limit
    */
    public Clock(int x, int y, PImage sprite, int time) {
        super(x,y,sprite);
        this.timer = time;
        this.timerFPS = timer * App.FPS;
    }

    /**
    * Draws the clock image
    * @param app, the app displaying the clock
    */
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
        String display = Integer.toString(this.timer);
        app.text(display,295,43);
    }

    /**
    * Handles the calculations and decreases the timer
    */
    public void tick() {
        
        this.timerFPS--;
        if(this.timerFPS % App.FPS == 0) {
            this.timer--;
        }

        if(this.timer == 0) {
            App.lose = true;
        }
    }

    /**
    * Returns the timer
    * @return time
    */
    public int getTimer() {
        return this.timer;
    }

    /**
    * Returns the timer multiplied by the fps 
    * @return fps * time
    */
    public int getTimerFPS() {
        return this.timerFPS;
    }
}