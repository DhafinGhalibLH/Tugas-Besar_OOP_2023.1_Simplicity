package otherobject;

import java.awt.Rectangle;

import main.Panel;
import character.Sim;

public class Kasur extends OtherObjects{
    public Kasur(Panel panel, String nama, int harga, int width, int height) {
        super(panel, nama, harga, width, height);
        this.solid = new Rectangle(16, 16, width - 32, height - 32);

        switch (nama) {
            case ("Kasur Single"):
                this.image = this.entity.setup("/object/KasurSingle.png", this.width, this.height);
                break;
            case ("Kasur Queen Size"):
                this.image = this.entity.setup("/object/KasurQueenSize.png", this.width, this.height);
                break;
            case ("Kasur King Size"):
                this.image = this.entity.setup("/object/KasurKingSize.png", this.width, this.height);
                break;
        }
    }

    public void aksi(Sim player) {
        
    }
}
