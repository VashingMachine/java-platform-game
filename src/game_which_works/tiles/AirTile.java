
package game_which_works.tiles;


public class AirTile extends Tile{

    public AirTile(float x, float y, float width, float height) {
        super(x, y, width, height);
        setCollisable(false);
    }

}
