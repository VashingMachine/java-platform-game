
package game_which_works.camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;


public class NoCamera extends Camera{
    
    public NoCamera(GameContainer gc) {
        super(gc);
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public void handleCamera(Vector2f v) {
        
    }

}
