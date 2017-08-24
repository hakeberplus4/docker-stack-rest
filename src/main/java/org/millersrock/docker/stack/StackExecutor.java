package org.millersrock.docker.stack;

import com.fasterxml.jackson.core.io.JsonStringEncoder;

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
                output.append(line).append("\n");
            }
            seo.setStatus(pr.waitFor());
        } catch (IOException | InterruptedException e) {
            seo.setError(e.toString());
        } finally {
            System.out.println(output.toString());
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
        Path tempPath = Files.createTempFile("docker-stack-file-", ".yml");
        Files.write(tempPath, lines, Charset.defaultCharset(), StandardOpenOption.WRITE);

        return tempPath;
    }

    static class CommandOutput {
        CommandOutput(String options) {
            this.command = "docker stack " + options;
            this.output = "";
        }

        String command;
        String output;
        Integer status;
        String error;

        void setOutput(String output) {
            this. output = "\n" + output;
        }

        public String getCommand() {
            return this.command;
        }

        public String getOutput() {
            return this.output;
        }

        public Integer getStatus() {
            return this.status;
        }

        public String getError() {
            return this.error;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public void setError(String error) {
            this.error = error;
        }

        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof CommandOutput)) return false;
            final CommandOutput other = (CommandOutput) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$command = this.getCommand();
            final Object other$command = other.getCommand();
            if (this$command == null ? other$command != null : !this$command.equals(other$command)) return false;
            final Object this$output = this.getOutput();
            final Object other$output = other.getOutput();
            if (this$output == null ? other$output != null : !this$output.equals(other$output)) return false;
            final Object this$status = this.getStatus();
            final Object other$status = other.getStatus();
            if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
            final Object this$error = this.getError();
            final Object other$error = other.getError();
            if (this$error == null ? other$error != null : !this$error.equals(other$error)) return false;
            return true;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $command = this.getCommand();
            result = result * PRIME + ($command == null ? 43 : $command.hashCode());
            final Object $output = this.getOutput();
            result = result * PRIME + ($output == null ? 43 : $output.hashCode());
            final Object $status = this.getStatus();
            result = result * PRIME + ($status == null ? 43 : $status.hashCode());
            final Object $error = this.getError();
            result = result * PRIME + ($error == null ? 43 : $error.hashCode());
            return result;
        }

        protected boolean canEqual(Object other) {
            return other instanceof CommandOutput;
        }

        public String toString() {
            return "org.millersrock.docker.stack.StackExecutor.CommandOutput(command=" + this.getCommand() + ", output=" + this.getOutput() + ", status=" + this.getStatus() + ", error=" + this.getError() + ")";
        }
    }
}
