package principal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by Spirit on 6/7/2016.
 */
public class Resurses {
    private String nameOfFile = null;
    private String parentPath = null;

    public String getNameOfFile() {
        return nameOfFile;
    }

    public String getParentPath() {
        return parentPath;
    }


    /// Cream un file chooser. Prelucram calea luata din chooser pentru a avea calea pana la fisier si numele fisierului
    public void chooser(JLabel lblNewLabel) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        // chooser.addChoosableFileFilter(new FileNameExtensionFilter("Xml Documents", "xml"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents", "txt"));
        chooser.setDialogTitle("Browse the folder to process");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            lblNewLabel.setText(chooser.getSelectedFile().getAbsolutePath());
            System.out.println(chooser.getSelectedFile().getName());

            nameOfFile = chooser.getSelectedFile().getName();
            int pos = nameOfFile.lastIndexOf(".");
            if (pos > 0) {
                nameOfFile = nameOfFile.substring(0, pos);
            }
            System.out.println(nameOfFile);
            parentPath = chooser.getSelectedFile().getParent();
            System.out.println(parentPath);
        } else {
            lblNewLabel.setText("No Selection ");
        }
    }
}
