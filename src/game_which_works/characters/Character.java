
package game_which_works.characters;

import game_which_works.Physics.PhysicObject;
import game_which_works.camera.Camera;
import game_which_works.states.LevelState;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;


public abstract class Character extends PhysicObject {

    protected Image         sprite;
    protected SpriteSheet   spriteSheet;
    protected Vector2f      renderPos;
    protected Animation     rightAnimation;
    protected Animation     leftAnimation;
    protected Animation     lastTimeAnimation;
    protected Vector2f     renderShift;
    
    public Character(LevelState state) {
        super(state);
        renderPos = new Vector2f();
        renderShift = new Vector2f();
    }
    
    protected void setSprite(Image sprite) {
        this.sprite = sprite;
    }
    
    public void render(Camera cam){
        render(cam, 0, 0);
    }
    
    public void render(Camera cam, int x, int y){
        
        renderShift.x = x;
        renderShift.y = y;
        renderPos.x = getPosition().x - cam.getX() + x;
        renderPos.y = getPosition().y - cam.getY() + y;
        getDisplay().draw(renderPos.x, renderPos.y);
    }
    
    public Vector2f getRenderPos() {
        return renderPos;
    }
    
    public Vector2f getRenderShift(){
        return renderShift;
    }
    
    
    public void loadSpriteSheet(String loc, int tw, int th) throws SlickException{
        
        spriteSheet = new SpriteSheet(loc, tw, th);
        Image[] frames = new Image[spriteSheet.getHorizontalCount() * spriteSheet.getVerticalCount()];
        setSprite(spriteSheet.getSprite(0, 0));
        int counter = 0;
        for(int i=0;i<spriteSheet.getVerticalCount();i++){
            for(int j=0;j<spriteSheet.getHorizontalCount();j++){
                frames[counter] = spriteSheet.getSprite(j, i);
                counter++;
            }
        }
        
        rightAnimation = new Animation(frames, 125);
        leftAnimation = new Animation();
        for(Image frame : frames){
            leftAnimation.addFrame(frame.getFlippedCopy(true, false), 125);
        }
    }
    
    public Renderable getDisplay(){
        
        if(rightAnimation != null && leftAnimation != null){
            if(velocity.x > 0){
                
                if(rightAnimation.isStopped())
                    rightAnimation.start();
                lastTimeAnimation = rightAnimation;
                return rightAnimation;
                
            } else if (velocity.x < 0) {
                
                if(leftAnimation.isStopped())
                    leftAnimation.start();
                lastTimeAnimation = leftAnimation;
                return leftAnimation;
                
            } else {
                
                if(lastTimeAnimation != null){
                    lastTimeAnimation.stop();
                    return lastTimeAnimation;
                } else {
                    return sprite;
                }
            }
           
        } else return sprite;
            
    }
    
    
}
