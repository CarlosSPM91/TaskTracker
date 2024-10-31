package model;

public enum Status {
    TODO,
    INPROGES,
    DONE;

    public static Status chooseStat(String stat){

        switch (stat){
            case "TODO":
                return Status.TODO;
            case "INPROGES":
                return Status.INPROGES;
            case "DONE":
                return Status.DONE;
            default:
                throw new IllegalStateException("Unexpected value: " + stat);
        }

    }
}
