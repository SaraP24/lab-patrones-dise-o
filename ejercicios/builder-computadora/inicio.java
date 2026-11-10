class Computadora {
    private final String procesador;
    private final int memoriaRam;
    private final String almacenamiento;
    private final String tarjetaGrafica;

    // El constructor es privado: solo el Builder puede crear computadoras.
    private Computadora(Builder builder) {
        // TODO 1: copiá los cuatro valores desde el builder a los atributos.
        this.procesador = null;
        this.memoriaRam = 0;
        this.almacenamiento = null;
        this.tarjetaGrafica = null;
    }

    @Override
    public String toString() {
        return "Computadora [procesador=" + procesador + ", memoriaRam=" + memoriaRam + "GB, almacenamiento="
                + almacenamiento + ", tarjetaGrafica=" + tarjetaGrafica + "]";
    }

    static class Builder {
        private final String procesador;           // obligatorio
        private int memoriaRam = 8;                // opcionales con valor por defecto
        private String almacenamiento = "256GB SSD";
        private String tarjetaGrafica = "Integrada";

        public Builder(String procesador) {
            this.procesador = procesador;
        }

        public Builder memoriaRam(int memoriaRam) {
            // TODO 2: guardá el valor y devolvé this para poder encadenar llamadas.
            return null;
        }

        public Builder almacenamiento(String almacenamiento) {
            // TODO 3: guardá el valor y devolvé this.
            return null;
        }

        public Builder tarjetaGrafica(String tarjetaGrafica) {
            // TODO 4: guardá el valor y devolvé this.
            return null;
        }

        public Computadora build() {
            // TODO 5: si memoriaRam es menor o igual a 0, lanzá
            //         new IllegalArgumentException("La memoria RAM debe ser mayor que 0")
            // TODO 6: devolvé una nueva Computadora pasándole this.
            return null;
        }
    }
}
