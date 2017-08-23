package org.millersrock.docker.stack;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
class DeployOptions {

    /**
     * Contents of a Distributed Application Bundle file
     */
    private String bundleFile;

    /**
     * Contents of a Compose file
     */
    private String composeFile;

    /**
     * Prune services that are no longer referenced
     */
    private Boolean prune;

    /**
     * Send registry authentication details to Swarm agents
     */
    private Boolean withRegistryAuth;

    /**
     * Prune services that are no longer referenced
     */
    private Enum<ResolveImageOptions> resolveImage = ResolveImageOptions.ALWAYS;

    public enum ResolveImageOptions {
        ALWAYS,
        CHANGED,
        NEVER;
    }
}
