package jrh.library.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static void deleteAllFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteAllFile(f);
            }
        } else if (file.exists()) {
            file.delete();
        }
    }

    public static File getFile(String Path) {
        File file = new File(Path);
        return file;
    }

    public static File isExists(String path,String name){
        return new File(path,name);
    }
    public static void deleteAllFile(String path) {
         deleteAllFile(getFile(path));
    }

    public static File getLastFile(String path) {
        return getLastFile(getFile(path));
    }

    public static File  getLastFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[files.length-i-1];
                if (isMusic(f)){
                    return f;
                }
            }
        } else if (file.exists()) {
            if (isMusic(file)){
                return file;
            }
           return  null;
        }
        return null;
    }

    public static boolean isMusic(File file) {

        if (file.isFile()) {

            String s = file.getPath();

            if (s.endsWith("mp3")) {

                return true;

            }
        }
        return false;
    }

    public static void copy(File source, File target) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            while (fileInputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void makeDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static void makeFile(String path,String name){
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
            try {
                File f = new File(path,name);
                if (!f.exists())
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
