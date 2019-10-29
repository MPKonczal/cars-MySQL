package pl.edu.utp.wtie.homeworkweek7.model.enumeration;

public enum Mark {
    ALFA_ROMEO("Alfa Romeo"),
    FERRARI("Ferrari"),
    HONDA("Honda"),
    MCLAREN("McLaren"),
    MERCEDES_BENZ("Mercedes-Benz"),
    RENAULT("Renault"),
    TOYOTA("Toyota");

    private String mark;

    Mark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}
