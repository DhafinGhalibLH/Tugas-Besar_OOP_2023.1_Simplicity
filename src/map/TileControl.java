package map;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import main.Panel;
import main.EntityControl;

public class TileControl {
    Panel panel;
    public Tile[] tile = new Tile[40];
    public int mapTileNum[][][];
    ArrayList<String> collisionStatus = new ArrayList<>();
    EntityControl entity;

    public TileControl(Panel panel) {
        this.panel = panel;
        entity = new EntityControl(this.panel);
        this.mapTileNum = new int[2][panel.world.width][panel.world.length];
        // READ TILE DATA FILE
        /*
         * InputStream input = getClass().getResourceAsStream("/maps/tiledata.txt");
         * BufferedReader reader = new BufferedReader(new InputStreamReader(input));
         * 
         * //GETTING TILE NAMES AND COLLISION INFO FROM THE FILE
         * String line;
         * try {
         * while((line = reader.readLine()) != null) {
         * fileNames.add(line);
         * collisionStatus.add(reader.readLine()); //read next line
         * }
         * reader.close();
         * } catch (IOException e) {
         * throw new RuntimeException(e);
         * }
         * 
         * //INITIALIZE THE TILE ARRAY BASED ON THE fileNames size
         * tile = new Tile[fileNames.size()];
         * getTileImage();
         * 
         * //GET THE world.width & Row
         * input = getClass().getResourceAsStream("/maps/dungeon02.txt");
         * reader = new BufferedReader(new InputStreamReader(input));
         * 
         * try {
         * String line2 = reader.readLine();
         * String maxTile[] = line2.split(" ");
         * 
         * panel.world.width = maxTile.length;
         * panel.world.length = maxTile.length;
         * 
         * mapTileNum = new int[panel.maxMap][panel.world.width][panel.world.length];
         * 
         * reader.close();
         * } catch (IOException e) {
         * System.out.println("Exception!");
         * }
         */
        getTileImage();
        loadMap("/maps/World.txt", 0);
        loadMap("/maps/Ruangan.txt", 1);
    }

    public void getTileImage() {
        setup(0, "rumput_t", false);
        setup(1, "tile_ubin_v2", false);
        setup(2, "street_t", false);
        setup(3, "sudut_kiri", false);
        setup(4, "rumput_atas", false);
        setup(5, "sudut_kanan", false);
        setup(6, "rumput_kiri", false);
        setup(7, "rumput_kanan", false);
        setup(8, "sudbaw_kiri", false);
        setup(9, "rumput_bott", false);
        setup(10, "sudbaw_kanan", false);
        setup(11, "tiles_ubin", true);
        setup(12, "sudbaw_street", false);
        setup(13, "sudbaw_street_1", false);
        setup(14, "sudbaw_street_2", false);
        setup(15, "sudbaw_street_3", false);
        setup(16, "water-1", true);
        setup(17, "water-2", true);
        setup(18, "water-3", true);
        setup(19, "water-4", true);
        setup(20, "water-5", true);
        setup(21, "water-6", true);
        setup(22, "water-7", true);
        setup(23, "water-8", true);
        setup(24, "water-9", true);
        setup(25, "tile_hitam", true);
        setup(26, "dinding_1", true);
        setup(27, "dinding_2", true);
        setup(28, "dinding_3", true);
        setup(29, "dinding_4", true);
        setup(30, "dinding_5", true);
        setup(31, "dinding_6", true);
        setup(32, "pintu", true);
    }

    public void setup(int idx, String image, boolean collision) {
        // IMPROVING RENDERING // Scaling with uTool
        // With uTool I'm not using anymore like: graph2d.drawImage(tile[tileNum].image,
        // screenX, screenY, panel.tileSize, panel.tileSize,null);
        // I use graph2d.drawImage(tile[tileNum].image, screenX, screenY,null);
        this.tile[idx] = new Tile();
        this.tile[idx].image = entity.setup(("/tiles/" + image + ".png"), panel.tileSize, panel.tileSize);
        this.tile[idx].collision = collision;
    }

    public void loadMap(String filePath, int nb) {
        try {
            InputStream input = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input)); // to read from txt

            int col = 0;
            int row = 0;

            while (col < panel.world.width && row < panel.world.length) {
                String line = reader.readLine();

                while (col < panel.world.width) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[nb][col][row] = num;
                    col++;
                }
                if (col == panel.world.width) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graph2d) {
        int width = 0;
        int length = 0;

        while (width < panel.world.width && length < panel.world.length) {
            int tileNum = mapTileNum[panel.currentMap][width][length];

            int worldX = width * panel.tileSize;
            int worldY = length * panel.tileSize;
            int screenX = worldX - panel.sim.absis + panel.sim.screenAbs;
            int screenY = worldY - panel.sim.ordinat + panel.sim.screenOrd;

            if (worldX + panel.tileSize > panel.sim.absis - panel.sim.screenAbs &&
                    worldX - panel.tileSize < panel.sim.absis + panel.sim.screenAbs &&
                    worldY + panel.tileSize > panel.sim.ordinat - panel.sim.screenOrd &&
                    worldY - panel.tileSize < panel.sim.ordinat + panel.sim.screenOrd) {
                graph2d.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            width++;

            if (width == panel.world.width) {
                width = 0;
                length++;
            }
        }
    }
}
