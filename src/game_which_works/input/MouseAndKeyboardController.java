
package game_which_works.input;

import game_which_works.characters.Player;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;

import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


public class MouseAndKeyboardController implements KeyListener, MouseListener, InputListener{

    private Input input;
    private Player player;
    
    public MouseAndKeyboardController(Input input, Player player) {
        setInput(input);
        this.player = player;
    }

    @Override
    public void keyPressed(int key, char c) {
        if(key == Input.KEY_ESCAPE){
            System.exit(0);
        }
        if(key == Input.KEY_W || key == Input.KEY_UP){
            player.jump();
        }
        if(key == Input.KEY_Q){
            System.err.println("test");
            
       
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        if(     key == Input.KEY_W ||
                key == Input.KEY_SPACE ||
                key == Input.KEY_UP ||
                key == Input.KEY_A ||
                key == Input.KEY_D ||
                key == Input.KEY_LEFT ||
                key == Input.KEY_RIGHT){
            
            player.setMovingFlag(false);
        }
        
    }

    @Override
    public void setInput(Input input) {
        this.input = input;
    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {
        
    }

    @Override
    public void inputStarted() {
        inputHandler();
    }
    
    private void inputHandler(){
        
        if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)){
            player.moveLeft();
        }
        if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)){
            player.moveRight();
        }
    }

    @Override
    public void mouseWheelMoved(int i) {
    }

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {
//        System.err.println(i1 + " " + i2 + " " + i3);
        
        
    }

    @Override
    public void mousePressed(int i1, int i2, int i3) {
        
       

        
        
        Vector2f throwVector = new Vector2f((i2 - player.getRenderPos().x + player.getRenderShift().x),
                                            (i3 - player.getRenderPos().y + player.getRenderShift().y))
                                            .getNormal();
        
        try{
            player.throwKremowka(throwVector);
        } catch(SlickException e){
            e.printStackTrace();
        }
        
    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {
    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {
    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {
    }

    @Override
    public void controllerLeftPressed(int i) {
    }

    @Override
    public void controllerLeftReleased(int i) {
    }

    @Override
    public void controllerRightPressed(int i) {
    }

    @Override
    public void controllerRightReleased(int i) {
    }

    @Override
    public void controllerUpPressed(int i) {
    }

    @Override
    public void controllerUpReleased(int i) {
    }

    @Override
    public void controllerDownPressed(int i) {
    }

    @Override
    public void controllerDownReleased(int i) {
    }

    @Override
    public void controllerButtonPressed(int i, int i1) {
    }

    @Override
    public void controllerButtonReleased(int i, int i1) {
    }
    
    
        
        

}
