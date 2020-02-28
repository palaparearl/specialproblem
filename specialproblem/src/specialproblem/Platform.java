package specialproblem;

import java.awt.Graphics;

public class Platform extends State{
	
	private World world;

	public Platform(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
//		Tile.tiles[0].render(g, 0, 0);
	}

}
