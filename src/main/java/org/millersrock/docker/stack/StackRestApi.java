package org.millersrock.docker.stack;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Manage Docker stacks
 * Created by mark on 8/19/17.
 * <p>
 * Commands:
 * /api/ls          List stacks
 * /api/ps          List the tasks in the stack
 * /api/services    List the services in the stack
 * /api/deploy      Deploy a new stack or update an existing stack
 * /api/rm          Remove one or more stacks
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/api")
public class StackRestApi {

    /**
     * List stacks
     *
     * @param format Pretty-print tasks using a Go template
     */
    @RequestMapping(value = "ls")
    @ResponseBody
    public StackExecutor.CommandOutput ls(@RequestParam(name = "format", required = false) String format) {
        StringBuilder sb = new StringBuilder("ls");
        if (format != null) {
            sb.append(" --format ").append(format);
        }
        return StackExecutor.execute(sb.toString());
    }

    /**
     * List the tasks in the stack
     *
     * @param stackName Name of stack you want to list tasks for
     * @param filter    Filter output based on conditions provided
     * @param format    Pretty-print tasks using a Go template
     * @param noResolve Do not map IDs to Names
     * @param noTrunc   Do not truncate output
     * @param quiet     Only display task IDs
     */
    @RequestMapping(value = "ps", method = RequestMethod.GET)
    public StackExecutor.CommandOutput ps(
            @RequestParam(name = "stackName") String stackName,
            @RequestParam(name = "filter", required = false) String filter,
            @RequestParam(name = "format", required = false) String format,
            @RequestParam(name = "noResolve", required = false) String noResolve,
            @RequestParam(name = "noTrunc", required = false) String noTrunc,
            @RequestParam(name = "quiet", required = false) String quiet) {

        StringBuilder sb = new StringBuilder("ps ").append(stackName);
        if (filter != null) {
            sb.append(" --filter ").append(filter);
        }
        if (format != null) {
            sb.append(" --format ").append(format);
        }
        if (noResolve != null) {
            sb.append(" --no-resolve ").append(noResolve);
        }
        if (noTrunc != null) {
            sb.append(" --no-trunc ").append(noTrunc);
        }
        if (quiet != null) {
            sb.append(" --quiet ").append(quiet);
        }
        return StackExecutor.execute(sb.toString());
    }

    /**
     * Deploy a new stack or update an existing stack
     *
     * @param stackName Name of stack to deploy
     * @param filter    Filter output based on conditions provided
     * @param format    Pretty-print tasks using a Go template
     * @param quiet     Only display IDs
     */
    @RequestMapping(value = "services", method = RequestMethod.GET)
    public StackExecutor.CommandOutput services(
            @RequestParam(name = "stackName") String stackName,
            @RequestParam(name = "filter", required = false) String filter,
            @RequestParam(name = "format", required = false) String format,
            @RequestParam(name = "quiet", required = false) String quiet) {

        StringBuilder sb = new StringBuilder("services ").append(stackName);
        if (filter != null) {
            sb.append(" --filter ").append(filter);
        }
        if (format != null) {
            sb.append(" --format ").append(format);
        }
        if (quiet != null) {
            sb.append(" --quiet ").append(quiet);
        }
        return StackExecutor.execute(sb.toString());
    }

    /**
     * Deploy a new stack or update an existing stack
     *
     * @param stackName Name of stack to deploy
     */
    @RequestMapping(value = "deploy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody StackExecutor.CommandOutput deploy(
            @RequestParam(name = "stackName") String stackName, @RequestBody DeployOptions options) {

        StringBuilder sb = new StringBuilder("deploy ").append(stackName);
        if (options.getPrune() != null) {
            sb.append(" --prune ").append(options.getPrune());
        }
        if (options.getWithRegistryAuth() != null) {
            sb.append(" --with-registry-auth ").append(options.getWithRegistryAuth());
        }
        if (options.getComposeFile() != null) {
            return StackExecutor.executeWithFile(sb.append(" --compose-file "), options.getComposeFile());
        } else {
            return StackExecutor.executeWithFile(sb.append(" --bundle-file "), options.getBundleFile());
        }
    }

    /**
     * Remove one or more stacks
     *
     * @param stackName Name of stack you want to list tasks for
     */
    @RequestMapping(value = "rm", method = RequestMethod.POST)
    public @ResponseBody StackExecutor.CommandOutput rm(
            @RequestParam(name = "stackName") String stackName,
            @RequestParam(name = "otherStackNames", required = false) String otherStackNames) {

        StringBuilder sb = new StringBuilder("rm ").append(stackName);
        return StackExecutor.execute(sb.toString());
    }
}
