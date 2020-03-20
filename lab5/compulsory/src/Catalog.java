import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Catalog class implements Serializable in order to convert the state of the objects into byte streams.
 */
public class Catalog implements Serializable {
    /**
     * Static int for associating unique IDs to added documents.
     */
    private static int id = 0;
    private String name;
    private List<Document> documents;


    public Catalog(String name) {
        this.name = name;
        this.documents = new ArrayList<Document>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    /**
     * Sets the list of documents assigning them different unique ids after checking the duplicity of the documents.
     * @param documents A list of documents.
     * @throws Exception If there are two duplicates (objects or names), the method will throw an exception (I will make a new class DocumentDuplicityException which extends Exception for the optional part of the laboratory)
     */
    public void setDocuments(List<Document> documents) throws Exception {
        List<String> list = documents.stream().map(Document::getName).collect(Collectors.toList());
        if(documents.stream().anyMatch(doc -> (documents.lastIndexOf(doc) != documents.indexOf(doc))) || list.stream().anyMatch(docName -> list.indexOf(docName) == list.lastIndexOf(docName))) {
            throw new Exception("The list of documents contains duplicates (objects or names)");
        }
        this.documents = documents;
        id = 0;
        for (Document document : documents) {
            document.setId(id++);
        }
    }

    /**
     * Gets the document by its name. If it doesn't exist, an exception will be thrown (I will make a class which extends the Exception class for the optional part)
     * @param documentName
     * @return
     */
    public Document getDocument(String documentName) throws Exception {
        if(documents.stream().noneMatch(doc -> (doc.getName().equals(documentName)))) {
            throw new Exception("The specified document (" + documentName + ") doesn't belong to this catalog");
        }
        return documents.stream().filter(doc -> (doc.getName().equals(documentName))).collect(Collectors.toList()).get(0);
    }

    /**
     * Saves the catalog to an external file, using object serialization
     * @param fileName The name of the file which will be created.
     * @return
     */
    public boolean save(String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);

            out.writeObject(this);
            out.close();
            fileOutputStream.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * loads the catalog from an external file
     * @param filenName loads the catalog from an external file
     * @return
     */
    public static Catalog load(String filenName) {
        Catalog catalog = null;
        try {
            FileInputStream fileOutputStream = new FileInputStream(filenName);
            ObjectInputStream in = new ObjectInputStream(fileOutputStream);

            catalog = (Catalog) in.readObject();
            in.close();
            fileOutputStream.close();
        }
        catch (IOException | ClassNotFoundException exception) {
            System.out.println("Error: " + exception.getMessage());
            exception.printStackTrace();
        }
        return catalog;
    }

    public void addDocument(Document document) throws Exception {
        if(documents.contains(document) || documents.stream().anyMatch(doc -> doc.getName().equals(document.getName()))) {
            throw new Exception("The document already belongs to the catalog");
        }
        documents.add(document);
        documents.get(documents.indexOf(document)).setId(id++);
    }

    public Document getDocumentByName(String documentName) throws Exception {
        List<Document> list = documents.stream().filter(document -> document.getName().equals(documentName)).collect(Collectors.toList());
        if(list.isEmpty()) {
            throw new Exception("Couldn't find document named " + documentName + " in the catalog");
        }
        return list.get(0);
    }

    public Document getDocumentById(int id) throws Exception {
        List<Document> list = documents.stream().filter(document -> document.getId() == id).collect(Collectors.toList());
        if(list.isEmpty()) {
            throw new Exception("Couldn't find document with id  " + id + " in the catalog");
        }
        return list.get(0);
    }

    public int getDocumentId(Document document) throws Exception {
        if(!documents.contains(document)) {
            //there should be an exception thrown here
            throw new Exception("The document doesn't belong to the catalog");
        }
        return documents.get(documents.lastIndexOf(document)).getId();
    }

    public int getDocumentId(String documentName) throws Exception {
        List<Document> list = documents.stream().filter(document -> document.getName().equals(documentName)).collect(Collectors.toList());
        if(list.isEmpty()) {
            //there should be an exception thrown here
            throw new Exception("Document " + documentName + " doesn't belong to the catalog");
        }
        return list.get(0).getId();
    }

    public void printDocumentsInfo() {
        for (Document document : documents) {
            System.out.println("Document " + name + " info:");
            for(Map.Entry<String,String> entry : document.getTags().entrySet()) {
                System.out.println("    " + entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        documents.forEach(document -> stringBuilder.append("    - ").append(document).append('\n'));

        return "Catalog '" + name + "' contains: \n" + stringBuilder.toString();
    }
}
