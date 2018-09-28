package listeners;

import javafx.scene.Scene;

/**
 * Created by kaysoro on 27/09/2018.
 */
public abstract class SceneLinkedListener {
    private Scene scene;

    public SceneLinkedListener(Scene scene){
        super();
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }
}
