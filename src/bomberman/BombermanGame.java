package bomberman;

import bomberman.entities.MapLoader;
import bomberman.entities.ObjectManager;
import bomberman.entities.GameScene;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BombermanGame {
    private int height;
    private int width;
    protected static BombermanGame instance;
    private GameScene gameScene;
    private ObjectManager objectManager;
    private MapLoader mapLoader;

    public BombermanGame() {

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static BombermanGame getInstance() {
        if (instance == null) {
            instance = new BombermanGame();
        }
        return instance;
    }

    public GameScene getScene() {
        return gameScene;
    }

    public void start(Stage primaryStage) {
        mapLoader = new MapLoader();
        mapLoader.loadMap(1);
        GameScene.SIZE = 40;
        objectManager = new ObjectManager(width, height);
        gameScene = new GameScene(objectManager, width, height);
        mapLoader.loadObject(objectManager);

        gameScene.setFocusTraversable(true);
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                objectManager.getBomber().onKeyEvent(event);
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {   //di chuyển xong thì trở về ban đầu
            @Override
            public void handle(KeyEvent event) {
                objectManager.getBomber().onKeyEvent(null);
            }
        });

        VBox root = new VBox();
        root.getChildren().add(gameScene);
        Scene screen1 = new Scene(root);
        primaryStage.setScene(screen1);
    }

    public void update() {
        gameScene.update();
    }
}