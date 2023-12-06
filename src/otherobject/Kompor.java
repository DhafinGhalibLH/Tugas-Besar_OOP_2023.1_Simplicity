package otherobject;

import java.awt.Rectangle;

import main.Panel;
import character.Sim;

public class Kompor extends OtherObjects{
    public Kompor(Panel panel, String nama, int harga, int width, int height) {
        super(panel, nama, harga, width, height);
        this.solid = new Rectangle(16, 16, width - 32, height - 32);

        switch (nama) {
            case ("Kompor Gas"):
                this.image = this.entity.setup("/object/KomporGas.png", this.width, this.height);
                break;
            case ("Kompor Listrik"):
                this.image = this.entity.setup("/object/KomporListrik.png", this.width, this.height);
                break;
        }
    }

    public void aksi(Sim player) {
        
    }
}
