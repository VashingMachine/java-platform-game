
package game_which_works.characters;

import game_which_works.states.LevelState;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;


public class Opponent extends Character implements InteligentCharacter{

    public Opponent(LevelState state, int x, int y) throws SlickException{
        super(state);
        setSprite(new Image("data/img/player/player.png"));
        setCollisionShape(new Rectangle(x, y, sprite.getWidth(), sprite.getHeight()));
        setJumpSpeed(-0.7f);
    }
    
    public void doAI(){
        Vector2f playerPosition = state.getPlayer().getPosition();
        if(playerPosition.x > getPosition().x)
            moveRight();
        else
            moveLeft();
        jump();
    }

}
