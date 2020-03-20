import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The Document class implements Serializable in order to allow Catalog to serialize its content (the documents array).
 */
public abstract class Document implements Serializable {
    private int id;
    private String name;
    private Map<String, String> tags;

    public Document(String name) {
        id = 0;
        this.name = name;
        this.tags = new HashMap<String, String>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public void setTitle(String title) {
        this.tags.put("title", title);
    }

    public void setAuthor(String author) {
        this.tags.put("author", author);
    }

    public void setYear(String year) {
        this.tags.put("year", year);
    }

    public void setYear(int year) {
        this.tags.put("year", String.valueOf(year));
    }

    public void setPublishingDate(String publishingDate) {
        this.tags.put("publishingDate", publishingDate);
    }

    public void setPublishingDate(Date publishingDate) {
        this.tags.put("publishingDate", String.valueOf(publishingDate));
    }

    public void printInfo() {
        System.out.println("Document " + name + " info:\n");
        for(Map.Entry<String,String> entry : tags.entrySet()) {
            System.out.println("    " + entry.getKey() + " : " + entry.getValue());
        }
    }

    public void printTags() {
        System.out.println("Document " + name + " info:");
        for(Map.Entry<String,String> entry : tags.entrySet()) {
            System.out.println("    " + entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * Abstract method to be implemented in the LocalDocument and ExternalDocument classes
     * It will open the document using the native operating system application
     */
    public abstract void openDocument();

    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", tags=" + tags.entrySet().toString() +
                '}';
    }
}
