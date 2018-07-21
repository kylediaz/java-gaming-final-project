package com.kylediaz.nameofgame;

import com.kylediaz.nameofgame.util.Time;
import com.kylediaz.nameofgame.util.Camera;
import com.kylediaz.nameofgame.util.Dimension;
import com.kylediaz.nameofgame.entity.LogicController;
import com.kylediaz.nameofgame.entity.*;
import com.kylediaz.nameofgame.input.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;   

public class Game extends JPanel {
    
    public static final Logger LOGGER = Logger.getLogger("NameOfGame");
    
    public final String NAME = "Name of Game";
    public final long TICKRATE = 144 / 1000;
    
    private JFrame parent;
    
    public Arena map;
    public Camera camera;
    
    public ArrayList<Entity> entityDirectory = new ArrayList<>();
    private LogicController logicController;
    public PlayableEntity player;
    
    public KeyboardHandler keyboardHandler = new KeyboardHandler();
    public MouseHandler mouseHandler = new MouseHandler();
    
    Font pixelFont;
    BufferedImage gameOverImage;
    
    public double timeGiven = 15;
    public int timeLeft = 15;
    
    public Game(JFrame parent) throws Exception {
        this.parent = parent;
        
        this.gameOverImage = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\com\\kylediaz\\nameofgame\\resources\\images\\deathScreen.jpg"));
        
        parent.addKeyListener(keyboardHandler);
        parent.addMouseListener(mouseHandler);
        parent.addMouseMotionListener(mouseHandler);
        // Listen for window resize
        parent.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                refreshScale();
            }
        });
        
        /* 
         * This shouldn't be used as the actual background of the g ame, but this color
         * will be drawn if some idiot ie me doesn't make sure the game paints over
         * the entireity of the screen
        */
        this.setBackground(new Color(0, 0, 32));
        
        pixelFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(System.getProperty("user.dir") + "\\src\\com\\kylediaz\\nameofgame\\resources\\slkscr.ttf"));
        pixelFont = pixelFont.deriveFont((float) 14);
        
        logicController = new LogicController(TICKRATE) {
            @Override   
            public void tick() {
                player.tick();
                for (Entity entity : toBeAdded) {
                    entityDirectory.add(entity);
                }
                toBeAdded.clear();
                for (Entity entity : toBeDeleted) {
                    entityDirectory.remove((Entity) entity);
                }
                toBeDeleted.clear();

                for (Entity entity : entityDirectory) {
                    entity.tick();
                }
                
                if (entityDirectory.size() < 5)
                    spawnUnseenEnemy();
                if (timeLeft < 0) {
                    LOGGER.log(Level.INFO, "Ran out of time");
                    endGame();
                }
                
                timeLeft = (int) timeGiven - Time.surpassedTimeMilli() / 1000;
                Time.refresh();
            }
        };
        startGame();
        logicController.start();
        
    }
    public void startGame() throws Exception {
        gameOver = false;
        entityDirectory.clear();
        Time.reset();
        timeGiven = 15;
        Arena arena = new Arena(new File(System.getProperty("user.dir") + "\\src\\com\\kylediaz\\nameofgame\\resources\\maps\\arena"));
        this.map = arena;

        Snake player = new Snake(this,
                new File(System.getProperty("user.dir") + "\\src\\com\\kylediaz\\nameofgame\\resources\\entities\\snake"),
                keyboardHandler.getPressedKeys(), mouseHandler.getMouse());
        this.player = player;
        this.camera = new Camera(player, new Dimension(96, 96));
    }
    boolean gameOver = false;
    public void endGame() {
        gameOver = true;
    }
    
    private long beforeTime, timeDiff, sleep;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameOver) {
            g.drawImage(gameOverImage, 0, 0, getWidth(), getHeight(), parent);
            if (keyboardHandler.getPressedKeys().contains(KeyEvent.VK_SPACE)) {
                try {
                    startGame();
                } catch (Exception ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            repaint();
            return;
        }
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(scale, scale);
        g2d.translate(paddingLeft, paddingTop);
        
        Point2D.Double focus = camera.focus.getFocalPoint();
        Point2D.Double topLeftCorner = new Point2D.Double(focus.x - camera.dimension.width / 2, 
                focus.y - camera.dimension.height / 2);
        g2d.translate(-topLeftCorner.x, -topLeftCorner.y);
        
        g2d.drawImage(map.background, 0, 0, map.dimension.width, map.dimension.height, this);
        
        player.render(g2d, camera);

        for (Entity entity : (ArrayList<Entity>) entityDirectory.clone()) {
            entity.render(g2d, camera);
        }
        
        g2d.setColor(Color.RED);
        g2d.setFont(pixelFont);
        String time = Integer.toString((int) timeGiven - Time.surpassedTimeMilli() / 1000);
        double stringWidth = g2d.getFontMetrics().getStringBounds(time, g2d).getWidth();
        g2d.translate(focus.x - stringWidth / 2, focus.y - camera.dimension.height / 3);
        g2d.drawString(time, 0, 0);
        
        g2d.setColor(Color.GREEN);
        String totalTime = Long.toString(Time.surpassedTimeMilli() / 1000);
        double stringHeight = g2d.getFontMetrics().getStringBounds(totalTime, g2d).getWidth();
        g2d.translate(0, stringHeight);
        g2d.drawString(totalTime, 0, 0);
        
        // Delays the repaint so it only repaints every TICKRATE seconds (TICKRATE * 1000 FPS)
        timeDiff = System.currentTimeMillis() - beforeTime;
        sleep = TICKRATE - timeDiff;
        if (sleep < 0) {
            sleep = 2;
        }
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            String errorMessage = String.format("Thread interrupted: %s", e.getMessage());
            System.err.println(errorMessage);
        }
        beforeTime = System.currentTimeMillis();
        
        repaint();
        g.dispose();
        g2d.dispose();
    }
    
    
    Rectangle topLeft = new Rectangle(0, 0, 128, 128);
    Rectangle topRight = new Rectangle(128, 0, 128, 128);
    Rectangle bottomLeft = new Rectangle(0, 128, 128, 128);
    Rectangle bottomRight = new Rectangle(128, 128, 128, 128);
    
    File enemyDirectory = new File(System.getProperty("user.dir") + "\\src\\com\\kylediaz\\nameofgame\\resources\\entities\\enemy");
    private long lastEnemySpawn = System.nanoTime();
    /**
     * spawns an enemy on the other side of the map
     */
    public void spawnUnseenEnemy() {
        if (System.nanoTime() - lastEnemySpawn < 1000000000) { //Cannot spawn one if it spawned one within 1 second
            return;
        }
        lastEnemySpawn = System.nanoTime();
        if (topLeft.contains(camera.focus.getFocalPoint())) {
            this.add(new Enemy(this, enemyDirectory, new Point2D.Double(255, 255)));
        } else if (topRight.contains(camera.focus.getFocalPoint())) {
            this.add(new Enemy(this, enemyDirectory, new Point2D.Double(0, 255)));
        } else if (bottomLeft.contains(camera.focus.getFocalPoint())) {
            this.add(new Enemy(this, enemyDirectory, new Point2D.Double(255, 0)));
        } else if (bottomLeft.contains(camera.focus.getFocalPoint())) {
            this.add(new Enemy(this, enemyDirectory, new Point2D.Double(0, 0)));
        }
    }
    
    /*
     * If you use .add() or .remove() directly on entityDirectory, you will get
     * a ConcurrentModificationException or it may delete it when the Entity is
     * being accessed, so I created "queues" for entities that need to be added 
     * or deleted.
     */
    private ArrayList<Entity> toBeAdded = new ArrayList<>();
    public void add(Entity entity) {
        LOGGER.log(Level.INFO, "Adding Entity " + entity.NAME);
        toBeAdded.add(entity);
    }
    private ArrayList<Entity> toBeDeleted = new ArrayList<>();
    public void remove(Entity entity) {
        LOGGER.log(Level.INFO, "Removing Entity " + entity.NAME);
        entity.toBeRemoved = true;
        toBeDeleted.add(entity);
    }
    
    public void changeCamera(Camera newCamera) {
        this.camera = newCamera;
        refreshScale();
    }
    /**
     * 1 unit in game = scale pixels on monitor
     */
    private double scale;
    private double paddingLeft, paddingTop;
    public void refreshScale() {
        scale = Math.min((float) this.getWidth() / camera.dimension.width, (float) this.getHeight() / camera.dimension.height);
        paddingLeft = (this.getWidth() - camera.dimension.width * scale) / 2 / scale;
        paddingTop = (this.getHeight() - camera.dimension.height * scale) / 2 / scale;
    }

}