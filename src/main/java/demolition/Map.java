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

import java.util.Collections;

public class Map {

    /**
    * Where the config.json file is located
    */
    public static String where = "config.json";

    private final PImage solidWallImage;
    private final PImage brokenWallImage;
    private final PImage emptyTileImage;
    private final PImage goalTileImage;
    private final PImage livesIcon;
    private final PImage timeIcon;

    private PImage playerSprite;

    private final PImage playerDown1;
    private final PImage playerDown2;
    private final PImage playerDown3;
    private final PImage playerDown4;

    private final PImage playerLeft1;
    private final PImage playerLeft2;
    private final PImage playerLeft3;
    private final PImage playerLeft4;

    private final PImage playerUp1;
    private final PImage playerUp2;
    private final PImage playerUp3;
    private final PImage playerUp4;

    private final PImage playerRight1;
    private final PImage playerRight2;
    private final PImage playerRight3;
    private final PImage playerRight4;

    private final HashMap<Integer, PImage> leftAnimation = new HashMap<Integer, PImage>();
    private final HashMap<Integer, PImage> rightAnimation = new HashMap<Integer, PImage>();
    private final HashMap<Integer, PImage> downAnimation = new HashMap<Integer, PImage>();
    private final HashMap<Integer, PImage> upAnimation = new HashMap<Integer, PImage>();

    private PImage redSprite;

    private final PImage redDown1;
    private final PImage redDown2;
    private final PImage redDown3;
    private final PImage redDown4;

    private final PImage redLeft1;
    private final PImage redLeft2;
    private final PImage redLeft3;
    private final PImage redLeft4;

    private final PImage redUp1;
    private final PImage redUp2;
    private final PImage redUp3;
    private final PImage redUp4;

    private final PImage redRight1;
    private final PImage redRight2;
    private final PImage redRight3;
    private final PImage redRight4;

    private final HashMap<Integer, PImage> leftAnimation_Red = new HashMap<Integer, PImage>();
    private final HashMap<Integer, PImage> rightAnimation_Red = new HashMap<Integer, PImage>();
    private final HashMap<Integer, PImage> downAnimation_Red = new HashMap<Integer, PImage>();
    private final HashMap<Integer, PImage> upAnimation_Red = new HashMap<Integer, PImage>();

    private PImage yellowSprite;

    private final PImage yellowDown1;
    private final PImage yellowDown2;
    private final PImage yellowDown3;
    private final PImage yellowDown4;

    private final PImage yellowLeft1;
    private final PImage yellowLeft2;
    private final PImage yellowLeft3;
    private final PImage yellowLeft4;

    private final PImage yellowUp1;
    private final PImage yellowUp2;
    private final PImage yellowUp3;
    private final PImage yellowUp4;

    private final PImage yellowRight1;
    private final PImage yellowRight2;
    private final PImage yellowRight3;
    private final PImage yellowRight4;

    private final HashMap<Integer, PImage> leftAnimation_Yellow = new HashMap<Integer, PImage>();
    private final HashMap<Integer, PImage> rightAnimation_Yellow = new HashMap<Integer, PImage>();
    private final HashMap<Integer, PImage> downAnimation_Yellow = new HashMap<Integer, PImage>();
    private final HashMap<Integer, PImage> upAnimation_Yellow = new HashMap<Integer, PImage>();

    private int firstFrame = 1;
    private int secondFrame = 2;
    private int thirdFrame = 3;
    private int fourthFrame = 4;
    private int fifthFrame = 5;
    private int sixthFrame = 6;
    private int seventhFrame = 7;
    private int eighthFrame = 8;

    private int currentLevel;
    private int currentLevelTime;
    private int levelLoaded;

    private ArrayList<String> levels;
    private ArrayList<Integer> times;
    private static ArrayList<ArrayList<GameObject>> map;

    private static ArrayList<Person> allPeople;
    private static ArrayList<Bomb> bombs = new ArrayList<Bomb>();;
    private static ArrayList<Explosion> booms = new ArrayList<Explosion>();

    private final PImage bomb1;
    private final PImage bomb2;
    private final PImage bomb3;
    private final PImage bomb4;
    private final PImage bomb5;
    private final PImage bomb6;
    private final PImage bomb7;
    private final PImage bomb8;

    private final HashMap<Integer, PImage> bomb_animation = new HashMap<Integer, PImage>();

