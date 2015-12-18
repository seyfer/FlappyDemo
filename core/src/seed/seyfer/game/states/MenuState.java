package seed.seyfer.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import seed.seyfer.game.FlappyDemo;

/**
 * Created by seyfer on 12/17/15.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    public MenuState(seed.seyfer.game.states.GameStateManager gameStateManager) {
        super(gameStateManager);

        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);

        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
//        spriteBatch.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
//        spriteBatch.draw(playBtn, (FlappyDemo.WIDTH / 2) - (playBtn.getWidth() / 2), FlappyDemo.HEIGHT / 2);

        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(playBtn, camera.position.x - (playBtn.getWidth() / 2), camera.position.y);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();

        System.out.println(PlayState.class + " disposed");
    }
}
