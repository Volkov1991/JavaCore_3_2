import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(10, 20, 30, 40.5);
        GameProgress game2 = new GameProgress(100, 200, 300, 400.5);
        GameProgress game3 = new GameProgress(1000, 2000, 3000, 4000.5);
        saveGameProgress("/Users/a0000/Documents/Java/JavaCore3_2/Games/savegames/game1.dat", game1);
        saveGameProgress("/Users/a0000/Documents/Java/JavaCore3_2/Games/savegames/game2.dat", game2);
        saveGameProgress("/Users/a0000/Documents/Java/JavaCore3_2/Games/savegames/game3.dat", game3);

        ArrayList games = new ArrayList<>();
        games.add("game1.dat");
        games.add("game2.dat");
        games.add("game3.dat"); // исправил (было game2.dat)

        zipFiles("/Users/a0000/Documents/Java/JavaCore3_2/Games/savegames/games.zip", games);

        File dat1 = new File("/Users/a0000/Documents/Java/JavaCore3_2/Games/savegames/game1.dat");
        File dat2 = new File("/Users/a0000/Documents/Java/JavaCore3_2/Games/savegames/game2.dat");
        File dat3 = new File("/Users/a0000/Documents/Java/JavaCore3_2/Games/savegames/game3.dat");
        dat1.delete();
        dat2.delete();
        dat3.delete();
    }

    private static void zipFiles(String folders, ArrayList games) {
        try (ZipOutputStream zot = new ZipOutputStream(new FileOutputStream(folders))) {
            for (Object s : games) {
                try (FileInputStream fis = new FileInputStream((String) s)) {
                    ZipEntry entry = new ZipEntry((String) s);
                    zot.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zot.write(fis.read());
                    }
                    zot.closeEntry();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void saveGameProgress(String folders, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(folders);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}