package game_which_works;

import game_which_works.states.LevelState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Game_which_works extends StateBasedGame{
    /*
    Główna klasa gry. StateBasedGame - czyli oparta na wielu poziomach. Ułatwia wstawianie wiele elementów intreface'u.
    */
   
    public static void main(String[] args) throws SlickException {

        AppGameContainer agc = new AppGameContainer(new Game_which_works("ROZPIERDALATOR"));
        agc.setDisplayMode(1280, 720, false);
        agc.start();
    }

    public Game_which_works(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {    //w tej klasie należy inicjować wszystkie staty(tylko tyle wiem xd)
        LevelState levelState = new LevelState(); 
        this.addState(levelState);
        this.enterState(0);
    }
    
}
