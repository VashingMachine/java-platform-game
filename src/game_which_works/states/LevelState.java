
package game_which_works.states;

import game_which_works.Physics.PhysicsHandler;
import game_which_works.camera.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import game_which_works.level.Level;
import game_which_works.characters.Character;
import game_which_works.characters.*;
import game_which_works.input.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.gui.TextField;

public class LevelState extends BasicGameState {
    /*
    Ta klasa zajmuje się wczytaniem odpowiedniej bitmapy(poprzez klasę Level), przechowywaniem postaci,
    zaiwera główne funkcję - pętle gry.
    */
    
    Level                   currentLevel; 
    ArrayList<Character>    characters;
    PhysicsHandler          physics;
    Player                  player;
    Camera                  camera;
    Renderable              components;
    TextField               sampleTextField;
    
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException { //taki konstruktor w Slicku
        currentLevel = new Level("level_0");
        characters = new ArrayList<Character>();
        physics = new PhysicsHandler(this);
        player = new Player(this, 50, 50);
        camera = new SmothCamera(gc, currentLevel.getTileMapWidth(), currentLevel.getTileMapHeight());

//        camera = new NoCamera(gc);
        
        gc.getInput().addListener(new MouseAndKeyboardController(gc.getInput(), player)); //dodanie obsługi klawiatury
        
        
        
        addCharacter(player);
//        addCharacter(new Opponent(this, 250, 50));
//        addCharacter(new Opponent(this, 60, 70));
        
        System.out.println(characters.get(0).getOccupyingTiles(currentLevel.getTiles()));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException { //ta metoda zajmuje się wyświtlaniem
        camera.handleCamera(player.getPosition()); //tu wyliczana jest kamera
        currentLevel.render(camera);
        for(Character character : characters){  
            character.render(camera);
            grphcs.draw(character.getCollisionShape());
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException { //tu wykonuje się pętla programu(troszkę częściej niż samo render)
        try{
            for(Character character : characters){
                if(character instanceof InteligentCharacter)
                    ((InteligentCharacter) character).doAI();
                physics.handleWithPhysics(character, delta); //dla każdej postaci stosuję fizykę
                
            }
        } catch(ConcurrentModificationException e){
            
        }
        
    }
    
    public Level getLevel(){
        return currentLevel;
    }
    
    public ArrayList<Character> getCharacters(){
        return characters;
    }
    
    public void addCharacter(Character c){
        characters.add(c);
    }
    
    public void removeCharacter(Character c){
        characters.remove(c);
    }
    
    public Player getPlayer(){
        return player;
    }

}
