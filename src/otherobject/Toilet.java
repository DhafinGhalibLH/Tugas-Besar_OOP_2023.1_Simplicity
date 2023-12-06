package otherobject;

import java.awt.Rectangle;

import main.Panel;
import character.Sim;

public class Toilet extends OtherObjects{
    public Toilet(Panel panel, String nama, int harga, int width, int height) {
        super(panel, nama, harga, width, height);
        this.solid = new Rectangle(16, 16, width - 32, height - 32);
        this.image = this.entity.setup("/object/Toilet.png", this.width, this.panel.tileSize * 2);
    }

    public void aksi(Sim player) {
        
    }
}
