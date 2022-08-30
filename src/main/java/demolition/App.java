package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

import processing.data.JSONObject;
import processing.data.JSONArray;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class App extends PApplet {
    /**
    * Width of the window
     */
    public static final int WIDTH = 480;

    /**
    * Height of the window
     */
    public static final int HEIGHT = 480;

    /**
    * The number of frames per second
     */
    public static final int FPS = 60;
    
    /**
    * Specifies whether the app should reset the map or not
    * True = reset and false = not reset;
     */
    public static boolean reset = true;

    /**
    * Player loses
     */
    public static boolean lose = false;

    /**
    * Player wins
     */
    public static boolean win = false;

    /**
    * Did the player press left
     */
    public static boolean leftPressed = false;

    /**
    * Did the player press right
     */
    public static boolean rightPressed = false;

    /**
    * Did the player press up
     */
    public static boolean upPressed = false;

    /**
    * Did the player press down
     */
    public static boolean downPressed = false;

    /**
    * Did the player press space
     */
    public static boolean spacePressed = false;

    /**
    * Lives class
    */
    public static Lives noLives;

    /**
    * Player class
    */
    public static Player player;

    /**
    * Timer class
    */
    public static Clock timer;

    /**
    * The font used
    */
    public static PFont font;

    /**
    * Map class
    */
    public static Map mapClass;

    /**
    * Goal class
    */
    public static Tile goal;

    public App() {
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
    * Loads the images and the font to be used later.
    */
    public void setup() {
        frameRate(FPS);
        
        mapClass = new Map(this);
        mapClass.setup();

        font = this.createFont("src/main/resources/PressStart2P-Regular.ttf",21);
        this.textFont(font);
        this.textSize(21);
        this.fill(0);
    }

    /**
    * Responsible for displaying the objects of the game.
    */
    public void draw() {
        background(239, 159, 0);

        if(!lose && !win) {
            mapClass.displayLevel(this);
            this.noLives.draw(this);
            this.noLives.tick();
            this.timer.draw(this); 
            this.timer.tick();
            
            mapClass.bombDraw(this);
            mapClass.bombTick();

            mapClass.explosionsDraw(this);
            mapClass.explosionsTick();

            mapClass.peopleDraw(this);
            mapClass.peopleTick();
            

        } else if (lose && !win) {
            this.text("GAME OVER",100, 240);
        } else if(win) {
            this.text("YOU WIN",100, 240);
        }
    }  

    /**
    * Checks for user input
    */
    public void keyPressed() {
        if (this.player.getMoved() == false) {
            if(this.keyCode == 37) {
                this.leftPressed = true;
            } else if(this.keyCode == 38) {
                this.upPressed = true;
            } else if(this.keyCode == 39) {
                this.rightPressed = true;
            } else if(this.keyCode == 40) {
                this.downPressed = true;
            }
        }

        if(this.keyCode == 32) {
            if(!this.spacePressed) {
                mapClass.addBomb(new Bomb(this.player.getX(), this.player.getY() + mapClass.getExtraPlayerHeight(), mapClass.getBomb1()));
                this.spacePressed = true;
            }
            
        }
    }

    /**
    * Checks for when the user releases the keys.
    */
    public void keyReleased() {

        if(this.keyCode == 37) {
            this.leftPressed = false;
        } else if (this.keyCode == 39) {
            this.rightPressed = false;
        } else if (this.keyCode == 38) {
            this.upPressed = false;
        } else if (this.keyCode == 40) {
            this.downPressed = false;
        }

        if(this.keyCode == 32) {
            this.spacePressed = false;
        }

        if(!this.leftPressed && !this.rightPressed && !this.downPressed && !this.upPressed && this.player.getMoved()) {
            this.player.personMoved();
        }
    }

    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
