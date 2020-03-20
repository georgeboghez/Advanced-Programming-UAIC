import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * External file
 */
public class ExternalDocument extends Document {
    private String url;

    public ExternalDocument(String name, String url) {
        super(name);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void openDocument(){
        try {
            Desktop.getDesktop().browse(new URI(url));
            System.out.println("File " + url + " opened successfully");
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (URISyntaxException e) {
            System.out.println("URISyntaxException: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "ExternalDocument " + getName() + " (" + url + ')';
    }
}
