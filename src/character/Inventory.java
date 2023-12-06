package character;

import main.Panel;
import main.GameInterface;
import otherobject.OtherObjects;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Inventory {
    public Map<Object, Integer> objects;
    public int slotCol = 0;
    public int slotRow = 0;
    Panel panel;
    GameInterface tools;
    Graphics2D graph2d;

    public Inventory(Panel panel) {
        this.panel = panel;
        objects = new HashMap<>();
    }

    public void addObject(Object objek, int amount) {
        if (objects.containsKey(objek)) {
            objects.put(objek, objects.get(objek) + amount);
        } else {
            objects.put(objek, amount);
        }
    }

    public void showInventory() {
        int frameX = panel.tileSize * 12;
		int frameY = panel.tileSize;
		int frameWidth = panel.tileSize * 6;
		int frameHeight = panel.tileSize * 7;
        slotCol = 0;
        slotRow = 0;

        //FRAME
        tools.drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = panel.tileSize + 3;


        //DRAW PLAYER'S ITEMS
        int i = 0;
        for(Object inventory : objects.keySet()) {
            OtherObjects objek = (OtherObjects)inventory;
            graph2d.drawImage(objek.image, slotX,slotY,null);  //draw item

            //DISPLAY AMOUNT
            if(objects.get(objek) > 1) {
                int amountX;
                int amountY;
                graph2d.setFont(graph2d.getFont().deriveFont(32f));

                String num = "" + objects.get(objek);
                amountX = tools.getXforAlignToRight(num, slotX + 44);
                amountY = slotY + panel.tileSize;

                //SHADOW
                graph2d.setColor(new Color(60,60,60));
                graph2d.drawString(num,amountX,amountY);
                //NUMBER
                graph2d.setColor(Color.white);
                graph2d.drawString(num,amountX-3,amountY-3);

            }

            slotX += slotSize;

            if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24)
            {
                //reset slotX
                slotX = slotXstart;
                //next row
                slotY += slotSize;
            }
        }

        //CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = panel.tileSize;
        int cursorHeight = panel.tileSize;

        //DRAW CURSOR
        graph2d.setColor(Color.white);
        graph2d.setStroke(new BasicStroke(3));
        graph2d.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);
    }

    public void getObject(String nama) {
        int itemIndex = tools.getItemIndexOnSlot(this.slotCol, this.slotRow);
        if(itemIndex < in.size())
        {
            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword ||
                    selectedItem.type == type_axe || selectedItem.type == type_pickaxe)
            {
                currentWeapon = selectedItem;
                attack = getAttack();   //update player attack
                getAttackImage(); //update player attack image (sword/axe)
            }
            if(selectedItem.type == type_shield)
            {
                currentShield = selectedItem;
                defense = getDefense(); //update player defense
            }
            if(selectedItem.type == type_light)
            {
                if(currentLight == selectedItem)
                {
                    currentLight = null;
                }
                else
                {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if(selectedItem.type == type_consumable)
            {
                if(selectedItem.use(this) == true)
                {
                    if(selectedItem.amount > 1)
                    {
                        selectedItem.amount--;
                    }
                    else
                    {
                        inventory.remove(itemIndex);
                    }
                }
            }

        }

        for (Object inventory : objects.keySet()) {
            OtherObjects objek = (OtherObjects)inventory;
            if (objek.getNama().equals(nama)) {
                System.out.println(objek.getNama() + " : " + objects.get(objek));
                return;
            }
        }
        System.out.println("Objek tidak ditemukan.");
    }
}
