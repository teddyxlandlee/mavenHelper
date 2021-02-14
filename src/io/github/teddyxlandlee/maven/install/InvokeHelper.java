package io.github.teddyxlandlee.maven.install;

import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.util.Collections;

public class InvokeHelper {
    static void invoke(File file) {
        String mavenHome = System.getenv("MAVEN_HOME");
        if (mavenHome == null && System.getProperty("maven.home") == null) {
            System.err.println("Did not define Maven Home");
            System.exit(-1);
        }
        if (mavenHome != null) System.setProperty("maven.home", mavenHome);

        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(file);
        Invoker invoker = new DefaultInvoker();
        request.setGoals(Collections.singletonList("install"));
        try {
            invoker.execute(request);
        } catch (MavenInvocationException e) {
            throw new RuntimeException(e);
        }
    }
}
