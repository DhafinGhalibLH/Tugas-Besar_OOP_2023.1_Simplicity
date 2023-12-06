package main;

import javax.swing.JPanel;

import character.*;
import building.*;
import javafx.animation.Transition;
import map.TileControl;
import map.World;
import otherobject.Jam;
import otherobject.OtherObjects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Panel extends JPanel implements Runnable {
    Font font;
    // Screen Setting Atributes
    // Default
    public final int defTileSize = 16;
    public final int scale = 3;
    public final int tileSize = defTileSize * scale;
    public final int screenWidth = tileSize * 20;
    public final int screenHeight = tileSize * 12;
    // Fullscreen
    public boolean isFullscreen = false;
    int fullscreenWidth = screenWidth;
    int fullscreenHeight = screenHeight;
    BufferedImage buffScreen;
    Graphics graph;
    public Graphics2D graph2d;

    // World Atributes
    public World world = new World(64, 64);
    public int currentMap = 1;

    // System Atributes
    final int fps = 60;
    public Controller controller = new Controller(this);
    public TileControl tileCont = new TileControl(this);
    public CollisionControl collisionCont = new CollisionControl(this);
    public Setter setter = new Setter(this);
    public GameInterface gameInterface = new GameInterface(this);
    Thread gameThread;

    // Object Atributes
    public ArrayList<Object> daftarEntity = new ArrayList<>();
    public Sim sim = new Sim(this, controller);
    public OtherObjects objek;

    // Rumah
    // Rumah rumah = new Rumah(this, "rumah1", this.controller);

    // State Atributes
    /*
     * pause = 0
     * play = 1
     * dialogue = 2
     * launch game = 3
     */
    public int state;

    // Constructor
    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(controller);
    }

    // Runtime
    @Override
    public void run() {
        double interval = 1000000000 / fps;
        double delta = 0;
        double timer = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / interval;
            lastTime = currentTime;

            if (delta >= 1) {
                if (Jam.isTimerRun) {
                    timer += delta;
                    if (timer >= 60) {
                        Jam.updateTime();
                        timer = 0;
                    }
                }
                update();
                drawBufferedScreen();
                drawScreen();
                delta--;
            }
        }
    }

    // Method
    // System and State
    public void setup() {
        this.state = 1;
        this.buffScreen = new BufferedImage(this.screenWidth, this.screenHeight, BufferedImage.TYPE_INT_ARGB);
        graph2d = (Graphics2D) this.buffScreen.getGraphics();

        if (this.isFullscreen) {
            this.fullscreen();
        }
    }

    public void reset() {

    }

    public void update() {
        if (state == 0) {
            // do nothing, pause the screen
        }
        if (state == 1) {
            this.sim.update();
        }
    }

    public void startGame() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
        //world.addDaftarRumah(rumah);
    }

    // Screen
    public void fullscreen() {
        GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphDev = graphEnv.getDefaultScreenDevice();
        graphDev.setFullScreenWindow(Main.window);
        this.fullscreenWidth = Main.window.getWidth();
        this.fullscreenHeight = Main.window.getHeight();
    }

    public void drawBufferedScreen() {
        // Launch Game
        if (this.state == 3) {
            gameInterface.draw(this.graph2d);
        }
        // Map
        /*
         * else if(this.state == mapState)
         * {
         * map.drawFullMapScreen(this.graph2d);
         * }
         */
        // OTHERS
        else {
            // Tile
            tileCont.draw(this.graph2d);

            // ADD ENTITIES TO THE LIST
            // PLAYER
            daftarEntity.add(this.sim);

            // Rumah

            for (int i = 0; i < this.world.daftarRumah.size(); i++) {
                daftarEntity.add(this.world.daftarRumah.get(i));
            }

            // Sorting
            if (daftarEntity.size() > 1) {
                Collections.sort(daftarEntity, new Comparator<Object>() {
                    @Override
                    public int compare(Object entity1, Object entity2) {
                        int result;
                        if (entity1.getClass().getName().equals("character.Sim")) {
                            Sim sim = (Sim) entity1;
                            Rumah rumah = (Rumah) entity2;
                            result = Integer.compare(sim.ordinat, rumah.getOrdinat()); // result returns : (x=y : 0, x>y
                                                                                       // : >0, x<y : <0)
                        } else if (entity2.getClass().getName().equals("character.Sim")) {
                            Rumah rumah = (Rumah) entity1;
                            Sim sim = (Sim) entity2;
                            result = Integer.compare(rumah.getOrdinat(), sim.ordinat); // result returns : (x=y : 0, x>y
                                                                                       // : >0, x<y : <0)
                        } else if (entity1.getClass().getName().equals(
                                "character.Sim")
                                && entity2.getClass().getName().equals("character.Sim")) {
                            Sim sim1 = (Sim) entity1;
                            Sim sim2 = (Sim) entity2;
                            result = Integer.compare(sim1.ordinat, sim2.ordinat); // result returns : (x=y : 0, x>y :
                                                                                  // >0, x<y : <0)
                        } else {
                            Rumah rumah1 = (Rumah) entity1;
                            Rumah rumah2 = (Rumah) entity2;
                            result = Integer.compare(rumah1.getOrdinat(), rumah2.getOrdinat()); // result returns : (x=y
                                                                                                // : 0, x>y : >0, x<y :
                                                                                                // <0)
                        }
                        return result;
                    }
                });
            }

            // Draw
            for (int i = 0; i < daftarEntity.size(); i++) {
                if (daftarEntity.get(i).getClass().getName().equals("character.Sim")) {
                    Sim sim = (Sim) daftarEntity.get(i);
                    sim.draw(this.graph2d);
                } else {
                    Rumah rumah = (Rumah) daftarEntity.get(i);
                    rumah.draw(this.graph2d, this);
                }
            }
            // TIME
            font = new Font("Arial", 10, 25);
            this.graph2d.setFont(font);
            this.graph2d.setColor(Color.WHITE);
            this.graph2d.drawString(("Hari:" + Jam.hari + " | " + Jam.menit + ":" + Jam.detik), this.tileSize * 11, 65);

            // Clear daftarEntity
            daftarEntity.clear();

            // Interface
            // gameInterface.draw(this.graph2d);
        }
    }

    public void drawScreen() {
        Graphics graph = getGraphics();
        graph.drawImage(this.buffScreen, 0, 0, this.fullscreenWidth, this.fullscreenHeight, null);
        graph.dispose();
    }

    public void changeView() {

    }

    public void removeEntity() {

    }

    // Music and Sound Effect
    public void playMusic() {

    }

    public void stopMusic() {

    }

    public void playSoundEffect() {

    }
}
