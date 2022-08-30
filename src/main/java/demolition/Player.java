package demolition;

import processing.core.PApplet;

import processing.core.PImage;

import java.util.ArrayList;

public class Player extends Person {

    /**
    * Constructor for the player
    * @param sprite, the player image
    * @param x, the x value
    * @param y, the y value
    */
    public Player(PImage sprite, int x, int y) {
        super(x,y,sprite);
    }

    /**
    * Draws the player
    * @param app, the app drawing
    */
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }

    /**
    * Handles the logic for the Player class,
    * Such as if the player is dead, the animation
    * and the movement
    */
    public void tick() {
        touchExplosion();

        if(!this.alive) {
            App.noLives.decreaseLives();
            App.leftPressed = false;
            App.rightPressed = false;
            App.upPressed = false;
            App.downPressed = false;
        }

        if(App.goal.getX() == this.x && App.goal.getY() == (this.y + Map.getExtraPlayerHeight())) {
            App.mapClass.changeLevel();
        }

        this.frameCounter++;

        if(this.frameCounter == this.framesPerAnimation) {

            if(faceDown) {
                this.sprite = App.mapClass.getDownAnimation().get(this.animationFrame);
            } else if (faceUp) {
                this.sprite = App.mapClass.getUpAnimation().get(this.animationFrame);
            } else if (faceRight) {
                this.sprite = App.mapClass.getRightAnimation().get(this.animationFrame);
            } else if (faceLeft) {
                this.sprite = App.mapClass.getLeftAnimation().get(this.animationFrame);
            }
            
            this.animationFrame++;
            this.frameCounter = 0;

            if(this.animationFrame == 5) {
                this.animationFrame = 1;
            }
        }

        if(!movesFound) {
            findMoves();
        }

        if(!moved) {
            
            if(App.leftPressed && this.canMoveLeft) {
                this.moveLeft();
                this.changeDirectionLeft();
            } else if(App.rightPressed && this.canMoveRight) {
                this.moveRight();
                this.changeDirectionRight();
            } else if(App.downPressed && this.canMoveDown) {
                this.moveDown();
                this.changeDirectionDown();
            } else if(App.upPressed && this.canMoveUp) {
                this.moveUp();
                this.changeDirectionUp();
            }
        }
    }

    /**
    * Changes the direction that the player is facing to up
    */
    public void changeDirectionUp() {
        this.faceUp = true;
        this.faceDown = false;
        this.faceLeft = false;
        this.faceRight = false;
        this.sprite = App.mapClass.getPlayerUp();
    }

    /**
    * The player faces down
    */
    public void changeDirectionDown() {
        this.faceUp = false;
        this.faceDown = true;
        this.faceLeft = false;
        this.faceRight = false;
        this.sprite = App.mapClass.getPlayerDown();
    }

    /**
    * The player faces left
    */
    public void changeDirectionLeft() {
        this.faceUp = false;
        this.faceDown = false;
        this.faceLeft = true;
        this.faceRight = false;
        this.sprite = App.mapClass.getPlayerLeft();
    }

    /**
    * The player faces rightt
    */
    public void changeDirectionRight() {
        this.faceUp = false;
        this.faceDown = false;
        this.faceLeft = false;
        this.faceRight = true;
        this.sprite = App.mapClass.getPlayerRight();
    }
}