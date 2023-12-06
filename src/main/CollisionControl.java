package main;

import character.Sim;

public class CollisionControl {
    Panel panel;
	
	public CollisionControl(Panel panel) {
		this.panel = panel;
	}
	
	public void checkTile(Sim sim) {
		int entityLeftWorldX = sim.absis + sim.solidArea.x;
		int entityRightWorldX = sim.absis + sim.solidArea.x + sim.solidArea.width;
		int entityTopWorldY = sim.ordinat + sim.solidArea.y;
		int entityBottomWorldY = sim.ordinat + sim.solidArea.y + sim.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / panel.tileSize;
		int entityRightCol = entityRightWorldX / panel.tileSize;
		int entityTopRow = entityTopWorldY / panel.tileSize;
		int entityBottomRow = entityBottomWorldY / panel.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(sim.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - sim.speed) / panel.tileSize;
			
			tileNum1 = panel.tileCont.mapTileNum[panel.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = panel.tileCont.mapTileNum[panel.currentMap][entityRightCol][entityTopRow];
			
			if(panel.tileCont.tile[tileNum1].collision == true ||
			   panel.tileCont.tile[tileNum2].collision == true) {
				sim.collision = true;
			}
			
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + sim.speed) / panel.tileSize;
			
			tileNum1 = panel.tileCont.mapTileNum[panel.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = panel.tileCont.mapTileNum[panel.currentMap][entityRightCol][entityBottomRow];
			
			if(panel.tileCont.tile[tileNum1].collision == true ||
			   panel.tileCont.tile[tileNum2].collision == true) {
				sim.collision = true;
			}
			
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - sim.speed) / panel.tileSize;
			
			tileNum1 = panel.tileCont.mapTileNum[panel.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = panel.tileCont.mapTileNum[panel.currentMap][entityLeftCol][entityBottomRow];
			
			if(panel.tileCont.tile[tileNum1].collision == true ||
			   panel.tileCont.tile[tileNum2].collision == true) {
				sim.collision = true;
			}
			
			break;
		case "right":
			entityRightCol = (entityRightWorldX + sim.speed) / panel.tileSize;
			
			tileNum1 = panel.tileCont.mapTileNum[panel.currentMap][entityRightCol][entityTopRow];
			tileNum2 = panel.tileCont.mapTileNum[panel.currentMap][entityRightCol][entityBottomRow];
			
			if(panel.tileCont.tile[tileNum1].collision == true ||
			   panel.tileCont.tile[tileNum2].collision == true) {
				sim.collision = true;
			}
			
			break;
		}
	}
	
	/* public int checkObject(Sim sim, boolean player) {
		int index = 999;
		
		for(int i = 0; i < panel.obj.length; i++) {
			if(panel.obj[i] != null) {
				// Get sim's solid area position
				sim.solidArea.x = sim.absis + sim.solidArea.x;
				sim.solidArea.y = sim.ordinat + sim.solidArea.y;
				
				// Get the object's solid area position
				panel.obj[i].solidArea.x = panel.obj[i].absis + panel.obj[i].solidArea.x;
				panel.obj[i].solidArea.y = panel.obj[i].ordinat + panel.obj[i].solidArea.y;
				
				switch (sim.direction) {
				case "up":
					sim.solidArea.y -= sim.speed;
					
					if(sim.solidArea.intersects(panel.obj[i].solidArea)) {
						if(panel.obj[i].collision == true) {
							sim.collision = true;
						}
						if(player == true) {
							index = i;
						}
					}
					
					break;
				case "down":
					sim.solidArea.y += sim.speed;
					
					if(sim.solidArea.intersects(panel.obj[i].solidArea)) {
						if(panel.obj[i].collision == true) {
							sim.collision = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					sim.solidArea.x -= sim.speed;
					
					if(sim.solidArea.intersects(panel.obj[i].solidArea)) {
						if(panel.obj[i].collision == true) {
							sim.collision = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					sim.solidArea.x += sim.speed;
					
					if(sim.solidArea.intersects(panel.obj[i].solidArea)) {
						if(panel.obj[i].collision == true) {
							sim.collision = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				}
				
				sim.solidArea.x = sim.solidAreaDefaultX;
				sim.solidArea.y = sim.solidAreaDefaultY;
				
				panel.obj[i].solidArea.x = panel.obj[i].solidAreaDefaultX;
				panel.obj[i].solidArea.y = panel.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	} */
}
