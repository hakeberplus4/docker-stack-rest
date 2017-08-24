package org.millersrock.docker.stack;

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

    public DeployOptions() {
    }

    public String getBundleFile() {
        return this.bundleFile;
    }

    public String getComposeFile() {
        return this.composeFile;
    }

    public Boolean getPrune() {
        return this.prune;
    }

    public Boolean getWithRegistryAuth() {
        return this.withRegistryAuth;
    }

    public Enum<ResolveImageOptions> getResolveImage() {
        return this.resolveImage;
    }

    public void setBundleFile(String bundleFile) {
        this.bundleFile = bundleFile;
    }

    public void setComposeFile(String composeFile) {
        this.composeFile = composeFile;
    }

    public void setPrune(Boolean prune) {
        this.prune = prune;
    }

    public void setWithRegistryAuth(Boolean withRegistryAuth) {
        this.withRegistryAuth = withRegistryAuth;
    }

    public void setResolveImage(Enum<ResolveImageOptions> resolveImage) {
        this.resolveImage = resolveImage;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof DeployOptions)) return false;
        final DeployOptions other = (DeployOptions) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$bundleFile = this.getBundleFile();
        final Object other$bundleFile = other.getBundleFile();
        if (this$bundleFile == null ? other$bundleFile != null : !this$bundleFile.equals(other$bundleFile))
            return false;
        final Object this$composeFile = this.getComposeFile();
        final Object other$composeFile = other.getComposeFile();
        if (this$composeFile == null ? other$composeFile != null : !this$composeFile.equals(other$composeFile))
            return false;
        final Object this$prune = this.getPrune();
        final Object other$prune = other.getPrune();
        if (this$prune == null ? other$prune != null : !this$prune.equals(other$prune)) return false;
        final Object this$withRegistryAuth = this.getWithRegistryAuth();
        final Object other$withRegistryAuth = other.getWithRegistryAuth();
        if (this$withRegistryAuth == null ? other$withRegistryAuth != null : !this$withRegistryAuth.equals(other$withRegistryAuth))
            return false;
        final Object this$resolveImage = this.getResolveImage();
        final Object other$resolveImage = other.getResolveImage();
        if (this$resolveImage == null ? other$resolveImage != null : !this$resolveImage.equals(other$resolveImage))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $bundleFile = this.getBundleFile();
        result = result * PRIME + ($bundleFile == null ? 43 : $bundleFile.hashCode());
        final Object $composeFile = this.getComposeFile();
        result = result * PRIME + ($composeFile == null ? 43 : $composeFile.hashCode());
        final Object $prune = this.getPrune();
        result = result * PRIME + ($prune == null ? 43 : $prune.hashCode());
        final Object $withRegistryAuth = this.getWithRegistryAuth();
        result = result * PRIME + ($withRegistryAuth == null ? 43 : $withRegistryAuth.hashCode());
        final Object $resolveImage = this.getResolveImage();
        result = result * PRIME + ($resolveImage == null ? 43 : $resolveImage.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof DeployOptions;
    }

    public String toString() {
        return "org.millersrock.docker.stack.DeployOptions(bundleFile=" + this.getBundleFile() + ", composeFile=" + this.getComposeFile() + ", prune=" + this.getPrune() + ", withRegistryAuth=" + this.getWithRegistryAuth() + ", resolveImage=" + this.getResolveImage() + ")";
    }

    public enum ResolveImageOptions {
        ALWAYS,
        CHANGED,
        NEVER;
    }
}
