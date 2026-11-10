class Computadora {
    private final String procesador;
    private final int memoriaRam;
    private final String almacenamiento;
    private final String tarjetaGrafica;

    private Computadora(Builder builder) {
        this.procesador = builder.procesador;
        this.memoriaRam = builder.memoriaRam;
        this.almacenamiento = builder.almacenamiento;
        this.tarjetaGrafica = builder.tarjetaGrafica;
    }

    @Override
    public String toString() {
        return "Computadora [procesador=" + procesador + ", memoriaRam=" + memoriaRam + "GB, almacenamiento="
                + almacenamiento + ", tarjetaGrafica=" + tarjetaGrafica + "]";
    }

    static class Builder {
        private final String procesador;
        private int memoriaRam = 8;
        private String almacenamiento = "256GB SSD";
        private String tarjetaGrafica = "Integrada";

        public Builder(String procesador) {
            this.procesador = procesador;
        }

        public Builder memoriaRam(int memoriaRam) {
            this.memoriaRam = memoriaRam;
            return this;
        }

        public Builder almacenamiento(String almacenamiento) {
            this.almacenamiento = almacenamiento;
            return this;
        }

        public Builder tarjetaGrafica(String tarjetaGrafica) {
            this.tarjetaGrafica = tarjetaGrafica;
            return this;
        }

        public Computadora build() {
            if (memoriaRam <= 0) {
                throw new IllegalArgumentException("La memoria RAM debe ser mayor que 0");
            }
            return new Computadora(this);
        }
    }
}
