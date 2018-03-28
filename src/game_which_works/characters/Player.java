
package game_which_works.characters;

import game_which_works.camera.Camera;
import game_which_works.states.LevelState;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import java.lang.Math;


public class Player extends Character{
    
    protected final float kremowkaSpeed = 5f;

    public Player(LevelState state, int x, int y) throws SlickException{
        super(state);
        loadSpriteSheet("data/img/player/player_spritesheet_v4.png", 48, 72);
        setCollisionShape(new Rectangle(x, y - 15, sprite.getWidth()-15, sprite.getHeight()-30));  
        deceleration = 0.001f;
        jumpSpeed = -2f;
        setBoucing(true);
    }
    
    @Override
    public void render(Camera cam){
        render(cam, -10, -15);
    }
    
    public void throwKremowka(Vector2f direction) throws SlickException{
        direction.scale(kremowkaSpeed);
        state.addCharacter(new Projectile(state,    (int) getCollisionShape().getLocation().x,
                                                    (int) getCollisionShape().getLocation().y,
                                                    direction));
    }
    
    
}
