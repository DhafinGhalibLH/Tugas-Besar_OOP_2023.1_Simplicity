package main;

import otherobject.*;
import building.*;


public class EntityGenerate {
    Panel panel;
    OtherObjects objek;

    public EntityGenerate(Panel panel) {
        this.panel = panel;
    }

    public OtherObjects getObject(String nama) {
        switch (nama) {
            case ("Jam"):
                objek = new Jam(this.panel, nama, 10, 1, 1);
                break;
            case ("Kasur Single"):
                objek = new Kasur(this.panel, nama, 50, 4, 1);
                break;
            case ("Kasur Queen Size"):
                objek = new Kasur(this.panel, nama, 100, 4, 2);
            case ("Kasur King Size"):
                objek = new Kasur(this.panel, nama, 150, 5, 2);
                break;
            case ("Toilet"):
                objek = new Toilet(this.panel, nama, 50, 1, 1);
                break;
            case ("Kompor Gas"):
                objek = new Kompor(this.panel, nama, 100, 2, 1);
                break;
            case ("Kompor Listrik"):
                objek = new Kompor(this.panel, nama, 200, 1, 1);
                break;
            case ("Meja dan Kursi"):
                objek = new MejaKursi(this.panel, nama, 50, 3, 3);
                break;
            case ("Playstation"):
                objek = new PlayStation(this.panel, nama, 150, 2, 1);
                break;
        }
        return objek;
    }
}