    private static final int tileWidth = 32;
    private static final int extraPlayerHeight = 16;
    private static final int pixelOffset = 2;

    private final PImage explosionCentre;
    private final PImage explosionVertical;
    private final PImage explosionHorizontal;

    /**
    * Constructor for Map, stores the images
    * that will be used.
    * @param app, the app that will display the map
    */
    public Map(PApplet app) {
        this.currentLevel = 1;
        this.levels = new ArrayList<String>();
        this.times = new ArrayList<Integer>();

        this.solidWallImage = app.loadImage("src/main/resources/wall/solid.png");
        this.brokenWallImage = app.loadImage("src/main/resources/broken/broken.png");
        this.emptyTileImage = app.loadImage("src/main/resources/empty/empty.png");
        this.goalTileImage = app.loadImage("src/main/resources/goal/goal.png");
        this.livesIcon = app.loadImage("src/main/resources/icons/player.png");
        this.timeIcon = app.loadImage("src/main/resources/icons/clock.png");

        this.playerSprite = app.loadImage("src/main/resources/player/player1.png");

        this.playerDown1 = app.loadImage("src/main/resources/player/player1.png");
        this.playerDown2 = app.loadImage("src/main/resources/player/player2.png");
        this.playerDown3 = app.loadImage("src/main/resources/player/player3.png");
        this.playerDown4 = app.loadImage("src/main/resources/player/player4.png");
        
        this.downAnimation.put(firstFrame,playerDown1);
        this.downAnimation.put(secondFrame,playerDown2);
        this.downAnimation.put(thirdFrame,playerDown3);
        this.downAnimation.put(fourthFrame,playerDown4);

        this.playerLeft1 = app.loadImage("src/main/resources/player/player_left1.png");
        this.playerLeft2 = app.loadImage("src/main/resources/player/player_left2.png");
        this.playerLeft3 = app.loadImage("src/main/resources/player/player_left3.png");
        this.playerLeft4 = app.loadImage("src/main/resources/player/player_left4.png");

        this.leftAnimation.put(firstFrame,playerLeft1);
        this.leftAnimation.put(secondFrame,playerLeft2);
        this.leftAnimation.put(thirdFrame,playerLeft3);
        this.leftAnimation.put(fourthFrame,playerLeft4);

        this.playerUp1 = app.loadImage("src/main/resources/player/player_up1.png");
        this.playerUp2 = app.loadImage("src/main/resources/player/player_up2.png");
        this.playerUp3 = app.loadImage("src/main/resources/player/player_up3.png");
        this.playerUp4 = app.loadImage("src/main/resources/player/player_up4.png");

        this.upAnimation.put(firstFrame,playerUp1);
        this.upAnimation.put(secondFrame,playerUp2);
        this.upAnimation.put(thirdFrame,playerUp3);
        this.upAnimation.put(fourthFrame,playerUp4);

        this.playerRight1 = app.loadImage("src/main/resources/player/player_right1.png");
        this.playerRight2 = app.loadImage("src/main/resources/player/player_right2.png");
        this.playerRight3 = app.loadImage("src/main/resources/player/player_right3.png");
        this.playerRight4 = app.loadImage("src/main/resources/player/player_right4.png");

        this.rightAnimation.put(firstFrame,playerRight1);
        this.rightAnimation.put(secondFrame,playerRight2);
        this.rightAnimation.put(thirdFrame,playerRight3);
        this.rightAnimation.put(fourthFrame,playerRight4);


        this.redSprite = app.loadImage("src/main/resources/red_enemy/red_down1.png");

        this.redDown1 = app.loadImage("src/main/resources/red_enemy/red_down1.png");
        this.redDown2 = app.loadImage("src/main/resources/red_enemy/red_down2.png");
        this.redDown3 = app.loadImage("src/main/resources/red_enemy/red_down3.png");
        this.redDown4 = app.loadImage("src/main/resources/red_enemy/red_down4.png");
        
        this.downAnimation_Red.put(firstFrame,redDown1);
        this.downAnimation_Red.put(secondFrame,redDown2);
        this.downAnimation_Red.put(thirdFrame,redDown3);
        this.downAnimation_Red.put(fourthFrame,redDown4);

        this.redLeft1 = app.loadImage("src/main/resources/red_enemy/red_left1.png");
        this.redLeft2 = app.loadImage("src/main/resources/red_enemy/red_left2.png");
        this.redLeft3 = app.loadImage("src/main/resources/red_enemy/red_left3.png");
        this.redLeft4 = app.loadImage("src/main/resources/red_enemy/red_left4.png");

        this.leftAnimation_Red.put(firstFrame,redLeft1);
        this.leftAnimation_Red.put(secondFrame,redLeft2);
        this.leftAnimation_Red.put(thirdFrame,redLeft3);
        this.leftAnimation_Red.put(fourthFrame,redLeft4);

        this.redUp1 = app.loadImage("src/main/resources/red_enemy/red_up1.png");
        this.redUp2 = app.loadImage("src/main/resources/red_enemy/red_up2.png");
        this.redUp3 = app.loadImage("src/main/resources/red_enemy/red_up3.png");
        this.redUp4 = app.loadImage("src/main/resources/red_enemy/red_up4.png");

        this.upAnimation_Red.put(firstFrame,redUp1);
        this.upAnimation_Red.put(secondFrame,redUp2);
        this.upAnimation_Red.put(thirdFrame,redUp3);
        this.upAnimation_Red.put(fourthFrame,redUp4);

        this.redRight1 = app.loadImage("src/main/resources/red_enemy/red_right1.png");
        this.redRight2 = app.loadImage("src/main/resources/red_enemy/red_right2.png");
        this.redRight3 = app.loadImage("src/main/resources/red_enemy/red_right3.png");
        this.redRight4 = app.loadImage("src/main/resources/red_enemy/red_right4.png");

        this.rightAnimation_Red.put(firstFrame,redRight1);
        this.rightAnimation_Red.put(secondFrame,redRight2);
        this.rightAnimation_Red.put(thirdFrame,redRight3);
        this.rightAnimation_Red.put(fourthFrame,redRight4);

        this.yellowSprite = app.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");

        this.yellowDown1 = app.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
        this.yellowDown2 = app.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
        this.yellowDown3 = app.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
        this.yellowDown4 = app.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
        
        this.downAnimation_Yellow.put(firstFrame,yellowDown1);
        this.downAnimation_Yellow.put(secondFrame,yellowDown2);
        this.downAnimation_Yellow.put(thirdFrame,yellowDown3);
        this.downAnimation_Yellow.put(fourthFrame,yellowDown4);

        this.yellowLeft1 = app.loadImage("src/main/resources/yellow_enemy/yellow_left1.png");
        this.yellowLeft2 = app.loadImage("src/main/resources/yellow_enemy/yellow_left2.png");
        this.yellowLeft3 = app.loadImage("src/main/resources/yellow_enemy/yellow_left3.png");
        this.yellowLeft4 = app.loadImage("src/main/resources/yellow_enemy/yellow_left4.png");

        this.leftAnimation_Yellow.put(firstFrame,yellowLeft1);
        this.leftAnimation_Yellow.put(secondFrame,yellowLeft2);
        this.leftAnimation_Yellow.put(thirdFrame,yellowLeft3);
        this.leftAnimation_Yellow.put(fourthFrame,yellowLeft4);

        this.yellowUp1 = app.loadImage("src/main/resources/yellow_enemy/yellow_up1.png");
        this.yellowUp2 = app.loadImage("src/main/resources/yellow_enemy/yellow_up2.png");
        this.yellowUp3 = app.loadImage("src/main/resources/yellow_enemy/yellow_up3.png");
        this.yellowUp4 = app.loadImage("src/main/resources/yellow_enemy/yellow_up4.png");

        this.upAnimation_Yellow.put(firstFrame,yellowUp1);
        this.upAnimation_Yellow.put(secondFrame,yellowUp2);
        this.upAnimation_Yellow.put(thirdFrame,yellowUp3);
        this.upAnimation_Yellow.put(fourthFrame,yellowUp4);

        this.yellowRight1 = app.loadImage("src/main/resources/yellow_enemy/yellow_right1.png");
        this.yellowRight2 = app.loadImage("src/main/resources/yellow_enemy/yellow_right2.png");
        this.yellowRight3 = app.loadImage("src/main/resources/yellow_enemy/yellow_right3.png");
        this.yellowRight4 = app.loadImage("src/main/resources/yellow_enemy/yellow_right4.png");

        this.rightAnimation_Yellow.put(firstFrame,yellowRight1);
        this.rightAnimation_Yellow.put(secondFrame,yellowRight2);
        this.rightAnimation_Yellow.put(thirdFrame,yellowRight3);
        this.rightAnimation_Yellow.put(fourthFrame,yellowRight4);

        this.bomb1 = app.loadImage("src/main/resources/bomb/bomb1.png");
        this.bomb2 = app.loadImage("src/main/resources/bomb/bomb2.png");
        this.bomb3 = app.loadImage("src/main/resources/bomb/bomb3.png");
        this.bomb4 = app.loadImage("src/main/resources/bomb/bomb4.png");
        this.bomb5 = app.loadImage("src/main/resources/bomb/bomb5.png");
        this.bomb6 = app.loadImage("src/main/resources/bomb/bomb6.png");
        this.bomb7 = app.loadImage("src/main/resources/bomb/bomb7.png");
        this.bomb8 = app.loadImage("src/main/resources/bomb/bomb8.png");

        this.bomb_animation.put(firstFrame,bomb1);
        this.bomb_animation.put(secondFrame,bomb2);
        this.bomb_animation.put(thirdFrame,bomb3);
        this.bomb_animation.put(fourthFrame,bomb4);
        this.bomb_animation.put(fifthFrame,bomb5);
        this.bomb_animation.put(sixthFrame,bomb6);
        this.bomb_animation.put(seventhFrame,bomb7);
        this.bomb_animation.put(eighthFrame,bomb8);

        this.explosionCentre = app.loadImage("src/main/resources/explosion/centre.png");
        this.explosionHorizontal = app.loadImage("src/main/resources/explosion/horizontal.png");
        this.explosionVertical = app.loadImage("src/main/resources/explosion/vertical.png");
    }

