package py.una.pol.web.tarea6.model;

public enum Role {
    COMPRADOR(0), VENDEDOR(1);

    private int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
