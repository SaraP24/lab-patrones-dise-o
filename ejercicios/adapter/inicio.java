import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ProcesadorPago> procesadores = List.of(
                new PagoModerno(),
                new AdaptadorPasarelaAntigua(new PasarelaAntigua()));

        for (ProcesadorPago p : procesadores) {
            boolean ok = p.pagar("Ana", 49.99);
            System.out.println("Resultado: " + ok);
        }
    }
}

// Interfaz que espera nuestro sistema (Target)
interface ProcesadorPago {
    boolean pagar(String cliente, double euros);
}

class PagoModerno implements ProcesadorPago {
    public boolean pagar(String cliente, double euros) {
        System.out.println("[Moderno] Cobro de " + euros + " EUR a " + cliente);
        return true;
    }
}

// Clase heredada que NO podemos modificar (Adaptee): usa XML y céntimos
class PasarelaAntigua {
    String enviarXml(String xml) {
        System.out.println("[Antigua] Procesando XML: " + xml);
        return "OK-200";
    }
}

// Adapter: implementa la interfaz nueva y traduce las llamadas a la antigua
class AdaptadorPasarelaAntigua implements ProcesadorPago {
    private final PasarelaAntigua pasarela;

    AdaptadorPasarelaAntigua(PasarelaAntigua pasarela) { this.pasarela = pasarela; }

    public boolean pagar(String cliente, double euros) {
        // TODO 1: convierte euros a céntimos con Math.round(euros * 100)
        // TODO 2: construye el XML exactamente así:  <pago cliente="Ana" centimos="4999"/>
        // TODO 3: envíalo con pasarela.enviarXml(xml)
        // TODO 4: devuelve true si la respuesta empieza por "OK"
        throw new UnsupportedOperationException("TODO: AdaptadorPasarelaAntigua.pagar()");
    }
}
