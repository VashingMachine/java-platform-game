
package game_which_works.Physics;

import game_which_works.states.LevelState;
import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import game_which_works.tiles.*;

public abstract class PhysicObject {

    protected LevelState state;

    protected Shape     collisionShape;
    protected Vector2f  velocity;
    protected Vector2f  acceleration;
    protected float  deceleration;
    
    protected boolean   decelerationable;
    
    protected float     dx;
    protected float     dy;
    
    protected float     moveAcceleration = 0.002f;
    protected float     jumpSpeed = -0.6f;
    protected float     maximumHorizontalSpeed = 0.4f;
    
    protected boolean   isMoving; 
    protected boolean   inAir;
    protected boolean   boucing;

    
    
    
    public PhysicObject(LevelState state){
        this.state = state;
        velocity = new Vector2f(0f, 0f);
        acceleration = new Vector2f(0f, 0f);
        moveAcceleration = 0f;
        dx = 0f;
        dy = 0f;
        inAir = true;
        isMoving = false;
        decelerationable = true;
        deceleration = 0.001f;
        boucing = false;
    }
    
    public void setCollisionShape(Shape shape){
        collisionShape = shape;
    }
    
    public Shape getCollisionShape(){
        return collisionShape;
    }
    
    public void moveRight(){
        moveAcceleration = 0.001f;
        isMoving = true;
    }
    
    public void moveLeft(){
        moveAcceleration = -0.001f;
        isMoving = true;
    }
    
    public void jump(){
        if(!inAir)
            velocity.set(velocity.getX(), jumpSpeed);
    }
    
    public void setPosition(Vector2f v){
        collisionShape.setLocation(v);
    }
    
    public void throwSelf(Vector2f v){
        velocity.add(v);
    }
    
    public ArrayList<Tile> getOccupyingTiles(Tile[][] tiles) throws ArrayIndexOutOfBoundsException{
        //this is made for rectangle collisions
        int x = (int)collisionShape.getLocation().x;
        int y = (int)collisionShape.getLocation().y;
        int maxX = (int)collisionShape.getWidth() + x;
        int maxY = (int)collisionShape.getHeight() + y;
        
        int offsetX = x/32;
        int offsetY = y/32;
        int offsetMaxX = maxX/32;
        int offsetMaxY = maxY/32;
        ArrayList<Tile> arrayTiles = new ArrayList<Tile>();
  
        for(int i=offsetX; i <= offsetMaxX; i++){
            for(int j=offsetY; j <= offsetMaxY; j++){
                arrayTiles.add(tiles[i][j]);
                
            }
        }
        return arrayTiles;
    }
    
    public boolean inAir(Tile[][] tiles){
        int x = (int)collisionShape.getLocation().x;
        int y = (int)collisionShape.getLocation().y;
        int maxX = (int)collisionShape.getWidth() + x;
        int maxY = (int)collisionShape.getHeight() + y + 10;
        
        int offsetX = x/32;
        int offsetY = y/32;
        int offsetMaxX = maxX/32;
        int offsetMaxY = maxY/32;
        
        for(int i=offsetX; i <= offsetMaxX; i++){
            if(tiles[i][offsetMaxY].isCollisable()){
                return false;
            }
        }
        return true;
    }
    
    public Vector2f getPosition(){
        return collisionShape.getLocation();
    }
    
    public void setMovingFlag(boolean flag){
        isMoving = flag;
        moveAcceleration = 0f;
    }
    
    public boolean isMoving(){
        return isMoving;
    }
    
    public void actCollision(PhysicObject object, Tile tile) {}
    
    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public Vector2f getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2f acceleration) {
        this.acceleration = acceleration;
    }

    public boolean isDeccelerationable() {
        return decelerationable;
    }

    public void setDeccelerationable(boolean decelerationable) {
        this.decelerationable = decelerationable;
    }

    public float getMoveAcceleration() {
        return moveAcceleration;
    }

    public void setMoveAcceleration(float moveAcceleration) {
        this.moveAcceleration = moveAcceleration;
    }

    public float getJumpSpeed() {
        return jumpSpeed;
    }

    public void setJumpSpeed(float jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }

    public float getMaximumHorizontalSpeed() {
        return maximumHorizontalSpeed;
    }

    public void setMaximumHorizontalSpeed(float maximumHorizontalSpeed) {
        this.maximumHorizontalSpeed = maximumHorizontalSpeed;
    }
    
    public float getDecceleration() {
        return deceleration;
    }
    
    public void setDecceleration(float dc){
        deceleration = dc;
    }
    
    public boolean isBoucing() {
        return boucing;
    }

    public void setBoucing(boolean boucing) {
        this.boucing = boucing;
    }
}
