package uv.mx.fei.logic.domain;

public class TransferProfessor {
    private static int id;
    private static String name;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        TransferProfessor.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        TransferProfessor.name = name;
    }
}
