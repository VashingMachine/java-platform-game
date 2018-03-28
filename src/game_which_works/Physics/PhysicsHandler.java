
package game_which_works.Physics;

import game_which_works.states.LevelState;
import game_which_works.tiles.Tile;
import java.util.ArrayList;
import org.newdawn.slick.geom.Vector2f;
import game_which_works.characters.Character;
import game_which_works.characters.Projectile;


public class PhysicsHandler {
    private float       gravity = 0.0012f;
    private float       maximumFallSpeed = 2f;
    private float       deceleration = 0.001f;
    private LevelState  levelState;
    
    public PhysicsHandler(LevelState ls){
        levelState = ls;
    }
    
    public void handleWithPhysics(PhysicObject object, int delta){
        if(object instanceof Projectile){
            handleWithPhysics((Projectile) object, delta);
        } else {
            handleVelocity(object, delta);
            handlePosition(object, delta);
        }
        
    }
    public void handleWithPhysics(Projectile object, int delta){
        handleVelocity(object, delta);
        handlePosition(object, delta);
    }
    
    private void handleDeccerelation(PhysicObject object, int delta){
        if(object.decelerationable && !object.isMoving && object.velocity.x != 0){
            
            
            
            object.velocity.x += (object.velocity.x < 0) ? object.deceleration * delta * delta : -object.deceleration * delta * delta;
            
            if(object.velocity.x > -0.001f && object.velocity.x < 0.001f)
                object.velocity.x = 0f;
        }
    }
    
    private void handleAcceleration(PhysicObject object, int delta){
        float velocity = object.velocity.x;
        float acceleration = object.moveAcceleration;
        float min = -object.maximumHorizontalSpeed;
        float max = object.maximumHorizontalSpeed;
        
        if((velocity < min && acceleration > 0) || (velocity > min && velocity < max) || (velocity > max && acceleration < 0))
            object.velocity.x += object.moveAcceleration * delta;
        object.velocity.x += object.acceleration.x * delta;
    }
    
    private void handleFalling(PhysicObject object, int delta){
        if(object.velocity.y < maximumFallSpeed)
            object.velocity.y += (object.acceleration.y + gravity) * delta;
    }
    
    private void handleVelocity(PhysicObject object, int delta){
        handleAcceleration(object, delta);
        handleDeccerelation(object, delta);
        handleFalling(object, delta);
    }
    
    private void handlePosition(PhysicObject object, int delta){
        object.dx += object.velocity.x * delta;
        object.dy += object.velocity.y * delta;
        
        if(object.dx > 1 || object.dx < -1){
            Vector2f pastPosition = object.getPosition();
            object.setPosition(object.getPosition().add(new Vector2f((int)object.dx, 0)));
            if(checkCollisions(object)){
                object.setPosition(pastPosition);
                object.velocity.x = (object.boucing) ? -object.velocity.x : 0f;
            }
            object.dx -= (int)object.dx;
        }
        
        if(object.dy > 1 || object.dy < -1){
            Vector2f pastPosition = object.getPosition();
            object.setPosition(object.getPosition().add(new Vector2f(0, (int)object.dy)));
            if(checkCollisions(object)){
                object.setPosition(pastPosition);
                object.velocity.y = (object.boucing) ? -object.velocity.y * 0.3f : 0f;
            }
            object.dy -= (int)object.dy;
        }
        
        
    }
    
    private boolean checkCollisions(PhysicObject object){
        try{
            
            for(Character character : levelState.getCharacters()){
                if(object != character && object.getCollisionShape().intersects(character.getCollisionShape())){
                    object.actCollision(character, null);
                    //return true;
                }
            }
            
            ArrayList<Tile> potentialCollisions = object.getOccupyingTiles(levelState.getLevel().getTiles());
            for(Tile tile : potentialCollisions){
                if(tile.isCollisable()){
                    object.actCollision(null, tile);
                    return true; //tutaj należy rozwinąć, jeżeli będę implementował skosy 
                }
            }
            
            object.inAir = object.inAir(levelState.getLevel().getTiles());
            return false;
            
        } catch(ArrayIndexOutOfBoundsException e){
            return true;
        }
        
    }
    
    public void setGravity(float g){
        gravity = g;
    }
}
