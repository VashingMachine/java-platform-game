
package game_which_works.tiles;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class Tile {

    

    protected Rectangle visionRectangle;
    protected Shape     collisionShape;
    protected boolean   collisable = false;
    
    public Tile(float x, float y, float width, float height) {
        visionRectangle = new Rectangle(x, y, width, height);
        //for now, it can be like this:
        collisionShape = visionRectangle;
        
    }
    
    public Rectangle getVisionRectangle() {
        return visionRectangle;
    }

    public Shape getCollisionShape() {
        return collisionShape;
    }

    public boolean isCollisable() {
        return collisable;
    }
    
    public void setCollisionShape(Shape collisionShape) {
        this.collisionShape = collisionShape;
    }

    public void setCollisable(boolean collisable) {
        this.collisable = collisable;
    }

    @Override
    public String toString() {
        return "Tile: " + (int)collisionShape.getLocation().x/32 + " " + (int)collisionShape.getLocation().y/32 + "; "+ this.getClass() + '\n';
    }
    
    
    
  
    
}
