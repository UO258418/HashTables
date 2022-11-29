package ed;

public enum NodeStatus {

    EMPTY, VALID, DELETED;

    public int getStatusNumber() {
        switch (this) {
            case EMPTY:
                return 0;

            case VALID:
                return 1;

            case DELETED:
                return 2;

            default:
                return -1;
        }
    }

}
