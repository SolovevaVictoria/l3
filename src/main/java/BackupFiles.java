import java.io.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class BackupFiles {
    public static void backupFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File backupDirectory = new File("./backup");

        if (!backupDirectory.exists()) {
            backupDirectory.mkdirs();
        }

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        try {
                            Files.copy(file.toPath(), new File(backupDirectory.getPath() + "/" + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        backupFilesInDirectory(".");
        int[] numbers = {0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 2, 2, 1, 0};

        try (OutputStream os = new FileOutputStream("numbers.bin")) {
            for (int i = 0; i < numbers.length; i++) {
                os.write(numbers[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream is = new FileInputStream("numbers.bin")) {
            int data;

            while ((data = is.read()) != -1) {
                System.out.print(data + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}