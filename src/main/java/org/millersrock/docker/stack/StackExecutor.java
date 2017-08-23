package org.millersrock.docker.stack;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

class StackExecutor {


    static CommandOutput execute(String dockerStackOptions) {
        CommandOutput seo = new CommandOutput(dockerStackOptions);
        StringBuilder output = new StringBuilder();
        try {
            Process pr = Runtime.getRuntime().exec(seo.getCommand());
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                output.append(line);
            }
            seo.setStatus(pr.waitFor());
        } catch (IOException | InterruptedException e) {
            seo.setError(e.toString());
        } finally {
            seo.setOutput(output.toString());
        }
        return seo;
    }

    static CommandOutput executeWithFile(StringBuilder dockerStackOptions, String fileContents) {

        CommandOutput seo = new CommandOutput(dockerStackOptions.toString());
        try {
            Path tmpPath = writeTempFile(fileContents);
            dockerStackOptions.append(tmpPath.toAbsolutePath().toString());
            seo = execute(dockerStackOptions.toString());
            Files.deleteIfExists(tmpPath);
        } catch (IOException ioe) {
            seo.setError(ioe.toString());
        }
        return seo;
    }

    private static Path writeTempFile(String fileContents) throws IOException {

        List<String> lines = Collections.singletonList(fileContents);
        Path tempPath = Files.createTempFile("docker-stack-file", "yml");
        Files.write(tempPath, lines, Charset.defaultCharset(), StandardOpenOption.WRITE);

        return tempPath;
    }

    @Data
    @Accessors
    static class CommandOutput {
        CommandOutput(String options) {
            this.command = "docker stack " + options;
            this.output = "";
        }

        String command;
        String output;
        Integer status;
        String error;
    }
}
