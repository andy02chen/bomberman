package demolition;

import processing.core.PApplet;

import processing.core.PImage;

import java.util.ArrayList;

public class Yellow extends Person {

    private int moveCounter = 0;

    /**
    * Constuctor for the Yellow enemy
    * @param sprite, the image of the enemy
    * @param x, the x value
    * @param y, the y value
    */
    public Yellow(PImage sprite, int x, int y) {
        super(x,y,sprite);
    }

    /**
    * Draws the enemy on the screen
    * @param app, the app drawing
    */
    public void draw(PApplet app) {
        if(this.alive) {
            app.image(this.sprite, this.x, this.y);
        }
    }

    /**
    * Handles the logic for the yellow enemy
    * such as movement, animation and death
    */
    public void tick() {
        touchExplosion();

        this.frameCounter++;

        if(this.y == App.player.getY() && this.x == App.player.getX()) {
            App.noLives.decreaseLives();
        }

        if(this.frameCounter == this.framesPerAnimation) {

            if(faceDown) {
                this.sprite =  App.mapClass.getDownAnimationYellow().get(this.animationFrame);
            } else if (faceUp) {
                this.sprite =  App.mapClass.getUpAnimationYellow().get(this.animationFrame);
            } else if (faceRight) {
                this.sprite =  App.mapClass.getRightAnimationYellow().get(this.animationFrame);
            } else if (faceLeft) {
                this.sprite =  App.mapClass.getLeftAnimationYellow().get(this.animationFrame);
            }
            
            this.animationFrame++;
            this.moveCounter++;
            this.frameCounter = 0;

            if(this.moveCounter == 5) {
                this.personMoved();
                this.moveCounter = 0;
            }

            if(this.animationFrame == 5) {
                this.animationFrame = 1;
            }
        }

        if(!this.movesFound) {
            findMoves();
        }

        if(!this.moved) {

            if(this.faceDown) {
                if(this.canMoveDown) {
                    this.moveDown();
                    this.changeDirectionDown();

                } else {
                    if(this.canMoveLeft) {
                        this.moveLeft();
                        this.changeDirectionLeft();

                    } else if (this.canMoveUp) {
                        this.moveUp();
                        this.changeDirectionUp();

                    } else if (this.canMoveRight) {
                        this.moveRight();
                        this.changeDirectionRight();
                    }
                }
            } else if(this.faceLeft) {
                if(this.canMoveLeft) {
                    this.moveLeft();
                    this.changeDirectionLeft();

                } else {
                    if(this.canMoveUp) {
                        this.moveUp();
                        this.changeDirectionUp();
                        
                    } else if(this.canMoveRight) {
                        this.moveRight();
                        this.changeDirectionRight();

                    } else if(this.canMoveDown) {
                        this.moveDown();
                        this.changeDirectionDown();
                    }
                }

            } else if(this.faceUp) {
                if(this.canMoveUp) {
                    this.moveUp();
                    this.changeDirectionUp();

                } else {
                    if(this.canMoveRight) {
                        this.moveRight();
                        this.changeDirectionRight();

                    } else if(this.canMoveDown) {
                        this.moveDown();
                        this.changeDirectionDown();

                    } else if(this.canMoveLeft) {
                        this.moveLeft();
                        this.changeDirectionLeft();
                    }
                }

            } else if(this.faceRight) {
                if(this.canMoveRight) {
                    this.moveRight();
                    this.changeDirectionRight();

                } else {
                    if(this.canMoveDown) {
                        this.moveDown();
                        this.changeDirectionDown();

                    } else if(this.canMoveLeft) {
                        this.moveLeft();
                        this.changeDirectionLeft();

                    } else if(this.canMoveUp) {
                        this.moveUp();
                        this.changeDirectionUp();
                    }
                }
            }

        }
    }

    /**
    * Makes the enemy face up
    */
    public void changeDirectionUp() {
        this.faceUp = true;
        this.faceDown = false;
        this.faceLeft = false;
        this.faceRight = false;
        this.sprite =  App.mapClass.getYellowUp();
    }

    /**
    * Changes to face down
    */
    public void changeDirectionDown() {
        this.faceUp = false;
        this.faceDown = true;
        this.faceLeft = false;
        this.faceRight = false;
        this.sprite =  App.mapClass.getYellowDown();
    }

    /**
    * Changes to face left
    */
    public void changeDirectionLeft() {
        this.faceUp = false;
        this.faceDown = false;
        this.faceLeft = true;
        this.faceRight = false;
        this.sprite =  App.mapClass.getYellowLeft();
    }

    /**
    * Changes to face right
    */    
    public void changeDirectionRight() {
        this.faceUp = false;
        this.faceDown = false;
        this.faceLeft = false;
        this.faceRight = true;
        this.sprite = App.mapClass.getYellowRight();
    }
}