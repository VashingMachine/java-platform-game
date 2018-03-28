
package game_which_works.level;

import game_which_works.camera.Camera;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import game_which_works.tiles.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;


public class Level {

    private TiledMap    tileMap;
    private Tile[][]    tiles;
    private float       tileSize;
    private Image       backgroundImage;
    
    public Level(String name) throws SlickException {
        loadTileMap(name);
        loadBackgroundImage("anything for now");
    }
    
    public void render(Camera cam){
        tileMap.render(-cam.getX(), -cam.getY());
        //backgroundImage.draw(300, 300, 1.5f);
    }
    
    private void loadTileMap(String name) throws SlickException {
        tileMap = new TiledMap("/data/levels/" + name + ".tmx", "data/tileSets");
        tiles = new Tile[tileMap.getWidth()][tileMap.getHeight()];
        int layerIndex = tileMap.getLayerIndex("FirstLayer");
        tileSize = tileMap.getTileWidth();
        
        for(int y = 0; y < tileMap.getHeight(); y++ ){
            for(int x = 0; x < tileMap.getWidth(); x++){
                int tileID = tileMap.getTileId(x, y, layerIndex);
                if(tileMap.getTileProperty(tileID, "collisable", "false").equals("true")){
                    tiles[x][y] = new SolidTile(x * tileSize,
                                                y * tileSize,
                                                tileSize,
                                                tileSize);
                } else {
                     tiles[x][y] = new AirTile( x * tileSize,
                                                y * tileSize,
                                                tileSize,
                                                tileSize);
                }
                
            }
        }
    }
    
    public Tile[][] getTiles() {
        return tiles;
    }
    
    private void loadBackgroundImage(String ref) throws SlickException{
        backgroundImage = new Image("data/img/player/player.png");
    }
    
    public int getTileMapHeight(){
        return tileMap.getHeight() * tileMap.getTileHeight();
    }
    
    public int getTileMapWidth(){
        return tileMap.getWidth() * tileMap.getTileWidth();
    }

}
