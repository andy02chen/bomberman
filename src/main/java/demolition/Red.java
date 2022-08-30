package demolition;

import processing.core.PApplet;

import processing.core.PImage;

import java.util.Random;

import java.util.ArrayList;

public class Red extends Person {

    private int moveCounter = 0;
    private ArrayList<String> chooseMove;
    private Random rand = new Random();

    /**
    * Constuctor for a red enemy
    * @param sprite, the enemy'y image
    * @param x, the x value
    * @param y, the y value
    */
    public Red(PImage sprite, int x, int y) {
        super(x,y,sprite);
    }

    /**
    * Draws the red enemy
    * @param app, the app that draws
    */
    public void draw(PApplet app) {
        if(this.alive) {
            app.image(this.sprite, this.x, this.y);
        }
    }

    /**
    * Handles the logic for the red enemy
    * such as movement, animation and death.
    */
    public void tick() {
        touchExplosion();
        this.frameCounter++;

        if(this.y == App.player.getY() && this.x == App.player.getX()) {
            App.noLives.decreaseLives();
        }

        if(this.frameCounter == this.framesPerAnimation) {

            if(faceDown) {
                this.sprite =  App.mapClass.getDownAnimationRed().get(this.animationFrame);
            } else if (faceUp) {
                this.sprite =  App.mapClass.getUpAnimationRed().get(this.animationFrame);
            } else if (faceRight) {
                this.sprite =  App.mapClass.getRightAnimationRed().get(this.animationFrame);
            } else if (faceLeft) {
                this.sprite =  App.mapClass.getLeftAnimationRed().get(this.animationFrame);
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

        if(!movesFound) {
            findMoves();
            chooseMove = new ArrayList<String>();

            if(this.canMoveDown) {
                chooseMove.add("down");
            }

            if(this.canMoveLeft) {
                chooseMove.add("left");
            }

            if(this.canMoveUp) {
                chooseMove.add("up");
            }

            if(this.canMoveRight) {
                chooseMove.add("right");
            }
        }

        if(!moved) {

             if(faceDown) {
                if(this.canMoveDown) {
                    this.moveDown();
                    this.changeDirectionDown();

                } else {
                    int whichWay = rand.nextInt(chooseMove.size());
                    String toMove = chooseMove.get(whichWay);

                    if(toMove == "left") {
                        this.moveLeft();
                        this.changeDirectionLeft();

                    } else if(toMove == "up") {
                        this.moveUp();
                        this.changeDirectionUp();

                    } else if(toMove == "right") {
                        this.moveRight();
                        this.changeDirectionRight();

                    }
                }
            } else if(faceLeft) {
                if(this.canMoveLeft) {
                    this.moveLeft();
                    this.changeDirectionLeft();

                } else {
                    int whichWay = rand.nextInt(chooseMove.size());
                    String toMove = chooseMove.get(whichWay);

                    if(toMove == "down") {
                        this.moveDown();
                        this.changeDirectionDown();

                    } else if(toMove == "up") {
                        this.moveUp();
                        this.changeDirectionUp();

                    } else if(toMove == "right") {
                        this.moveRight();
                        this.changeDirectionRight();

                    }
                }

            } else if(faceUp) {
                if(this.canMoveUp) {
                    this.moveUp();
                    this.changeDirectionUp();

                } else {
                    int whichWay = rand.nextInt(chooseMove.size());
                    String toMove = chooseMove.get(whichWay);

                    if(toMove == "left") {
                        this.moveLeft();
                        this.changeDirectionLeft();

                    } else if(toMove == "down") {
                        this.moveDown();
                        this.changeDirectionDown();

                    } else if(toMove == "right") {
                        this.moveRight();
                        this.changeDirectionRight();

                    }
                }

            } else if(faceRight) {
                if(this.canMoveRight) {
                    this.moveRight();
                    this.changeDirectionRight();

                } else {
                    int whichWay = rand.nextInt(chooseMove.size());
                    String toMove = chooseMove.get(whichWay);

                    if(toMove == "left") {
                        this.moveLeft();
                        this.changeDirectionLeft();

                    } else if(toMove == "up") {
                        this.moveUp();
                        this.changeDirectionUp();

                    } else if(toMove == "down") {
                        this.moveDown();
                        this.changeDirectionDown();

                    }
                }
            }

        }
    }

    /**
    * Enemy faces up
    */
    public void changeDirectionUp() {
        this.faceUp = true;
        this.faceDown = false;
        this.faceLeft = false;
        this.faceRight = false;
        this.sprite =  App.mapClass.getRedUp();
    }

    /**
    * Enemy faces down
    */
    public void changeDirectionDown() {
        this.faceUp = false;
        this.faceDown = true;
        this.faceLeft = false;
        this.faceRight = false;
        this.sprite =  App.mapClass.getRedDown();
    }

    /**
    * Enemy faces left
    */
    public void changeDirectionLeft() {
        this.faceUp = false;
        this.faceDown = false;
        this.faceLeft = true;
        this.faceRight = false;
        this.sprite =  App.mapClass.getRedLeft();
    }

    /**
    * Enemy faces right
    */
    public void changeDirectionRight() {
        this.faceUp = false;
        this.faceDown = false;
        this.faceLeft = false;
        this.faceRight = true;
        this.sprite = App.mapClass.getRedRight();
    }
}