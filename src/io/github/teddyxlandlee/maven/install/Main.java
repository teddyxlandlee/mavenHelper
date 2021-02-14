package io.github.teddyxlandlee.maven.install;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        String repoUrl, groupId, artifactId, version;
        long nano = System.nanoTime();
        if (args.length == 0) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Maven Repo URL=");
            repoUrl = br.readLine();
            checkUrl(repoUrl);
            FilesHelper.writeUrl(repoUrl);
        } else if (args.length < 3) {
            System.err.println("Not enough parameters: you should type groupId, artifactId and version separated by spaces!");
            System.exit(-1);
        } else {
            repoUrl = FilesHelper.readUrl();
            checkUrl(repoUrl);
            groupId = args[0]; artifactId = args[1]; version = args[2];
            File pom = FilesHelper.createPom(repoUrl, groupId, artifactId, version);
            InvokeHelper.invoke(pom);
            pom.deleteOnExit();
            System.out.println("Finished in " + (System.nanoTime() - nano) / 1e9 + "s.");
        }
    }

    private static void checkUrl(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException ignore) {
            System.err.println("Invalid URL");
            System.exit(-1);
        }
    }
}
