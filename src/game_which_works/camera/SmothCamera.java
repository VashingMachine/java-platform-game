
package game_which_works.camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;


public class SmothCamera extends Camera {
    
    final protected float resizeX = 0.4f;
    final protected float resizeY = 0.4f;
    
    final protected float smoothParam = 1f;
    
    protected int mapSizeY;
    protected int mapSizeX;
    
    protected int visionRectangleX;
    protected int visionRectangleY;

    public SmothCamera(GameContainer gc, int mapSizeX, int mapSizeY) {
        super(gc);
        visionRectangleX = 600;
        visionRectangleY = 400;
        this.mapSizeX = mapSizeX;
        this.mapSizeY = mapSizeY;
    }
    
    public void handleCamera(Vector2f v){
        pointToFollow = v;
        float pX = pointToFollow.getX() / this.mapSizeX;
        float pY = pointToFollow.getY() / this.mapSizeY;
                
        pointToSet.x = pointToFollow.getX() - visionRectangleX * pX - 400;
        pointToSet.y = pointToFollow.getY() - visionRectangleY * pY;

    }
    
    public int getX(){
        return (int) pointToSet.getX();
    }
    
    public int getY(){
        return (int) pointToSet.getY();
    }

}
