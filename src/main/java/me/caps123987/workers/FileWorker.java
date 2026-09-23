package me.caps123987.workers;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileWorker {
    List<Path> filesToBeDeleted = new ArrayList<>();

    public void removeFile(Path file) {
        filesToBeDeleted.add(file);
    }

    public void run() {
        List<Path> filesToBeDeleted = new ArrayList<>(this.filesToBeDeleted);
        for (Path file : filesToBeDeleted) {
            boolean status = true;

            if (file.toFile().isDirectory()) {
                for(File f : file.toFile().listFiles()) {
                    if (!f.delete()) {
                        status = false;
                    }
                }
            }

            if (!file.toFile().delete()) {
                status = false;
            }


            if(status) {
                this.filesToBeDeleted.remove(file);
            }
        }
    }
}
