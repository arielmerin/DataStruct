package mx.unam.ciencias.edd;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello wordl!");
        ArbolAVL<Integer> arbolBinario = new ArbolAVL<>();
        arbolBinario.agrega(8);
        arbolBinario.agrega(4);
        arbolBinario.agrega(10);
        arbolBinario.agrega(2);
        arbolBinario.agrega(6);
        arbolBinario.agrega(7);
        System.out.println(arbolBinario);
        arbolBinario.elimina(6);
        System.out.println(arbolBinario);

    }
}
