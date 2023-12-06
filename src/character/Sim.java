package character;

import main.Panel;
import main.Controller;
import main.EntityControl;
import otherobject.OtherObjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Sim {
    private String nama;
    private Pekerjaan pekerjaan;
    private int kesehatan;
    private int kekenyangan;
    private int mood;
    private int uang;
    private Inventory inventory;
    private String status;
    private String character = "char1";

    Panel panel;
    public Controller controlKey;
    public String direction;
    public int absis, ordinat, screenAbs, screenOrd,solidAreadefaultX, solidAreadefaultY;
    public int speed = 3;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int standCounter = 0;
    public boolean collision = false;
    public EntityControl entityControl = new EntityControl(this.panel);
    public BufferedImage image, up1, up2, down1, down2, left1, left2, right1, right2, kerja1, kerja2, makan1, makan2;
    public BufferedImage masak1, masak2, masak3, masak4, olahraga1, olahraga2, olahraga3, olahraga4, tidur1, tidur2, tidur3, toilet1, toilet2;
    public Rectangle solidArea;

    public Sim(Panel panel, Controller controlKey) {
        /*
         * this.nama = nama;
         * this.pekerjaan = pekerjaan;
         * this.kesehatan = 80;
         * this.kekenyangan = 80;
         * this.mood = 80;
         * this.uang = 100;
         * this.status = status;
         * this.inventory = new Inventory();
         */
        this.panel = panel;
        this.controlKey = controlKey;
        this.direction = "down";
        this.absis = panel.tileSize * 32;
        this.ordinat = panel.tileSize * 32;
        this.screenAbs = panel.screenWidth / 2 - (panel.tileSize / 2);
        this.screenOrd = panel.screenHeight / 2 - (panel.tileSize / 2);
        this.setCharacter(this.character);
        entityControl = new EntityControl(this.panel);
        
        this.solidArea = new Rectangle();
        solidAreadefaultX = 8;
        solidAreadefaultY = 16;
        solidArea.x = solidAreadefaultX;
        solidArea.y = solidAreadefaultY;
        solidArea.width = 32;
        solidArea.height = 32; 


    }

    public void setCharacter(String character) {
        switch (this.character) {
            case ("char1"):
                this.up1 = entityControl.setup("/character/char1/up-1.png", this.panel.tileSize, this.panel.tileSize);
                this.up2 = entityControl.setup("/character/char1/up-2.png", this.panel.tileSize, this.panel.tileSize);
                this.down1 = entityControl.setup("/character/char1/down-1.png", this.panel.tileSize, this.panel.tileSize);
                this.down2 = entityControl.setup("/character/char1/down-2.png", this.panel.tileSize, this.panel.tileSize);
                this.left1 = entityControl.setup("/character/char1/left-1.png", this.panel.tileSize, this.panel.tileSize);
                this.left2 = entityControl.setup("/character/char1/left-2.png", this.panel.tileSize, this.panel.tileSize);
                this.right1 = entityControl.setup("/character/char1/right-1.png", this.panel.tileSize, this.panel.tileSize);
                this.right2 = entityControl.setup("/character/char1/right-2.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja1 = entityControl.setup("/character/char1/kerja-1.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja2 = entityControl.setup("/character/char1/kerja_2.png", this.panel.tileSize, this.panel.tileSize);
                this.makan1 = entityControl.setup("/character/char1/makan-1.png", this.panel.tileSize, this.panel.tileSize);
                this.makan2 = entityControl.setup("/character/char1/makan-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak1 = entityControl.setup("/character/char1/masak-1.png", this.panel.tileSize, this.panel.tileSize);
                this.masak2 = entityControl.setup("/character/char1/masak-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak3 = entityControl.setup("/character/char1/masak-3.png", this.panel.tileSize, this.panel.tileSize);
                this.masak4 = entityControl.setup("/character/char1/masak-4.png", this.panel.tileSize, this.panel.tileSize);  
                this.olahraga1 = entityControl.setup("/character/char1/olahraga-1.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga2 = entityControl.setup("/character/char1/olahraga-2.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga3 = entityControl.setup("/character/char1/olahraga-3.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga4 = entityControl.setup("/character/char1/olahraga-4.png", this.panel.tileSize, this.panel.tileSize); 
                this.tidur1 = entityControl.setup("/character/char1/tidur-1.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur2 = entityControl.setup("/character/char1/tidur-2.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur3 = entityControl.setup("/character/char1/tidur-3.png", this.panel.tileSize, this.panel.tileSize);   
                this.toilet1 = entityControl.setup("/character/char1/toilet-1.png", this.panel.tileSize, this.panel.tileSize);
                this.toilet2 = entityControl.setup("/character/char1/toilet-2.png", this.panel.tileSize, this.panel.tileSize); 
                break;
            case ("char2"):
                this.up1 = entityControl.setup("/character/char2/up-1.png", this.panel.tileSize, this.panel.tileSize);
                this.up2 = entityControl.setup("/character/char2/up-2.png", this.panel.tileSize, this.panel.tileSize);
                this.down1 = entityControl.setup("/character/char2/down-1.png", this.panel.tileSize, this.panel.tileSize);
                this.down2 = entityControl.setup("/character/char2/down-2.png", this.panel.tileSize, this.panel.tileSize);
                this.left1 = entityControl.setup("/character/char2/left-1.png", this.panel.tileSize, this.panel.tileSize);
                this.left2 = entityControl.setup("/character/char2/left-2.png", this.panel.tileSize, this.panel.tileSize);
                this.right1 = entityControl.setup("/character/char2/right-1.png", this.panel.tileSize, this.panel.tileSize);
                this.right2 = entityControl.setup("/character/char2/right-2.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja1 = entityControl.setup("/character/char2/kerja-1.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja2 = entityControl.setup("/character/char2/kerja_2.png", this.panel.tileSize, this.panel.tileSize);
                this.makan1 = entityControl.setup("/character/char2/makan-1.png", this.panel.tileSize, this.panel.tileSize);
                this.makan2 = entityControl.setup("/character/char2/makan-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak1 = entityControl.setup("/character/char2/masak-1.png", this.panel.tileSize, this.panel.tileSize);
                this.masak2 = entityControl.setup("/character/char2/masak-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak3 = entityControl.setup("/character/char2/masak-3.png", this.panel.tileSize, this.panel.tileSize);
                this.masak4 = entityControl.setup("/character/char2/masak-4.png", this.panel.tileSize, this.panel.tileSize);  
                this.olahraga1 = entityControl.setup("/character/char2/olahraga-1.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga2 = entityControl.setup("/character/char2/olahraga-2.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga3 = entityControl.setup("/character/char2/olahraga-3.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga4 = entityControl.setup("/character/char2/olahraga-4.png", this.panel.tileSize, this.panel.tileSize); 
                this.tidur1 = entityControl.setup("/character/char2/tidur-1.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur2 = entityControl.setup("/character/char2/tidur-2.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur3 = entityControl.setup("/character/char2/tidur-3.png", this.panel.tileSize, this.panel.tileSize);   
                this.toilet1 = entityControl.setup("/character/char2/toilet-1.png", this.panel.tileSize, this.panel.tileSize);
                this.toilet2 = entityControl.setup("/character/char2/toilet-2.png", this.panel.tileSize, this.panel.tileSize); 
                break;
            case ("char3"):
                this.up1 = entityControl.setup("/character/char3/up-1.png", this.panel.tileSize, this.panel.tileSize);
                this.up2 = entityControl.setup("/character/char3/up-2.png", this.panel.tileSize, this.panel.tileSize);
                this.down1 = entityControl.setup("/character/char3/down-1.png", this.panel.tileSize, this.panel.tileSize);
                this.down2 = entityControl.setup("/character/char3/down-2.png", this.panel.tileSize, this.panel.tileSize);
                this.left1 = entityControl.setup("/character/char3/left-1.png", this.panel.tileSize, this.panel.tileSize);
                this.left2 = entityControl.setup("/character/char3/left-2.png", this.panel.tileSize, this.panel.tileSize);
                this.right1 = entityControl.setup("/character/char3/right-1.png", this.panel.tileSize, this.panel.tileSize);
                this.right2 = entityControl.setup("/character/char3/right-2.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja1 = entityControl.setup("/character/char3/kerja-1.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja2 = entityControl.setup("/character/char3/kerja_2.png", this.panel.tileSize, this.panel.tileSize);
                this.makan1 = entityControl.setup("/character/char3/makan-1.png", this.panel.tileSize, this.panel.tileSize);
                this.makan2 = entityControl.setup("/character/char3/makan-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak1 = entityControl.setup("/character/char3/masak-1.png", this.panel.tileSize, this.panel.tileSize);
                this.masak2 = entityControl.setup("/character/char3/masak-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak3 = entityControl.setup("/character/char3/masak-3.png", this.panel.tileSize, this.panel.tileSize);
                this.masak4 = entityControl.setup("/character/char3/masak-4.png", this.panel.tileSize, this.panel.tileSize);  
                this.olahraga1 = entityControl.setup("/character/char3/olahraga-1.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga2 = entityControl.setup("/character/char3/olahraga-2.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga3 = entityControl.setup("/character/char3/olahraga-3.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga4 = entityControl.setup("/character/char3/olahraga-4.png", this.panel.tileSize, this.panel.tileSize); 
                this.tidur1 = entityControl.setup("/character/char3/tidur-1.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur2 = entityControl.setup("/character/char3/tidur-2.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur3 = entityControl.setup("/character/char3/tidur-3.png", this.panel.tileSize, this.panel.tileSize);   
                this.toilet1 = entityControl.setup("/character/char3/toilet-1.png", this.panel.tileSize, this.panel.tileSize);
                this.toilet2 = entityControl.setup("/character/char3/toilet-2.png", this.panel.tileSize, this.panel.tileSize); 
                break;
            case ("char4"):
                this.up1 = entityControl.setup("/character/char4/up-1.png", this.panel.tileSize, this.panel.tileSize);
                this.up2 = entityControl.setup("/character/char4/up-2.png", this.panel.tileSize, this.panel.tileSize);
                this.down1 = entityControl.setup("/character/char4/down-1.png", this.panel.tileSize, this.panel.tileSize);
                this.down2 = entityControl.setup("/character/char4/down-2.png", this.panel.tileSize, this.panel.tileSize);
                this.left1 = entityControl.setup("/character/char4/left-1.png", this.panel.tileSize, this.panel.tileSize);
                this.left2 = entityControl.setup("/character/char4/left-2.png", this.panel.tileSize, this.panel.tileSize);
                this.right1 = entityControl.setup("/character/char4/right-1.png", this.panel.tileSize, this.panel.tileSize);
                this.right2 = entityControl.setup("/character/char4/right-2.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja1 = entityControl.setup("/character/char4/kerja-1.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja2 = entityControl.setup("/character/char4/kerja_2.png", this.panel.tileSize, this.panel.tileSize);
                this.makan1 = entityControl.setup("/character/char4/makan-1.png", this.panel.tileSize, this.panel.tileSize);
                this.makan2 = entityControl.setup("/character/char4/makan-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak1 = entityControl.setup("/character/char4/masak-1.png", this.panel.tileSize, this.panel.tileSize);
                this.masak2 = entityControl.setup("/character/char4/masak-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak3 = entityControl.setup("/character/char4/masak-3.png", this.panel.tileSize, this.panel.tileSize);
                this.masak4 = entityControl.setup("/character/char4/masak-4.png", this.panel.tileSize, this.panel.tileSize);  
                this.olahraga1 = entityControl.setup("/character/char4/olahraga-1.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga2 = entityControl.setup("/character/char4/olahraga-2.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga3 = entityControl.setup("/character/char4/olahraga-3.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga4 = entityControl.setup("/character/char4/olahraga-4.png", this.panel.tileSize, this.panel.tileSize); 
                this.tidur1 = entityControl.setup("/character/char4/tidur-1.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur2 = entityControl.setup("/character/char4/tidur-2.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur3 = entityControl.setup("/character/char4/tidur-3.png", this.panel.tileSize, this.panel.tileSize);   
                this.toilet1 = entityControl.setup("/character/char4/toilet-1.png", this.panel.tileSize, this.panel.tileSize);
                this.toilet2 = entityControl.setup("/character/char4/toilet-2.png", this.panel.tileSize, this.panel.tileSize); 
                break;
            case ("char5"):
                this.up1 = entityControl.setup("/character/char5/up-1.png", this.panel.tileSize, this.panel.tileSize);
                this.up2 = entityControl.setup("/character/char5/up-2.png", this.panel.tileSize, this.panel.tileSize);
                this.down1 = entityControl.setup("/character/char5/down-1.png", this.panel.tileSize, this.panel.tileSize);
                this.down2 = entityControl.setup("/character/char5/down-2.png", this.panel.tileSize, this.panel.tileSize);
                this.left1 = entityControl.setup("/character/char5/left-1.png", this.panel.tileSize, this.panel.tileSize);
                this.left2 = entityControl.setup("/character/char5/left-2.png", this.panel.tileSize, this.panel.tileSize);
                this.right1 = entityControl.setup("/character/char5/right-1.png", this.panel.tileSize, this.panel.tileSize);
                this.right2 = entityControl.setup("/character/char5/right-2.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja1 = entityControl.setup("/character/char5/kerja-1.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja2 = entityControl.setup("/character/char5/kerja_2.png", this.panel.tileSize, this.panel.tileSize);
                this.makan1 = entityControl.setup("/character/char5/makan-1.png", this.panel.tileSize, this.panel.tileSize);
                this.makan2 = entityControl.setup("/character/char5/makan-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak1 = entityControl.setup("/character/char5/masak-1.png", this.panel.tileSize, this.panel.tileSize);
                this.masak2 = entityControl.setup("/character/char5/masak-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak3 = entityControl.setup("/character/char5/masak-3.png", this.panel.tileSize, this.panel.tileSize);
                this.masak4 = entityControl.setup("/character/char5/masak-4.png", this.panel.tileSize, this.panel.tileSize);  
                this.olahraga1 = entityControl.setup("/character/char5/olahraga-1.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga2 = entityControl.setup("/character/char5/olahraga-2.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga3 = entityControl.setup("/character/char5/olahraga-3.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga4 = entityControl.setup("/character/char5/olahraga-4.png", this.panel.tileSize, this.panel.tileSize); 
                this.tidur1 = entityControl.setup("/character/char5/tidur-1.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur2 = entityControl.setup("/character/char5/tidur-2.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur3 = entityControl.setup("/character/char5/tidur-3.png", this.panel.tileSize, this.panel.tileSize);   
                this.toilet1 = entityControl.setup("/character/char5/toilet-1.png", this.panel.tileSize, this.panel.tileSize);
                this.toilet2 = entityControl.setup("/character/char5/toilet-2.png", this.panel.tileSize, this.panel.tileSize); 
                break;
            case ("char6"):
                this.up1 = entityControl.setup("/character/char6/up-1.png", this.panel.tileSize, this.panel.tileSize);
                this.up2 = entityControl.setup("/character/char6/up-2.png", this.panel.tileSize, this.panel.tileSize);
                this.down1 = entityControl.setup("/character/char6/down-1.png", this.panel.tileSize, this.panel.tileSize);
                this.down2 = entityControl.setup("/character/char6/down-2.png", this.panel.tileSize, this.panel.tileSize);
                this.left1 = entityControl.setup("/character/char6/left-1.png", this.panel.tileSize, this.panel.tileSize);
                this.left2 = entityControl.setup("/character/char6/left-2.png", this.panel.tileSize, this.panel.tileSize);
                this.right1 = entityControl.setup("/character/char6/right-1.png", this.panel.tileSize, this.panel.tileSize);
                this.right2 = entityControl.setup("/character/char6/right-2.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja1 = entityControl.setup("/character/char6/kerja-1.png", this.panel.tileSize, this.panel.tileSize);
                this.kerja2 = entityControl.setup("/character/char6/kerja_2.png", this.panel.tileSize, this.panel.tileSize);
                this.makan1 = entityControl.setup("/character/char6/makan-1.png", this.panel.tileSize, this.panel.tileSize);
                this.makan2 = entityControl.setup("/character/char6/makan-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak1 = entityControl.setup("/character/char6/masak-1.png", this.panel.tileSize, this.panel.tileSize);
                this.masak2 = entityControl.setup("/character/char6/masak-2.png", this.panel.tileSize, this.panel.tileSize);
                this.masak3 = entityControl.setup("/character/char6/masak-3.png", this.panel.tileSize, this.panel.tileSize);
                this.masak4 = entityControl.setup("/character/char6/masak-4.png", this.panel.tileSize, this.panel.tileSize);  
                this.olahraga1 = entityControl.setup("/character/char6/olahraga-1.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga2 = entityControl.setup("/character/char6/olahraga-2.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga3 = entityControl.setup("/character/char6/olahraga-3.png", this.panel.tileSize, this.panel.tileSize);
                this.olahraga4 = entityControl.setup("/character/char6/olahraga-4.png", this.panel.tileSize, this.panel.tileSize); 
                this.tidur1 = entityControl.setup("/character/char6/tidur-1.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur2 = entityControl.setup("/character/char6/tidur-2.png", this.panel.tileSize, this.panel.tileSize);
                this.tidur3 = entityControl.setup("/character/char6/tidur-3.png", this.panel.tileSize, this.panel.tileSize);   
                this.toilet1 = entityControl.setup("/character/char6/toilet-1.png", this.panel.tileSize, this.panel.tileSize);
                this.toilet2 = entityControl.setup("/character/char6/toilet-2.png", this.panel.tileSize, this.panel.tileSize); 
                break;
        }
    }

    public void update() {
        if (controlKey.up == true || controlKey.down == true || controlKey.left == true || controlKey.right == true) {
            this.collision = false;
            // panel.collisionCont.checkTile(this);

            if (this.controlKey.up) {
                direction = "up";
            }
            if (this.controlKey.down) {
                direction = "down";
            }
            if (this.controlKey.left) {
                direction = "left";
            }
            if (this.controlKey.right) {
                direction = "right";
            }
            if (this.controlKey.enter) {
                entityControl.eventCheck();
            }
            if (this.controlKey.space) {
                    
            }

            if (!this.collision) {
                switch (direction) {
                    case ("up"):
                        this.ordinat -= speed;
                        break;
                    case ("down"):
                        this.ordinat += speed;
                        break;
                    case ("left"):
                        this.absis -= speed;
                        break;
                    case ("right"):
                        this.absis += speed;
                        break;
                }
            }

            this.spriteCounter++;
            if (this.spriteCounter > 12) {
                if (this.spriteNum == 1) {
                    this.spriteNum = 2;
                } else if (this.spriteNum == 2) {
                    this.spriteNum = 1;
                }
                this.spriteCounter = 0;
            }
        } else {
            this.standCounter++;
            if (this.standCounter == 20) {
                this.spriteNum = 1;
                this.standCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graph2d) {
        switch (this.direction) {
            case ("up"):
                switch (this.spriteNum) {
                    case 1:
                        this.image = this.up1;
                        break;
                    case 2:
                        this.image = this.up2;
                        break;
                }
                break;
            case ("down"):
                switch (this.spriteNum) {
                    case 1:
                        this.image = this.down1;
                        break;
                    case 2:
                        this.image = this.down2;
                        break;
                }
                break;
            case ("left"):
                switch (this.spriteNum) {
                    case 1:
                        this.image = this.left1;
                        break;
                    case 2:
                        this.image = this.left2;
                        break;
                }
                break;
            case ("right"):
                switch (this.spriteNum) {
                    case 1:
                        this.image = this.right1;
                        break;
                    case 2:
                        this.image = this.right2;
                        break;
                }
                break;
        }

        graph2d.drawImage(this.image, this.screenAbs, this.screenOrd, null);
    }

    public String getNama() {
        return nama;
    }

    public String getPekerjaan() {
        return pekerjaan.getNamaPekerjaan();
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public int getMood() {
        return mood;
    }

    public int getUang() {
        return uang;
    }

    public String getStatus() {
        return status;
    }

    public String getLokasi() {
        return String.format(this.absis + ", " + this.ordinat);
    }

    /*
     * public List<String> getInventory() {
     * return inventory.getDaftarInventory();
     * }
     */

    public void kerja(int durasi) {
        uang += pekerjaan.getGaji() * durasi;
        mood -= durasi * 2;
        kesehatan -= durasi * 3;
        // pekerjaan.setDurasiKerja(pekerjaan.getDurasiKerja() + durasi);
        if (pekerjaan.getDurasiKerja() >= 8) {
            mood -= 10;
            kesehatan -= 10;
        }
    }

    public void olahraga(int durasi) {
        kekenyangan -= durasi * 2;
        mood += durasi * 3;
        kesehatan += durasi * 4;
        if (kesehatan > 100) {
            kesehatan = 100;
        }
        if (mood > 100) {
            mood = 100;
        }
    }

    public void berkunjung() {
        mood += 5;
        kesehatan -= 5;
        status = "berkunjung";
    }

    public void beliBarang(OtherObjects obj) {
        uang -= obj.getHarga();
        inventory.addObject(obj, 1);
        kekenyangan -= 5;
        mood -= 5;
    }

    public void pasangBarang(OtherObjects obj) {
        // inventory.removeObject(obj);
        kekenyangan -= 5;
        mood -= 5;
    }

    public void pindahBarang(OtherObjects obj, Inventory inventoryTujuan) {
        // inventory.removeObject(obj);
        inventoryTujuan.addObject(obj, 1);
        kekenyangan -= 5;
        mood -= 5;
    }

    public void rekreasi() {
        mood += 10;
        kesehatan += 5;
        kekenyangan -= 10;
        status = "rekreasi";
    }

    public void bermainGame() {
        mood += 15;
        kesehatan -= 5;
        kekenyangan -= 10;
        status = "bermain game";
    }

    public void setLocInWorld(int x, int y) {
        // ordinat = panel.tileSize * x;
        // absis = panel.tileSize * y;
        // speed = 3;
        // direction = "down";
    }

    public void getSimImage() {
        // Sim berjalan seperti biasa kekiri - kanan - kiri - atas
    }

    public void getSimMemasak() {
        // Sim memasak

    }

    public void getSimKerja() {
        // Sim Bekerja
    }

    public void getSimMakan() {
        // Sim Makan
        BufferedImage makan1, makan2;
        makan1 = setupImage("", "");
        makan2 = setupImage("", "");
    }

    public void getSimOlahraga() {
        // Sim berolahraga
        BufferedImage olahraga1;
        olahraga1 = setupImage("", "");
    }

    public void getSimBuangAir() {
        // Sim buang air
        BufferedImage buangair1;
        buangair1 = setupImage("", "");
    }

    public void getSimTidur() {
        // Sim tidur
        BufferedImage tidur1;
        tidur1 = setupImage("", "");
    }

    public void getSimBermain() {
        // Sim Bermain
        BufferedImage bermain1;
        bermain1 = setupImage(" ", " ");
    }

    public BufferedImage setupImage(String pathImage, String pathNum) {
        EntityControl eControl = new EntityControl(this.panel);
        try {
            this.image = ImageIO.read((getClass().getResourceAsStream("/assets/char" + pathNum + pathImage + ".png")));
            this.image = eControl.utility(this.image, panel.tileSize, panel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.image;
    }
}
