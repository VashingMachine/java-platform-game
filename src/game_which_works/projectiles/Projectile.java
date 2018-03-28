package game_which_works.characters;

import game_which_works.Physics.PhysicObject;
import game_which_works.camera.Camera;
import game_which_works.states.LevelState;
import game_which_works.tiles.Tile;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;


public class Projectile extends Character implements InteligentCharacter{

    public Projectile(LevelState state,int x, int y, Vector2f velocity) throws SlickException{
        super(state);
        setSprite(new Image("data/projectiles/kremowka.png"));
        setCollisionShape(new Rectangle(x, y, sprite.getWidth(), sprite.getHeight()));
        setDecceleration(0.0005f);
        setBoucing(true);
        throwSelf(velocity);
    }
    
    @Override
    public void actCollision(PhysicObject object, Tile tile){
        //state.removeCharacter(this);
    }

    @Override
    public void doAI() {
        if(!inAir && -jumpSpeed > 0.01f){
            jump();
            jumpSpeed = jumpSpeed * 5/6;
        }
         //To change body of generated methods, choose Tools | Templates.
    }
    
    
}