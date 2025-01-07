import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class CopyFileAndReplace {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the source file");

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Program terminated.");
            return;
        }

        Path sourcePath = fileChooser.getSelectedFile().toPath();

        fileChooser.setDialogTitle("Select the target file location");
        fileChooser.setSelectedFile(new File("target.txt"));

        userSelection = fileChooser.showSaveDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            System.out.println("No save location selected. Program terminated.");
            return;
        }

        Path targetPath = fileChooser.getSelectedFile().toPath();

        try (BufferedReader reader = Files.newBufferedReader(sourcePath);
             BufferedWriter writer = Files.newBufferedWriter(targetPath)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String modifiedLine = line.replace(" ", "-");
                writer.write(modifiedLine);
                writer.newLine();
            }

            System.out.println("File successfully copied with spaces replaced by hyphens.");
            System.out.println("Target file path: " + targetPath);
        } catch (IOException e) {
            System.err.println("An error occurred during file operations: " + e.getMessage());
        }
    }
}
