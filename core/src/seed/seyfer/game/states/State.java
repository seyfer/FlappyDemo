package seed.seyfer.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by seyfer on 12/17/15.
 */
public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3            mouse;
    protected GameStateManager   gameStateManager;

    /**
     * @param gameStateManager GameStateManager
     */
    protected State(GameStateManager gameStateManager) {

        this.gameStateManager = gameStateManager;

        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();

    /**
     * @param dt delta time
     */
    public abstract void update(float dt);

    /**
     * @param spriteBatch SpriteBatch to render with
     */
    public abstract void render(SpriteBatch spriteBatch);

    public abstract void dispose();

}
