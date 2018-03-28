
package game_which_works.camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;


public class Camera {
    
    protected Vector2f pointToFollow;
    protected Vector2f pointToSet;
    protected GameContainer gc;

    public Camera(GameContainer gc) {
        pointToFollow = new Vector2f();
        pointToSet = new Vector2f();
        this.gc = gc;
    }
    
    public void handleCamera(Vector2f v){
        pointToFollow = v;
        pointToSet.x = (pointToFollow.getX() - 500)/1.4f;
        pointToSet.y = (pointToFollow.getY() - 500)/1.1f;
    }
    
    public int getX(){
        return (int) pointToSet.getX();
    }
    
    public int getY(){
        return (int) pointToSet.getY();
    }
    
    
}
