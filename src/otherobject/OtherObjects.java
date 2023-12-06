package otherobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Panel;
import main.EntityControl;
import character.Sim;

public abstract class OtherObjects {
    Panel panel;
    EntityControl entity;
    protected final String nama;
    protected final int harga;
    public final int width;
    public final int height;
    public BufferedImage image;
    public boolean collision;
    public Rectangle solid;
    public int absis = -1;
    public int ordinat = -1; 

    public OtherObjects(Panel panel, String nama, int harga, int width, int height) {
        this.panel = panel;
        this.nama = nama;
        this.harga = harga;
        this.width = panel.tileSize * 2 * width;
        this.height = panel.tileSize * 2 * height;
        this.collision = true;
        this.entity = new EntityControl(this.panel);
    }

    public String getNama() {
        return this.nama;
    }

    public int getHarga() {
        return this.harga;
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.image, this.absis, this.ordinat, null);
    }

    public abstract void aksi(Sim player);
}