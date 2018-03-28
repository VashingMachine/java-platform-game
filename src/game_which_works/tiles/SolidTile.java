
package game_which_works.tiles;


public class SolidTile extends Tile{

    public SolidTile(float x, float y, float width, float height) {
        super(x, y, width, height);
        setCollisable(true);
    }

}
