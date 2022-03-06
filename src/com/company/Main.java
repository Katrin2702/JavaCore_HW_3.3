package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {

        openZip("D://Games/savegames/SaveGame.zip", "D://Games/savegames/");
        System.out.println(openProgress("D://Games/savegames/save2.dat"));
    }

    public static void openZip(String wayZip, String way){
         try (ZipInputStream zin = new ZipInputStream(new FileInputStream(wayZip))) {
             ZipEntry entry;
             String name;
             while ((entry = zin.getNextEntry()) != null) {
                 name = entry.getName();
                 FileOutputStream fout = new FileOutputStream(way + name);
                 for (int c = zin.read(); c != -1; c = zin.read()) {
                     fout.write(c);
                 }
                 fout.flush();
                 zin.closeEntry();
                 fout.close();
             }
         } catch (Exception ex) {
             System.out.println(ex.getMessage());
         }
    }

    public static GameProgress openProgress(String way) {
        GameProgress gameProgress = null;
        try (FileInputStream  fis = new FileInputStream(way);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
        }

}
