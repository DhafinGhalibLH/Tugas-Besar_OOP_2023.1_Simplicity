package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import map.World;
import character.Sim;

public class EntityControl {
    BufferedImage image, scaledImage;
    Graphics2D graph2d;
    int eventKol, eventBar;
    Panel ePanel;
    Rectangle eventSpot = new Rectangle(23,23,2,2);
    int eventSpotdefault = 23;

    public EntityControl(Panel ePanel) {
        this.ePanel = ePanel;
        /*
         * for(int i = 0; i < this.ePanel.world.daftarRumah.size(); i++)
         * {
         * dftrRumah.add(this.ePanel.world.daftarRumah.get(i));
         * }
         */
        // do nothing
    }

    public BufferedImage utility(BufferedImage image, int width, int height) {
        scaledImage = new BufferedImage(width, height, image.getType());
        graph2d = scaledImage.createGraphics();
        graph2d.drawImage(image, 0, 0, width, height, null);
        graph2d.dispose();

        return scaledImage;
    }

    public BufferedImage setup(String path, int width, int height) {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
            this.image = this.utility(this.image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.image;
    }


    public void eventCheck(){
        for(int i = 0; i < this.ePanel.world.daftarRumah.size(); i++)
            {
                if (touch(this.ePanel.world.daftarRumah.get(i).getAbsis()/48,(this.ePanel.world.daftarRumah.get(i).getOrdinat()/48)+1,"up")){
                    //Ganti ruangan
                    if (this.ePanel.currentMap == 0) {this.ePanel.currentMap = 1;}
                    else {this.ePanel.currentMap = 0;}

                }
            }
    }

    public boolean touch(int eventKol, int eventBar, String arahPlayer) {
        boolean isTouch = false;

        eventSpot.x += eventKol*ePanel.tileSize;
        eventSpot.y += eventBar*ePanel.tileSize;
        //this.ePanel.sim.solidArea.x += this.ePanel.sim.absis;
        //Disini buat collision si Sim

        if (this.ePanel.sim.solidArea.intersects(eventSpot)) {
            if (this.ePanel.sim.direction.equals(arahPlayer)) {
                isTouch = true;
            }
        }

        eventSpot.x = eventSpotdefault;
        eventSpot.y = eventSpotdefault;
        //this.ePanel.sim.solidArea.x -= this.ePanel.sim.solidAreaDefaultX;
        //this.ePanel.sim.solidArea.y -= this.ePanel.sim.solidAreaDefaultY;
        return isTouch;

    }
}
