package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import character.Inventory;

public class GameInterface {
    Panel panel;
	Graphics2D graph2d;
    public Font maruMonica, purisaB;
    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0 : Main Menu, 1 : the second screen
    //Player Inventory
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    //Merchant NPC Inventory
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;

    int subState = 0;
    int counter = 0; // transition
    //public inventoobjectsint charIndex = 0;
    String combinedText = "";

    public GameInterface(Panel panel) {
        this.panel = panel;

        try {
            InputStream input = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, input);
            input = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, input);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //CREATE HUD OBJECT
        /*inventory objectsew OBJ_Heart(panel);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        inventory objects new OBJ_ManaCrystal(panel);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;
        inventory objectsn = new OBJ_Coin_Bronze(panel);
        coin = bronzeCoin.down1;*/
    }

    public void pauseScreen()
    {
        graph2d.setFont(graph2d.getFont().deriveFont(Font.BOLD,80F));
        int x = getXforCenteredText("Game Paused");
        int y = panel.screenHeight / 2;
        graph2d.drawString("Game Paused", x, y);

    }

    /*public void dialogue()
    {
        // WINDOW
        int x = panel.tileSize * 3;
        int y = panel.tileSize / 2;
        int width = panel.screenWidth - (panel.tileSize * 6);
        int height = panel.tileSize * 4;

        drawSubWindow(x,y,width,height);

        graph2d.setFont(graph2d.getFont().deriveFont(Font.PLAIN,28F));
        x += panel.tileSize;
        y += panel.tileSize;

        if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null)
        {
            //currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];//For display text once, enable this and disable letter by letter.(Letter by letter: The if statement below there)

            char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();

            if(charIndex < characters.length)
            {
                panel.playSE(17);//Speak sound
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s; //every loop add one character to combinedText
                currentDialogue = combinedText;

                charIndex++;
            }
            if(panel.keyH.enterPressed == true)
            {
                charIndex = 0;
                combinedText = "";
                if(panel.gameState == panel.dialogueState || panel.gameState == panel.cutsceneState)
                {
                    npc.dialogueIndex++;
                    panel.keyH.enterPressed = false;
                }
            }
        }
        else //If no text input in the array
        {
            npc.dialogueIndex = 0;
            if(panel.gameState == panel.dialogueState)
            {
                panel.gameState = panel.playState;
            }
            if(panel.gameState == panel.cutsceneState)
            {
                panel.csManager.scenePhase++;
            }
        }


        for(String line : currentDialogue.split("\n"))   // splits dialogue until "\n" as a line
        {
            graph2d.drawString(line,x,y);
            y += 40;
        }

    }*/
    public void  simInfo()
    {
        // CREATE A FRAME
        final int frameX = panel.tileSize * 2;
        final int frameY = panel.tileSize;
        final int frameWidth = panel.tileSize * 5;
        final int frameHeight = panel.tileSize * 7;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        // TEXT
        graph2d.setColor(Color.white);
        graph2d.setFont(graph2d.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + panel.tileSize;
        final int lineHeight = 35;

        // NAMES
        graph2d.drawString("Nama", textX,textY);
        textY += lineHeight;
        graph2d.drawString("Kekenyangan", textX,textY);
        textY += lineHeight;
        graph2d.drawString("Mood", textX,textY);
        textY += lineHeight;
        graph2d.drawString("Kesehatan", textX,textY);
		textY += lineHeight;
		graph2d.drawString("Pekerjaan", textX,textY);
        textY += lineHeight;
        graph2d.drawString("Uang", textX,textY);
        textY += lineHeight;
        graph2d.drawString("Status", textX,textY);
        textY += lineHeight;
        textY += lineHeight;


        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        // Reset textY
        textY = frameY + panel.tileSize;
        String value;

        value = String.valueOf(panel.sim.getNama());
        textX = getXforAlignToRight(value,tailX);
        graph2d.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(panel.sim.getKekenyangan());
        textX = getXforAlignToRight(value,tailX);
        graph2d.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(panel.sim.getMood());
        textX = getXforAlignToRight(value,tailX);
        graph2d.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(panel.sim.getPekerjaan());
        textX = getXforAlignToRight(value,tailX);
        graph2d.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(panel.sim.getUang());
        textX = getXforAlignToRight(value,tailX);
        graph2d.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(panel.sim.getStatus());
        textX = getXforAlignToRight(value,tailX);
        graph2d.drawString(value,textX,textY);
        textY += lineHeight;
    }

    public void drawTransition()
    {
        counter++;
        graph2d.setColor(new Color(0,0,0,counter*5));
        graph2d.fillRect(0,0,panel.screenWidth2,panel.screenHeight2); // screen gets darker

        if(counter == 50) //the transition input done
        {
            counter = 0;
            panel.gameState = panel.playState;
            panel.player.worldX =  panel.tileSize * panel.eHandler.tempCol;
            panel.player.worldY = panel.tileSize * panel.eHandler.tempRow;
            panel.currentMap = panel.eHandler.tempMap;
            panel.eHandler.previousEventX = panel.player.worldX;
            panel.eHandler.previousEventY = panel.player.worldY;
            panel.changeArea();
        }
    }
    public void drawTradeScreen()
    {
        switch(subState)
        {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        panel.keyH.enterPressed = false;
    }
    public void trade_select()
    {
        npc.dialogueSet = 0;
        drawDialogueScreen();

        //DRAW WINDOW
        int x = panel.tileSize * 15;
        int y = panel.tileSize * 4;
        int width = panel.tileSize *3;
        int height = (int)(panel.tileSize*3.5);
        drawSubWindow(x,y,width,height);

        //DRAW TEXTS
        x += panel.tileSize;
        y += panel.tileSize;
        graph2d.drawString("Buy",x,y);
        if(commandNum == 0)
        {
            graph2d.drawString(">", x-24,y);
            if(panel.keyH.enterPressed == true)
            {
                subState = 1;
            }
        }
        y += panel.tileSize;
        graph2d.drawString("Sell",x,y);
        if(commandNum == 1)
        {
            graph2d.drawString(">", x-24,y);
            if(panel.keyH.enterPressed == true)
            {
                subState = 2;
            }
        }
        y += panel.tileSize;
        graph2d.drawString("Leave",x,y);
        if(commandNum == 2)
        {
            graph2d.drawString(">", x-24,y);
            if(panel.keyH.enterPressed == true)
            {
                //leave trade
                commandNum = 0;
                npc.startDialogue(npc,1);
            }
        }
    }
    public void trade_buy()
    {
        // DRAW PLAYER INVENTORY
        drawInventory(panel.player, false); // i want to move cursor on merchant's inventory so cursor = false.
        // DRAW PLAYER INVENTORY
        drawInventory(npc, true);

        // DRAW HINT WINDOW
        int x = panel.tileSize * 2;
        int y = panel.tileSize * 9;
        int width = panel.tileSize * 6;
        int height = panel.tileSize * 2;
        drawSubWindow(x,y,width,height);
        graph2d.drawString("[ESC] Back", x+24,y+60);

        // DRAW PLAYER COIN WINDOW
        x = panel.tileSize * 12;
        y = panel.tileSize * 9;
        width = panel.tileSize * 6;
        height = panel.tileSize * 2;
        drawSubWindow(x,y,width,height);
        graph2d.drawString("Your Coin: " + panel.player.coin, x+24,y+60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol,npcSlotRow);
        if(itemIndex < npc.inventory.size())
        {
            x = (int)(panel.tileSize * 5.5);
            y = (int)(panel.tileSize * 5.5);
            width = (int)(panel.tileSize * 2.5);
            height = panel.tileSize;
            drawSubWindow(x,y,width,height);
            graph2d.drawImage(coin, x+10, y+8, 32,32,null );

            int price = npc.inventory.get(itemIndex).price;
            String text = String.valueOf(price);
            x = getXforAlignToRight(text,(panel.tileSize * 8) - 20);
            graph2d.drawString(text, x, y+34);

            //BUY AN ITEM
            if(panel.keyH.enterPressed == true)
            {
                if(npc.inventory.get(itemIndex).price > panel.player.coin) //not enough coin
                {
                    subState = 0;
                    npc.startDialogue(npc,2);
                }
                else
                {
                    if(panel.player.canObtainItem(npc.inventory.get(itemIndex)) == true)
                    {
                        panel.player.coin -= npc.inventory.get(itemIndex).price;  //-price
                    }
                    else
                    {
                        subState = 0;
                        npc.startDialogue(npc,3);
                    }
                }
//                else if(panel.player.inventory.size() == panel.player.maxInventorySize) //full inventory
//                {
//                    subState = 0;
//                    panel.gameState = panel.dialogueState;
//                    currentDialogue = "You can not carry any more!";
//                }
//                else
//                {
//                    panel.player.coin -= npc.inventory.get(itemIndex).price;  //-price
//                    panel.player.inventory.add(npc.inventory.get(itemIndex)); //add item to player's inventory
//                }
            }
        }
    }
    public void trade_sell()
    {
        //DRAW PLAYER INVENTORY
        drawInventory(panel.player, true);
        int x;
        int y;
        int width;
        int height;

        // DRAW HINT WINDOW
        x = panel.tileSize * 2;
        y = panel.tileSize * 9;
        width = panel.tileSize * 6;
        height = panel.tileSize * 2;
        drawSubWindow(x,y,width,height);
        graph2d.drawString("[ESC] Back", x+24,y+60);

        // DRAW PLAYER COIN WINDOW
        x = panel.tileSize * 12;
        y = panel.tileSize * 9;
        width = panel.tileSize * 6;
        height = panel.tileSize * 2;
        drawSubWindow(x,y,width,height);
        graph2d.drawString("Your Coin: " + panel.player.coin, x+24,y+60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol,playerSlotRow);
        if(itemIndex < panel.player.inventory.size())
        {
            x = (int)(panel.tileSize * 15.5);
            y = (int)(panel.tileSize * 5.5);
            width = (int)(panel.tileSize * 2.5);
            height = panel.tileSize;
            drawSubWindow(x,y,width,height);
            graph2d.drawImage(coin, x+10, y+8, 32,32,null );

            int price = panel.player.inventory.get(itemIndex).price / 2;
            String text = String.valueOf(price);
            x = getXforAlignToRight(text,(panel.tileSize * 18) - 20);
            graph2d.drawString(text, x, y+34);

            //SELL AN ITEM
            if(panel.keyH.enterPressed == true)
            {
                if(panel.player.inventory.get(itemIndex) == panel.player.currentWeapon ||
                        panel.player.inventory.get(itemIndex) == panel.player.currentShield) //equipped items cant sell
                {
                    commandNum = 0;
                    subState = 0;
                    npc.startDialogue(npc,4);
                }
                else
                {
                    if(panel.player.inventory.get(itemIndex).amount > 1)
                    {
                        panel.player.inventory.get(itemIndex).amount--;
                    }
                    else
                    {
                        panel.player.inventory.remove(itemIndex);
                    }
                    panel.player.coin += price;
                }
            }
        }

    }
    public void drawSleepScreen()
    {
        counter++;
        if(counter < 120)
        {
            panel.eManager.lighting.filterAlpha += 0.01f;
            if(panel.eManager.lighting.filterAlpha > 1f)
            {
                panel.eManager.lighting.filterAlpha = 1f;
            }
        }
        if(counter >= 120)
        {
            panel.eManager.lighting.filterAlpha -= 0.01f;
            if(panel.eManager.lighting.filterAlpha <= 0f)
            {
                panel.eManager.lighting.filterAlpha = 0f;
                counter = 0;
                panel.eManager.lighting.dayState = panel.eManager.lighting.day;
                panel.eManager.lighting.dayCounter = 0;
                panel.gameState = panel.playState;
                panel.player.getImage();
            }
        }
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow)
    {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }
    public void drawPlayerLife()
    {
        //panel.player.life = 5;
        int x = panel.tileSize/2;
        int y = panel.tileSize/2;
        int i = 0;
        int iconSize = 32;
        int manaStartX = (panel.tileSize/2) - 5;
        int manaStartY = 0;

        //DRAW MAX LIFE (BLANK)
        while(i < panel.player.maxLife/2)
        {
            graph2d.drawImage(heart_blank, x, y, iconSize, iconSize, null);
            i++;
            x += iconSize;
            manaStartY = y + 32;

            if(i % 8 == 0)
            {
                x = panel.tileSize / 2;
                y += iconSize;
            }
        }
        //reset
        x = panel.tileSize/2;
        y = panel.tileSize/2;
        i = 0;
        //DRAW CURRENT HEART // ITS LIKE COLORING THE BLANK HEARTS
        while(i < panel.player.life)
        {
            graph2d.drawImage(heart_half,x,y,iconSize, iconSize, null);
            i++;
            if(i < panel.player.life)
            {
                graph2d.drawImage(heart_full,x,y,iconSize, iconSize, null);
            }
            i++;
            x += iconSize;

            if(i % 16 == 0)
            {
                x = panel.tileSize / 2;
                y += iconSize;
            }
        }

        //DRAW MAX MANA (BLANK)
        x = manaStartX;
        y = manaStartY;
        i = 0;
        while(i < panel.player.maxMana)
        {
            graph2d.drawImage(crystal_blank,x,y, iconSize, iconSize, null);
            i++;
            x += 20;

            if(i % 10 == 0)
            {
                x = manaStartX;
                y += iconSize;
            }
        }
        //reset
        x = manaStartX;
        y = manaStartY;
        i = 0;
        //DRAW MANA
        while(i < panel.player.mana)
        {
            graph2d.drawImage(crystal_full,x,y,iconSize,iconSize,null);
            i++;
            x += 20;
            if(i % 10 == 0)
            {
                x = manaStartX;
                y += iconSize;
            }
        }
    }
    public void drawMonsterLife()
    {
        //Monster HP Bar
        for(int i = 0; i < panel.monster[1].length; i++)
        {
            inventory objects panel.monster[panel.currentMap][i];

            if(monster != null && monster.inCamera() == true)
            {
                if(monster.hpBarOn == true && monster.boss == false)
                {
                    double oneScale = (double)panel.tileSize/monster.maxLife; // (bar lenght / maxlife) Ex: if monster hp = 2, tilesize = 48px. So, 1 hp = 24px
                    double hpBarValue = oneScale * monster.life;

                    if(hpBarValue < 0) //Ex: You attack 5 hp to monster which has 3 hp. Monster's hp will be -2 and bar will ofset to left. To avoid that check if hpBarValue less than 0.
                    {
                        hpBarValue = 0;
                    }

                    graph2d.setColor(new Color(35,35,35));
                    graph2d.fillRect(monster.getScreenX()-1,monster.getScreenY()-16,panel.tileSize+2,12);

                    graph2d.setColor(new Color(255,0,30));
                    graph2d.fillRect(monster.getScreenX(),monster.getScreenY() - 15, (int)hpBarValue,10);

                    monster.hpBarCounter++;
                    if(monster.hpBarCounter > 600)  // 10
                    {
                        monster.hpBarCounter = 0;
                        monster.hpBarOn = false;
                    }
                }
                else if(monster.boss == true)
                {
                    double oneScale = (double)panel.tileSize*8/monster.maxLife; // (bar lenght / maxlife) Ex: if monster hp = 2, tilesize = 48px. So, 1 hp = 24px
                    double hpBarValue = oneScale * monster.life;
                    int x = panel.screenWidth/2 - panel.tileSize*4;
                    int y = panel.tileSize * 10;

                    if(hpBarValue < 0)  //Ex: You attack 5 hp to monster which has 3 hp. Monster's hp will be -2 and bar will ofset to left. To avoid that check if hpBarValue less than 0.
                    {
                        hpBarValue = 0;
                    }

                    graph2d.setColor(new Color(35,35,35));
                    graph2d.fillRect(x-1,y-1,panel.tileSize*8 + 2,22);

                    graph2d.setColor(new Color(255,0,30));
                    graph2d.fillRect(x,y, (int)hpBarValue,20);

                    graph2d.setFont(graph2d.getFont().deriveFont(Font.BOLD, 24f));
                    graph2d.setColor(Color.white);
                    graph2d.drawString(monster.name, x+4, y-10);
                }
            }
        }

    }
    public void drawMessage()
    {
        int messageX = panel.tileSize;
        int messageY = panel.tileSize * 4;
        graph2d.setFont(graph2d.getFont().deriveFont(Font.BOLD,24F));

        for(int i = 0; i < message.size(); i++)
        {
            if(message.get(i) != null)
            {
                //Shadow
                graph2d.setColor(Color.black);
                graph2d.drawString(message.get(i), messageX+2,messageY+2);
                //Text
                graph2d.setColor(Color.white);
                graph2d.drawString(message.get(i), messageX,messageY);

                int counter = messageCounter.get(i) + 1; //messageCounter++
                messageCounter.set(i,counter);           //set the counter to the array
                messageY += 50;

                if(messageCounter.get(i) > 150)          //display 2.5 seconds
                {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }

        }
    }
    public void drawTitleScreen()
    {
        graph2d.setColor(new Color(0,0,0));             // FILL BACKGROUND BLACK
        graph2d.fillRect(0,0, panel.screenWidth, panel.screenHeight);
        //MAIN MENU
        if(titleScreenState == 0)
        {

            //TITLE NAME
            graph2d.setFont(graph2d.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Blue Boy Adventure\n";
            int x = getXforCenteredText(text);
            int y = panel.tileSize * 3;
            //SHADOW
            graph2d.setColor(Color.gray);
            graph2d.drawString(text,x+5,y+5);
            //MAIN COLOR
            graph2d.setColor(Color.white);
            graph2d.drawString(text, x, y);

            //BLUE BOY IMAGE
            x = panel.screenWidth/2 - (panel.tileSize * 2) / 2;
            y += panel.tileSize*2;
            graph2d.drawImage(panel.player.down1,x,y,panel.tileSize*2,panel.tileSize*2,null);

            //MENU
            graph2d.setFont(graph2d.getFont().deriveFont(Font.BOLD, 48F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += panel.tileSize * 3.5;
            graph2d.drawString(text,x,y);
            if(commandNum == 0)
            {
                graph2d.drawString(">",x - panel.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += panel.tileSize;
            graph2d.drawString(text,x,y);
            if(commandNum == 1)
            {
                graph2d.drawString(">",x - panel.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += panel.tileSize;
            graph2d.drawString(text,x,y);
            if(commandNum == 2)
            {
                graph2d.drawString(">",x - panel.tileSize, y);
            }
        }
        //SECOND SCREEN
        else if(titleScreenState == 1)
        {

            //CLASS SELECTION SCREEN
            graph2d.setColor(Color.white);
            graph2d.setFont(graph2d.getFont().deriveFont(42F));

            String text = "Select your class!";
            int x = getXforCenteredText(text);
            int y = panel.tileSize * 3;
            graph2d.drawString(text,x,y);

            text = "Fighter";
            x = getXforCenteredText(text);
            y += panel.tileSize * 3;
            graph2d.drawString(text,x,y);
            if(commandNum == 0)
            {
                graph2d.drawString(">",x-panel.tileSize,y);
            }

            text = "Thief";
            x = getXforCenteredText(text);
            y += panel.tileSize;
            graph2d.drawString(text,x,y);
            if(commandNum == 1)
            {
                graph2d.drawString(">",x-panel.tileSize,y);
            }

            text = "Sorcerer";
            x = getXforCenteredText(text);
            y += panel.tileSize;
            graph2d.drawString(text,x,y);
            if(commandNum == 2)
            {
                graph2d.drawString(">",x-panel.tileSize,y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += panel.tileSize * 2;
            graph2d.drawString(text,x,y);
            if(commandNum == 3)
            {
                graph2d.drawString(">",x-panel.tileSize,y);
            }
        }
    }
    public void drawGameOverScreen()
    {
        graph2d.setColor(new Color(0,0,0,150)); //Half-black
        graph2d.fillRect(0,0,panel.screenWidth,panel.screenHeight);

        int x;
        int y;
        String text;
        graph2d.setFont(graph2d.getFont().deriveFont(Font.BOLD,110f));
        text = "Game Over";

        //Shadow
        graph2d.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = panel.tileSize * 4;
        graph2d.drawString(text,x,y);
        //Text
        graph2d.setColor(Color.white);
        graph2d.drawString(text,x-4,y-4);

        //RETRY
        graph2d.setFont(graph2d.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += panel.tileSize * 4;
        graph2d.drawString(text,x,y);
        if(commandNum == 0)
        {
            graph2d.drawString(">", x-40, y);
        }

        //BACK TO THE TITLE SCREEN
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        graph2d.drawString(text,x,y);
        if(commandNum == 1)
        {
            graph2d.drawString(">", x-40, y);
        }

    }
    public void drawOptionsScreen()
    {
        graph2d.setColor(Color.white);
        graph2d.setFont(graph2d.getFont().deriveFont(32F));

        // SUB WINDOW

        int frameX = panel.tileSize * 6;
        int frameY = panel.tileSize;
        int frameWidth = panel.tileSize * 8;
        int frameHeight = panel.tileSize * 10;

        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch(subState)
        {
            case 0: options_top(frameX,frameY); break;
            case 1: options_fullScreenNotification(frameX,frameY); break;
            case 2: options_control(frameX,frameY); break;
            case 3: options_endGameConfirmation(frameX,frameY);

        }
        panel.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY)
    {
        int textX;
        int textY;

        //TITLE
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + panel.tileSize;
        graph2d.drawString(text,textX,textY);

        //FULL SCREEN ON/OFF
        textX = frameX + panel.tileSize;
        textY += panel.tileSize * 2;
        graph2d.drawString("Full Screen", textX, textY);
        if(commandNum == 0)
        {
            graph2d.drawString(">", textX-25,textY);
            if(panel.keyH.enterPressed == true)
            {
                if(panel.fullScreenOn == false)
                {
                    panel.fullScreenOn = true;
                }
                else if(panel.fullScreenOn == true)
                {
                    panel.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        //MUSIC
        textY += panel.tileSize;
        graph2d.drawString("Music", textX, textY);
        if(commandNum == 1)
        {
            graph2d.drawString(">", textX-25,textY);
        }

        //SE
        textY += panel.tileSize;
        graph2d.drawString("SE", textX, textY);
        if(commandNum == 2)
        {
            graph2d.drawString(">", textX-25,textY);
        }

        //CONTROLS
        textY += panel.tileSize;
        graph2d.drawString("Controls", textX, textY);
        if(commandNum == 3)
        {
            graph2d.drawString(">", textX-25,textY);
            if(panel.keyH.enterPressed == true)
            {
                subState = 2;
                commandNum = 0;
            }
        }

        //END GAME
        textY += panel.tileSize;
        graph2d.drawString("End Game", textX, textY);
        if(commandNum == 4)
        {
            graph2d.drawString(">", textX-25,textY);
            if(panel.keyH.enterPressed == true)
            {
                subState = 3;
                commandNum = 0;
            }
        }

        //BACK
        textY += panel.tileSize * 2;
        graph2d.drawString("Back", textX, textY);
        if(commandNum == 5)
        {
            graph2d.drawString(">", textX-25,textY);
            if(panel.keyH.enterPressed == true)
            {
                panel.gameState = panel.playState;
                commandNum = 0;
            }
        }

        //FULL SCREEN CHECK BOX
        textX = frameX + (int)(panel.tileSize * 4.5);
        textY = frameY + panel.tileSize * 2 + 24;
        graph2d.setStroke(new BasicStroke(3));
        graph2d.drawRect(textX,textY,24,24);
        if(panel.fullScreenOn == true)
        {
            graph2d.fillRect(textX,textY,24,24);
        }

        //MUSIC VOLUME
        textY += panel.tileSize;
        graph2d.drawRect(textX,textY,120, 24); //120/5 = 24px = 1 scale
        int volumeWidth = 24 * panel.music.volumeScale;
        graph2d.fillRect(textX,textY,volumeWidth,24);

        //SE VOLUME
        textY += panel.tileSize;
        graph2d.drawRect(textX,textY,120, 24);
        volumeWidth = 24 * panel.se.volumeScale;
        graph2d.fillRect(textX,textY,volumeWidth,24);

        //SAVE OPTIONS
        panel.config.saveConfig();
    }
    public void options_fullScreenNotification(int frameX, int frameY)
    {
        int textX = frameX + panel.tileSize;
        int textY = frameY + panel.tileSize * 3;

        currentDialogue = "The change will take \neffect after restarting \nthe game.";
        for(String line: currentDialogue.split("\n"))
        {
            graph2d.drawString(line,textX,textY);
            textY += 40;
        }

        //BACK
        textY = frameY + panel.tileSize * 9;
        graph2d.drawString("Back", textX,textY);
        if(commandNum == 0)
        {
            graph2d.drawString(">", textX-25, textY);
            if(panel.keyH.enterPressed == true)
            {
                subState = 0;
            }
        }
    }
    public void options_control(int frameX,int frameY)
    {
        int textX;
        int textY;

        //TITLE
        String text = "Controls";
        textX = getXforCenteredText(text);
        textY = frameY + panel.tileSize;
        graph2d.drawString(text,textX,textY);

        textX = frameX +panel.tileSize;
        textY += panel.tileSize;
        graph2d.drawString("Move", textX,textY); textY += panel.tileSize;
        graph2d.drawString("Confirm/Attack", textX,textY); textY += panel.tileSize;
        graph2d.drawString("Shoot/Cast", textX,textY); textY += panel.tileSize;
        graph2d.drawString("Character Screen", textX,textY); textY += panel.tileSize;
        graph2d.drawString("Pause", textX,textY); textY += panel.tileSize;
        graph2d.drawString("Options", textX,textY); textY += panel.tileSize;

        //KEYS
        textX = frameX + panel.tileSize * 6;
        textY = frameY + panel.tileSize * 2;
        graph2d.drawString("WASD", textX,textY); textY += panel.tileSize;
        graph2d.drawString("ENTER", textX,textY); textY += panel.tileSize;
        graph2d.drawString("F", textX,textY); textY += panel.tileSize;
        graph2d.drawString("C", textX,textY); textY += panel.tileSize;
        graph2d.drawString("P", textX,textY); textY += panel.tileSize;
        graph2d.drawString("ESC", textX,textY); textY += panel.tileSize;


        //BACK
        textX = frameX + panel.tileSize;
        textY = frameY + panel.tileSize * 9;
        graph2d.drawString("Back", textX,textY);
        if(commandNum == 0)
        {
            graph2d.drawString(">", textX-25,textY);
            if(panel.keyH.enterPressed == true)
            {
                subState = 0;
                commandNum = 3; //back to control row
            }
        }
    }
    public void options_endGameConfirmation(int frameX, int frameY)
    {
        int textX = frameX + panel.tileSize;
        int textY = frameY + panel.tileSize * 3;

        currentDialogue = "Quit the game and \nreturn to the title screen?";
        for(String line: currentDialogue.split("\n"))
        {
            graph2d.drawString(line,textX,textY);
            textY += 40;
        }
        //YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += panel.tileSize * 3;
        graph2d.drawString(text,textX,textY);
        if(commandNum == 0)
        {
            graph2d.drawString(">",textX-25,textY);
            if(panel.keyH.enterPressed == true)
            {
                subState = 0;
                panel.ui.titleScreenState = 0;
                panel.gameState = panel.titleState;
                panel.resetGame(true);
                panel.stopMusic();
            }
        }

        //NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += panel.tileSize;
        graph2d.drawString(text,textX,textY);
        if(commandNum == 1)
        {
            graph2d.drawString(">",textX-25,textY);
            if(panel.keyH.enterPressed == true)
            {
                subState = 0;
                commandNum = 4; //back to end row
            }
        }
    }
    public void drawSubWindow(int x, int y, int width, int height)
    {
        Color c = new Color(0,0,0,210);  // R,G,B, alfa(opacity)
        graph2d.setColor(c);
        graph2d.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        graph2d.setColor(c);
        graph2d.setStroke(new BasicStroke(5));    // 5 = width of outlines of graphics
        graph2d.drawRoundRect(x+5,y+5,width-10,height-10,25,25);

    }
    public int getXforCenteredText(String text)
    {
        int textLenght;
        textLenght = (int)graph2d.getFontMetrics().getStringBounds(text,graph2d).getWidth(); // Gets width of text.
        int x = panel.screenWidth / 2 - textLenght/2;
        return x;
    }
    public int getXforAlignToRight(String text, int tailX)
    {
        int textLenght;
        textLenght = (int)graph2d.getFontMetrics().getStringBounds(text,graph2d).getWidth(); // Gets width of text.
        int x = tailX - textLenght;
        return x;
    }
    public void addMessage(String text)
    {
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D graph2d)
    {
        this.graph2d = graph2d;
        graph2d.setFont(maruMonica);
        graph2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  // Anti Aliasing // Smoothes the text
        graph2d.setColor(Color.white);

        //TITLE STATE
        if(panel.gameState == panel.titleState)
        {
            drawTitleScreen();
        }
        //OTHERS
        else
        {
            //PLAY STATE
            if(panel.gameState == panel.playState)
            {
                drawPlayerLife();
                drawMonsterLife();
                drawMessage();
            }
            //PAUSE STATE
            if(panel.gameState == panel.pauseState)
            {
                drawPlayerLife();
                drawPauseScreen();
            }
            //DIALOGUE STATE
            if(panel.gameState == panel.dialogueState)
            {
                drawDialogueScreen();
            }
            //CHARACTER STATE
            if(panel.gameState == panel.characterState)
            {
                drawCharacterScreen();
                drawInventory(panel.player, true);
            }
            //OPTIONS STATE
            if(panel.gameState == panel.optionsState)
            {
                drawOptionsScreen();
            }
            //GAME OVER STATE
            if(panel.gameState == panel.gameOverState)
            {
                drawGameOverScreen();
            }
            //TRANSITION STATE
            if(panel.gameState == panel.transitionState)
            {
                drawTransition();
            }
            //TRADE STATE
            if(panel.gameState == panel.tradeState)
            {
                drawTradeScreen();
            }
            //SLEEP STATE
            if(panel.gameState == panel.sleepState)
            {
                drawSleepScreen();
            }
        }
    }
}
