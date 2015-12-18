package seed.seyfer.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import seed.seyfer.game.FlappyDemo;
import seed.seyfer.game.sprites.Bird;
import seed.seyfer.game.sprites.Tube;

/**
 * Created by seyfer on 12/18/15.
 */
public class PlayState extends State {

    private static final int TUBE_SPACING    = 125;
    private static final int TUBE_COUNT      = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private Bird        bird;
    private Texture     background;
    //    private Tube    tube;
    private Array<Tube> tubes;
    private Texture     ground;
    private Vector2     groundPosition1, groundPosition2;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        background = new Texture("bg.png");
        ground = new Texture("ground.png");
//        tube = new Tube(100);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);

        tubes = new Array<Tube>();
        groundPosition1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPosition2 = new Vector2(camera.position.x - camera.viewportWidth / 2 + ground.getWidth(), GROUND_Y_OFFSET);

        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

        updateGround();
        bird.update(dt);

        camera.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosBottomTube().x + ((Tube.WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if (tube.collides(bird.getBounds())) {
                gameStateManager.set(new PlayState(gameStateManager));
            }
        }

        if (bird.getPosition().y < ground.getHeight() + GROUND_Y_OFFSET) {
            gameStateManager.set(new PlayState(gameStateManager));
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);

            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }

        spriteBatch.draw(ground, groundPosition1.x, groundPosition1.y);
        spriteBatch.draw(ground, groundPosition2.x, groundPosition2.y);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        ground.dispose();

//        System.out.println(PlayState.class + " disposed");
    }

    public void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition1.x + ground.getWidth()) {
            groundPosition1.add(ground.getWidth() * 2, 0);
        }
        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition2.x + ground.getWidth()) {
            groundPosition2.add(ground.getWidth() * 2, 0);
        }
    }
}