    /**
    * Calls the loadConfigs function to load the level
    */
    public void setup() {
        loadConfigs();
    }

    /**
    * Returns the width of a tile
    * @return width
    */
    public static int getTileWidth() {
        return tileWidth;
    }

    /**
    * Returns the extra height of the player\
    * @return extra pixel height for player image
    */
    public static int getExtraPlayerHeight() {
        return extraPlayerHeight;
    }

    /**
    * Returns the offset
    * @return the offset
    */
    public static int getPixelOffset() {
        return pixelOffset;
    }

    /**
    * Returns the map's layout
    * @return the layout
    */
    public static ArrayList<ArrayList<GameObject>> getMap() {
        return map;
    }

    /**
    * Returns an empty tile image
    * @return image of empty tile
    */
    public PImage getEmptyTile() {
        return this.emptyTileImage;
    }

    /**
    * Returns the up-facing animation for the player
    * @return HashMap of player's up animation
    */
    public HashMap<Integer, PImage> getUpAnimation() {
        return this.upAnimation;
    }

    /**
    * Returns the down-facing animation for the player
    * @return HashMap of player's down animation
    */
    public HashMap<Integer, PImage> getDownAnimation() {
        return this.downAnimation;
    }

    /**
    * Returns the left-facing animation for the player
    * @return HashMap of player's left animation
    */
    public HashMap<Integer, PImage> getLeftAnimation() {
        return this.leftAnimation;
    }

