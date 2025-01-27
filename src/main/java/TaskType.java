public enum TaskType {
    TODO("[T]"),
    DEADLINE("[D]"),
    EVENT("[E]");

    private final String tag;

    TaskType(String tag) {
        this.tag = tag;
    }

    // Getter method to retrieve the tag
    public String getTag() {
        return this.tag;
    }
}