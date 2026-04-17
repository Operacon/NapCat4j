package fun.imiku.napcat4j.component;

public enum NapCatApiPath {
    ;

    private final String path;

    NapCatApiPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
