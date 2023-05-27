import java.util.Arrays;
import java.util.Random;

public class Main {

    static Random aleatorio = new Random(42);


    private static int[][] grafoCompletoPonderado(int vertices) {
        int[][] matriz = new int[vertices][vertices];
        int valor;
        for (int i = 0; i < matriz.length; i++) {
            matriz[i][i] = -1;
            for (int j = i + 1; j < matriz.length; j++) {
                valor = aleatorio.nextInt(25) + 1;
                matriz[i][j] = valor;
                matriz[j][i] = valor;
            }
        }
        return matriz;
    }

    public static int[] caixeiroViajante(int[][] grafo, int cidadePartida) {
        int numCidades = grafo.length;
        int[] caminho = new int[numCidades + 1];
        boolean[] visitado = new boolean[numCidades];
        Arrays.fill(visitado, false);

        caminho[0] = cidadePartida;
        visitado[cidadePartida] = true;

        for (int i = 1; i < numCidades; i++) {
            int proximaCidade = -1;
            int custoMinimo = Integer.MAX_VALUE;
            int cidadeAtual = caminho[i - 1];

            for (int j = 0; j < numCidades; j++) {
                if (!visitado[j] && grafo[cidadeAtual][j] != -1 && grafo[cidadeAtual][j] < custoMinimo) {
                    proximaCidade = j;
                    custoMinimo = grafo[cidadeAtual][j];
                }
            }

            caminho[i] = proximaCidade;
            visitado[proximaCidade] = true;
        }

        caminho[numCidades] = cidadePartida;

        return caminho;
    }

    public static void main(String[] args) {
        int[][] grafo = grafoCompletoPonderado(5); // Altere o número de vértices conforme necessário
        int cidadePartida = 0; // Escolha a cidade de partida desejada

        int[] caminhoMinimo = caixeiroViajante(grafo, cidadePartida);

        System.out.println("Caminho de menor custo:");
        for (int cidade : caminhoMinimo) {
            System.out.print(cidade + " ");
        }
    }
}