    /**
    * Returns the right-facing animation for the player
    * @return HashMap of player's up animation
    */
    public HashMap<Integer, PImage> getRightAnimation() {
        return this.rightAnimation;
    }

    /**
    * Returns the first image of the up-facing animation for the player
    * @return player's up image
    */
    public PImage getPlayerUp() {
        return this.playerUp1;
    }

    /**
    * Returns the first image of the down-facing animation for the player
    * @return player's down image
    */
    public PImage getPlayerDown() {
        return this.playerDown1;
    }

    /**
    * Returns the first image of the left-facing animation for the player
    * @return player's left image
    */
    public PImage getPlayerLeft() {
        return this.playerLeft1;
    }

    /**
    * Returns the first image of the right-facing animation for the player
    * @return player's right image
    */
    public PImage getPlayerRight() {
        return this.playerRight1;
    }

    /**
    * Returns the up-facing animation for the red enemy
    * @return HashMap of red enemy's up animation
    */
    public HashMap<Integer, PImage> getUpAnimationRed() {
        return this.upAnimation_Red;
    }

    /**
    * Returns the down-facing animation for the red enemy
    * @return HashMap of red enemy's down animation
    */
    public HashMap<Integer, PImage> getDownAnimationRed() {
        return this.downAnimation_Red;
    }

