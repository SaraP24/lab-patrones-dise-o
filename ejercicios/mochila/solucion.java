public class Mochila {

    public static int mochila(int[] valores, int[] pesos, int capacidad, int n) {
        // Caso base
        if (n == 0 || capacidad == 0) {
            return 0;
        }

        // El objeto actual no entra: se descarta
        if (pesos[n - 1] > capacidad) {
            return mochila(valores, pesos, capacidad, n - 1);
        }

        // Entra: comparar incluirlo vs no incluirlo
        int incluir = valores[n - 1] + mochila(valores, pesos, capacidad - pesos[n - 1], n - 1);
        int noIncluir = mochila(valores, pesos, capacidad, n - 1);
        return Math.max(incluir, noIncluir);
    }
}
