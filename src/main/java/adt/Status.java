package adt;

public enum Status {
    CONTINUE(100),
    PROCESSING(102),
    CHECKPOINT(103);


    private int code;

    Status(int code) {
        this.code = code;
    }
}
