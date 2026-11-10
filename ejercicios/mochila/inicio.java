public class Mochila {

    public static int mochila(int[] valores, int[] pesos, int capacidad, int n) {
        // TODO 1 (caso base): si no quedan objetos (n == 0) o no hay capacidad, devolvé 0.

        // TODO 2: si el objeto actual (posición n - 1) pesa más que la capacidad,
        //         no puede incluirse: devolvé la llamada recursiva con n - 1.

        // TODO 3: si puede incluirse, calculá dos alternativas:
        //         incluir   = valor del objeto + mochila(..., capacidad - su peso, n - 1)
        //         noIncluir = mochila(..., capacidad, n - 1)
        // TODO 4: devolvé la mayor de las dos (Math.max).
        return 0;
    }
}
