package demolition;

import processing.core.PApplet;

import processing.core.PImage;

import java.util.ArrayList;

import java.util.Comparator;

public abstract class Person extends GameObject {

    /**
    * Variable for facing up
    */
    protected boolean faceUp;

    /**
    * Variable for facing down
    */
    protected boolean faceDown;

    /**
    * Variable for facing left
    */
    protected boolean faceLeft;

    /**
    * Variable for facing rightt
    */
    protected boolean faceRight;

    /**
    * Variable for if the person is alive
    */
    protected boolean alive;

    /**
    * Variable for whether the person can move up
    */
    protected boolean canMoveUp;

    /**
    * Variable for if the person is able to move down
    */
    protected boolean canMoveDown;
    
    /**
    * Variable for whether or not the person can move left
    */
    protected boolean canMoveLeft;

    /**
    * Variable for whether or not the person is able to move right
    */
    protected boolean canMoveRight;

    /**
    * Variable that states whether the person has moved or not
    */
    protected boolean moved;

    /**
    * Variable for if the person's available moved are found
    */
    protected boolean movesFound;

    /**
    * Which frame for the animation
    */
    protected int animationFrame = 1;

    /**
    * How many frames per animation
    */
    protected final int framesPerAnimation = 12;

    /**
    * Counts the frame
    */
    protected int frameCounter = 0;

    /**
    * Constructor for a Person
    * @param x, the x coordinate
    * @param y, the y coordinate
    * @param sprite, the image for the Person
    */
    public Person(int x, int y, PImage sprite) {
        super(x,y,sprite);
        this.faceUp = false;
        this.faceDown = true;
        this.faceLeft = false;
        this.faceRight = false;
        this.alive = true;
        this.moved = false;
        this.canMoveUp = false;
        this.canMoveDown = false;
        this.canMoveLeft = false;
        this.canMoveRight = false;
        this.movesFound = false;
    }

    /**
    * Abstract method for changing to up direction
    */
    public abstract void changeDirectionUp();

    /**
    * Abstract method for changing to down direction
    */
    public abstract void changeDirectionDown();

    /**
    * Abstract method for changing to left direction
    */
    public abstract void changeDirectionLeft();

    /**
    * Abstract method for changing to right direction
    */
    public abstract void changeDirectionRight();

    /**
    * Abstract method to for the tick function
    */
    public abstract void tick();

    /**
    * Abstract method for drawing the person
    * @param app, the app for drawing
    */
    public abstract void draw(PApplet app);

    /**
    * Moves th person left
    */
    public void moveLeft() {
        this.personMoved();
        this.x = this.x - Map.getTileWidth();
        this.movesFound = false;
    }

    /**
    * Moves the person right
    */
    public void moveRight() {
        this.personMoved();
        this.x = this.x + Map.getTileWidth();
        this.movesFound = false;
    }

    /**
    * Moves the person down
    */
    public void moveDown() {
        this.personMoved();
        this.y = this.y + Map.getTileWidth();
        this.movesFound = false;
    }

    /**
    * Moves the person up
    */
    public void moveUp() {
        this.personMoved();
        this.y = this.y - Map.getTileWidth();
        this.movesFound = false;
    }

    /**
    * Returns whether the person has moved
    * @return has the person moved
    */
    public boolean getMoved() {
        return this.moved;
    }

    /**
    * Changes the person's move status
    */
    public void personMoved() {
        if(this.moved) {
            this.moved = false;
        } else {
            this.moved = true;
        }
    }

    /**
    * Determines where the person can move to
    */
    public void findMoves() {

        int personX = this.x / Map.getTileWidth();
        int personY = ((this.y + Map.getExtraPlayerHeight()) / Map.getTileWidth()) - Map.getPixelOffset();

        ArrayList<ArrayList<GameObject>> layout = Map.getMap();

        GameObject leftMove = layout.get(personY).get(personX - 1);
        GameObject rightMove = layout.get(personY).get(personX + 1);
        GameObject upMove = layout.get(personY - 1).get(personX);
        GameObject downMove = layout.get(personY + 1).get(personX);

        if(((Tile)leftMove).isEmpty()) {
            this.canMoveLeft = true;
        } else {
            this.canMoveLeft = false;
        }

        if(((Tile)rightMove).isEmpty()) {
            this.canMoveRight = true;
        } else {
            this.canMoveRight = false;
        }

        if(((Tile)upMove).isEmpty()) {
            this.canMoveUp = true;
        } else {
            this.canMoveUp = false;
        }

        if(((Tile)downMove).isEmpty()) {
            this.canMoveDown = true;
        } else {
            this.canMoveDown = false;
        }

        this.movesFound = true;
    }

    /**
    * Comparator for ordering how the people are displayed
    */
    public static Comparator<Person> PersonComparator = new Comparator<Person>() {

       public int compare(Person a, Person b) {

           int aCompare = a.getY();
           int bCompare = b.getY();

           return aCompare - bCompare;

       }
    };

    /**
    * The person is dead
    */
    public void die() {
        this.alive = false;
    }

    /**
    * Returns whether or not the person is alive
    * @return is the person alive
    */
    public boolean getAlive() {
        return this.alive;
    }

    /**
    * Determines if the person has touch an explosion
    */
    public void touchExplosion() {
        ArrayList<Explosion> fire = App.mapClass.getBooms();
        for(Explosion explosion : fire) {
            if(explosion.getX() == this.x && explosion.getY() == (this.y + Map.getExtraPlayerHeight())) {
                this.die();
                App.mapClass.kill();
            }
        }
    }
}