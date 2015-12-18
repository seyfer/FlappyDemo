package seed.seyfer.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import seed.seyfer.game.states.GameStateManager;
import seed.seyfer.game.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {

    public static final int WIDTH  = 480;
    public static final int HEIGHT = 800;

    public static final String TITLE = "Flappy Demo";

    private GameStateManager gameStateManager;

    private SpriteBatch batch;
//    private Texture     img;

    @Override
    public void create() {
        batch = new SpriteBatch();
//        img = new Texture("badlogic.jpg");
        gameStateManager = new GameStateManager();

        Gdx.gl.glClearColor(1, 0, 0, 1);

        gameStateManager.push(new MenuState(gameStateManager));
    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.render(batch);

//        batch.begin();
//        batch.draw(img, 0, 0);
//        batch.end();
    }
}
