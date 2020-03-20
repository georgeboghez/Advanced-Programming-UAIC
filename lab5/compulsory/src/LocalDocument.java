import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Local file
 */
public class LocalDocument extends Document {
    private String path;

    public LocalDocument(String name, String path) {
        super(name);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void openDocument(){
        try {
            System.out.println("File " + path + " opened successfully");
            Desktop.getDesktop().open(new File(path));
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "LocalDocument " + getName() + " (" + path + ')';
    }
}