    /**
    * Returns the left-facing animation for the red enemy
    * @return HashMap of red enemy's left animation
    */
    public HashMap<Integer, PImage> getLeftAnimationRed() {
        return this.leftAnimation_Red;
    }

    /**
    * Returns the right-facing animation for the red enemy
    * @return HashMap of red enemy's right animaton
    */
    public HashMap<Integer, PImage> getRightAnimationRed() {
        return this.rightAnimation_Red;
    }

    /**
    * Returns the first image of the up-facing animation for the red enemy
    * @return red enemy's up image
    */
    public PImage getRedUp() {
        return this.redUp1;
    }


    /**
    * Returns the first image of the down-facing animation for the red enemy
    * @return red enemy's down image
    */
    public PImage getRedDown() {
        return this.redDown1;
    }

    /**
    * Returns the first image of the left-facing animation for the red enemy
    * @return red enemy's left image
    */
    public PImage getRedLeft() {
        return this.redLeft1;
    }

    /**
    * Returns the first image of the right-facing animation for the red enemy
    * @return red enemy's right image
    */
    public PImage getRedRight() {
        return this.redRight1;
    }

    /**
    * Returns the up-facing animation for the yellow enemy
    * @return HashMap of yellow enemy's up animation
    */
    public HashMap<Integer, PImage> getUpAnimationYellow() {
        return this.upAnimation_Yellow;
    }

    /**
    * Returns the down-facing animation for the yellow enemy
    * @return HashMap of yellow enemy's down animation
    */
    public HashMap<Integer, PImage> getDownAnimationYellow() {
        return this.downAnimation_Yellow;
    }

    /**
    * Returns the left-facing animation for the yellow enemy
    * @return HashMap of yellow enemy's left animation
    */
    public HashMap<Integer, PImage> getLeftAnimationYellow() {
        return this.leftAnimation_Yellow;
    }

    /**
    * Returns the right-facing animation for the yellow enemy
    * @return HashMap of yellow enemy's right animation
    */
    public HashMap<Integer, PImage> getRightAnimationYellow() {
        return this.rightAnimation_Yellow;
    }

    /**
    * Returns the first image of the up-facing animation for the yellow enemy
    * @return yellow enemy's up image
    */
    public PImage getYellowUp() {
        return this.yellowUp1;
    }

    /**
    * Returns the first image of the down-facing animation for the yellow enemy
    * @return yellow enemy's down image
    */
    public PImage getYellowDown() {
        return this.yellowDown1;
    }

    /**
    * Returns the first image of the left-facing animation for the yellow enemy
    * @return yellow enemy's left image
    */
    public PImage getYellowLeft() {
        return this.yellowLeft1;
    }

    /**
    * Returns the first image of the right-facing animation for the yellow enemy
    * @return yellow enemy's right image
    */
    public PImage getYellowRight() {
        return this.yellowRight1;
    }

    /**
    * Returns the bomb animation cycle
    * @return a HashMap of the bomb animation
    */
    public HashMap<Integer, PImage> getBombAnimation() {
        return this.bomb_animation;
    }

    /**
    * Returns the first image of the bomb cycle
    * @return the first bomb image
    */
    public PImage getBomb1() {
        return this.bomb1;
    }

    /**
    * Returns the list of bombs on the map
    * @return list of bombs
    */
    public ArrayList<Bomb> getBombs() {
        return this.bombs;
    }

    /**
    * Returns the centre explosion image
    * @return centre explosion image
    */
    public PImage getCentreBoom() {
        return this.explosionCentre;
    }

    /**
    * Returns the horizontal explosion image
    * @return the horizontal explosion
    */
    public PImage getHorizontalBoom() {
        return this.explosionHorizontal;
    }

    /**
    * Returns the vertical explosion image
    * @return returns the vertical explosion
    */
    public PImage getVerticalBoom() {
        return this.explosionVertical;
    }

