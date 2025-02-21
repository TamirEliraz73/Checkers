import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.nls.base.BaseGame;

public class Main {
    public static void main(String[] args) {
        Lwjgl3Application app = new Lwjgl3Application(
                new BaseGame()
        );
        System.out.println("Hello World");
    }
}
