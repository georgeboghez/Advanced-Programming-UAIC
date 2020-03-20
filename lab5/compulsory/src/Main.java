import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;


public class Main {
    /**
     *
     * @param args
     * @throws Exception I will create new classes for different exceptions which extend Exception for the optional part of the laboratory
     */
    public static void main(String[] args) throws Exception {
        Catalog catalog = new Catalog("c1");
        String homedirPath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();

        ExternalDocument labURL = new ExternalDocument("LabURL", "https://profs.info.uaic.ro/~acf/java/labs/lab_05.html");
        new FileWriter(new File(homedirPath + "\\image.png")); // only a proof of work
        LocalDocument localDocument = new LocalDocument("PNGFile",homedirPath + "\\image.png");


        catalog.addDocument(labURL);
        catalog.addDocument(localDocument);

        System.out.println(catalog);
        catalog.getDocument("LabURL").openDocument();
        catalog.getDocument("PNGFile").openDocument();
        System.out.println();

        catalog.save(homedirPath + "\\catalog_" + catalog.getName());

        Catalog catalog2 = Catalog.load(homedirPath + "\\catalog_" + catalog.getName());
        System.out.println(catalog2);
    }
}