    /**
    * Loads the data from the config files such as lives, levels, and the map
    */
    public void loadConfigs() {
        try {
            File readConfig = new File(where);
            Scanner configScan = new Scanner(readConfig);

            String configurations = "";

            while(configScan.hasNext()) {
                configurations = configurations + configScan.nextLine();
            }

            JSONObject config = JSONObject.parse(configurations);

            App.noLives = new Lives(127,15,this.livesIcon,config.getInt("lives"));

            JSONArray whichLevel = config.getJSONArray("levels");

            for (int i = 0; i < whichLevel.size(); i++) {
                String level = whichLevel.getJSONObject(i).getString("path");
                int time = whichLevel.getJSONObject(i).getInt("time");

                this.levels.add(level);
                this.times.add(time);
            }

            configScan.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
    * Retrieves the level and stores it so that it can be used later
    * @return returns the level read from the file
    */
    public ArrayList<ArrayList<Character>> getLevel() {
        ArrayList<ArrayList<Character>> layout = new ArrayList<ArrayList<Character>>();
        String level = this.levels.get(this.currentLevel - 1);
        this.currentLevelTime = this.times.get(this.currentLevel - 1);

        try{
            File readLevel = new File(level);
            Scanner getRows = new Scanner(readLevel);
            int rows = 0;
            int columns = 0;

            while(getRows.hasNextLine()) {
                getRows.nextLine();
                rows++;
            }

            getRows.close();

            for(int i = 0; i < rows; i++) {
                layout.add(new ArrayList<Character>());
            }

            int rowNum = 0;

            Scanner readLayout = new Scanner(readLevel);
            while(readLayout.hasNextLine()) {
                String row = readLayout.nextLine();
                for(int i = 0; i < row.length(); i++) {
                    char letter = row.charAt(i);
                    layout.get(rowNum).add(letter);
                }
                rowNum++;
            }
            readLayout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return layout;
    }

    /**
    * Creates the level layout 
    * @return the layoyt of the level
    */
    public ArrayList<ArrayList<GameObject>> createLevel() {
        this.map = new ArrayList<ArrayList<GameObject>>();
        this.allPeople = new ArrayList<Person>();
        this.booms = new ArrayList<Explosion>();
        this.bombs = new ArrayList<Bomb>();
        ArrayList<ArrayList<Character>> layout = getLevel();

        for(int i = 0; i < layout.size(); i++) {

            map.add(new ArrayList<GameObject>());

            for(int j = 0; j < layout.get(i).size(); j++) {
            
                if(layout.get(i).get(j) == 'W'){
                    map.get(i).add(new SolidWall(j * this.tileWidth, (i + pixelOffset) * this.tileWidth, this.solidWallImage));

                } else if (layout.get(i).get(j) == ' ') {
                    map.get(i).add(new EmptyTile(j * this.tileWidth, (i + pixelOffset) * this.tileWidth, this.emptyTileImage));

                } else if(layout.get(i).get(j) == 'Y') {

                    map.get(i).add(new EmptyTile(j * this.tileWidth, (i + pixelOffset) * this.tileWidth, this.emptyTileImage));
                    allPeople.add(new Yellow(this.yellowDown1, j * this.tileWidth, (i + pixelOffset) * this.tileWidth - this.extraPlayerHeight));

                } else if(layout.get(i).get(j) == 'R') {
                    map.get(i).add(new EmptyTile(j * this.tileWidth, (i + pixelOffset) * this.tileWidth, this.emptyTileImage));
                    allPeople.add(new Red(this.redDown1, j * this.tileWidth, (i + pixelOffset) * this.tileWidth - this.extraPlayerHeight));

                } else if (layout.get(i).get(j) == 'B') {
                    map.get(i).add(new BrokenWall(j * this.tileWidth, (i + pixelOffset) * this.tileWidth, this.brokenWallImage));

                } else if (layout.get(i).get(j) == 'G') {
                    map.get(i).add(new GoalTile(j * this.tileWidth, (i + pixelOffset) * this.tileWidth, this.goalTileImage));
                    App.goal = new GoalTile(j * this.tileWidth, (i + pixelOffset) * this.tileWidth, this.goalTileImage);

                } else if (layout.get(i).get(j) == 'P') {
                    map.get(i).add(new EmptyTile(j * this.tileWidth, (i + pixelOffset) * this.tileWidth, this.emptyTileImage));

                    App.player = new Player(this.playerDown1 ,j * this.tileWidth, (i + pixelOffset) * this.tileWidth - this.extraPlayerHeight);
                    allPeople.add(App.player);
                }
            }
        }
        return this.map;
    }
    
    /**
    * Displays the level
    * @param app, the app that will display
    */
    public void displayLevel(PApplet app) {
        if((this.currentLevel > this.levelLoaded && !App.win)) {
            App.reset = false;
            ArrayList<ArrayList<GameObject>> map = createLevel();
            this.levelLoaded++;
            App.timer = new Clock(255,15,this.timeIcon,this.currentLevelTime);
        } else if(App.reset) {
            App.reset = false;
            ArrayList<ArrayList<GameObject>> map = createLevel();
            App.timer = new Clock(255,15,this.timeIcon,this.currentLevelTime);
        }
        
        for(int i = 0; i <  map.size(); i++) {
            for(int j = 0; j <  map.get(i).size(); j++) {
                map.get(i).get(j).draw(app);
            }
        }
    }

    /**
    * Changes the level so that the player is able to progress
    */
    public void changeLevel() {
        App.leftPressed = false;
        App.rightPressed = false;
        App.downPressed = false;
        App.upPressed = false;
        if(this.currentLevel != levels.size()) {
            this.currentLevel++;
        } else {
            App.win = true;
        }
    }

    /**
    * Returns the people
    * @return the list of people to be displayed
    */
    public ArrayList<Person> getPeople() {
        return this.allPeople;
    }

    /**
    * Draws the people
    * @param app, the app that will be drawing
    */
    public void peopleDraw(PApplet app) {
        for(Person toDraw : allPeople) {
            toDraw.draw(app);
        }
    }
    
    /**
    * Calls the tick function for the people in the game
    */
    public void peopleTick() {
        Collections.sort(allPeople, Person.PersonComparator);

        allPeople.forEach((p) -> p.tick());
    }

    /**
    * Kills the people that are no longer in the level
    */
    public void kill() {
        ArrayList<Person> keep = new ArrayList<Person>();
        this.allPeople.forEach((d) -> {
            if(d.getAlive() == true) {
                keep.add(d);
            }
        });

        this.allPeople = keep;
    }

    /**
    * Adds a bomb to the list
    * @param boom, the bomb
    */
    public void addBomb(Bomb boom) {
        bombs.add(boom);
    }

    /**
    * Calls the tick function for each bomb in the list
    */
    public void bombTick() {
        bombs.forEach((b) -> b.tick());
    }

    /**
    * Draws the bomb
    * @param app, the app drawing the bomb
    */
    public void bombDraw(PApplet app) {
        bombs.forEach((b) -> b.draw(app));
    }

    /**
    * Removes the bombs from the list
    */
    public void removeBombs() {
        ArrayList<Bomb> keep = new ArrayList<Bomb>();
        this.bombs.forEach((b) -> {
            if(b.getRemove() == false) {
                keep.add(b);
            }
        });

        this.bombs = keep;
    }

    /**
    * Adds an explosion to the list of explosions
    * @param explosion, the explosion that will be added
    */
    public void addBoom(Explosion explosion) {
        booms.add(explosion);
    }

    /**
    * Draws the explosion
    * @param app, the app drawing
    */
    public void explosionsDraw(PApplet app) {
        booms.forEach((e) -> e.draw(app));
    }

    /**
    * Calls the tick function for each explosion
    */
    public void explosionsTick() {
        booms.forEach((e) -> e.tick());
    }

    /**
    * Removes the explosion after it has finished
    */
    public void removeBooms() {
        ArrayList<Explosion> keep = new ArrayList<Explosion>();
        this.booms.forEach((e) -> {
            if(e.getRemove() == false) {
                keep.add(e);
            }
        });

        this.booms = keep;
    }

    /**
    * Changes the broken wall into an empty tile once it has been destroyed
    * @param y, the y coordinate
    * @param x, the x coordinatie
    * @param wall, the wall to replace the broken wall
    */
    public void changeWall(int y, int x, GameObject wall) {
        this.map.get(y).set(x, wall);
    }

    /**
    * Returns the explosions
    * @return the list of bombs
    */
    public ArrayList<Explosion> getBooms() {
        return this.booms;
    }

    /**
    * Returns the current level
    * @return the current level number
    */
    public int getCurrentLevel() {
        return this.currentLevel;
    }
}