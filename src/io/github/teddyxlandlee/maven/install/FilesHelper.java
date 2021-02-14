package io.github.teddyxlandlee.maven.install;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

public class FilesHelper {
    public static File createPom(String url, String group, String artifact, String version) throws IOException {
        File file = File.createTempFile(UUID.randomUUID().toString(), "pom");
        BufferedWriter bor = new BufferedWriter(new FileWriter(file));
        String string = resourceReader('1').readLine();
        bor.write(string);
        bor.write(url);
        string = resourceReader('2').readLine();
        bor.write(string);
        bor.write(group);
        string = resourceReader('3').readLine();
        bor.write(string);
        bor.write(artifact);
        string = resourceReader('4').readLine();
        bor.write(string);
        bor.write(version);
        string = resourceReader('5').readLine();
        bor.write(string);
        bor.close();
        return file;
    }

    public static void writeUrl(String url) throws IOException {
        File file = new File("mvn.cfg");
        clearIfExists(file);
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(MathHelpers.fromInt(Meta.header));  // HEADER
        outputStream.write(Meta.version);  // Version
        byte[] bytes = url.getBytes(StandardCharsets.UTF_8);
        outputStream.write(MathHelpers.fromInt(bytes.length));
        outputStream.write(bytes);
        outputStream.close();
    }

    public static String readUrl() throws IOException {
        InputStream inputStream = new FileInputStream("mvn.cfg");
        byte[] tmp = new byte[4];
        if (inputStream.read(tmp) != 4 || Meta.header != MathHelpers.toInt(tmp)) throw new RuntimeException("mvn.cfg: bad header");
        inputStream.read();
        if (inputStream.read(tmp) != 4) throw new RuntimeException("mvn.cfg: bad string length");
        int len = MathHelpers.toInt(tmp);
        tmp = new byte[len];
        if (inputStream.read(tmp) != len) throw new RuntimeException("bad string");
        return new String(tmp, StandardCharsets.UTF_8);
    }

    private static void clearIfExists(File file) throws IOException {
        if (file.exists())
            file.delete();
        file.createNewFile();
    }

    private static BufferedReader resourceReader(char name) {
        return new BufferedReader(new InputStreamReader(FilesHelper.class.getResourceAsStream("/assets/maven-helper/prt" + name + ".txt")));
    }
}
