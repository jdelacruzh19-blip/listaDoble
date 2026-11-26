package com.uthh.edd.unidad2.listas;

/**
 * Bateria de pruebas de listaDoble: muestra cada metodo nuevo en los casos
 * borde (lista vacia, un elemento, inicio, final, medio e indices invalidos),
 * imprimiendo siempre el recorrido hacia adelante y en reversa.
 */
public class Main {

    private static void mostrar(String titulo, listaDoble l) {
        System.out.println(titulo);
        System.out.println("  Adelante: " + l);
        System.out.println("  Reverso : " + l.toStringReverso());
        System.out.println("  Tamanio : " + l.getTamanio());
        System.out.println();
    }

    public static void main(String[] args) {
        listaDoble l = new listaDoble();

        System.out.println("1. insertarEnPosicion");
        l.insertarEnPosicion(0, 20);   // lista vacia
        l.insertarEnPosicion(0, 10);   // inicio
        l.insertarEnPosicion(2, 40);   // final (pos == tamanio)
        l.insertarEnPosicion(2, 30);   // medio
        mostrar("Tras insertar 20(pos0), 10(pos0), 40(pos2), 30(pos2):", l);
        try {
            l.insertarEnPosicion(9, 99);
        } catch (IllegalStateException e) {
            System.out.println("Posicion 9 invalida -> " + e.getMessage());
        }
        try {
            l.insertarEnPosicion(-1, 99);
        } catch (IllegalStateException e) {
            System.out.println("Posicion -1 invalida -> " + e.getMessage());
        }
        System.out.println();

        System.out.println("2. eliminarPorValor");
        System.out.println("eliminarPorValor(30) (medio)  -> " + l.eliminarPorValor(30));
        System.out.println("eliminarPorValor(99) (no esta)-> " + l.eliminarPorValor(99));
        System.out.println("eliminarPorValor(10) (cabeza) -> " + l.eliminarPorValor(10));
        System.out.println("eliminarPorValor(40) (cola)   -> " + l.eliminarPorValor(40));
        mostrar("Queda un solo elemento:", l);
        System.out.println("eliminarPorValor(20) (unico)  -> " + l.eliminarPorValor(20));
        mostrar("Lista vacia:", l);
        System.out.println("eliminarPorValor(5) en vacia  -> " + l.eliminarPorValor(5));
        System.out.println();

        System.out.println("3. eliminarEnPosicion");
        for (int i = 1; i <= 5; i++) {
            l.insertarFinal(i * 10);
        }
        mostrar("Lista base:", l);
        System.out.println("eliminarEnPosicion(2) (medio)  -> " + l.eliminarEnPosicion(2));
        System.out.println("eliminarEnPosicion(0) (inicio) -> " + l.eliminarEnPosicion(0));
        System.out.println("eliminarEnPosicion(" + (l.getTamanio() - 1) + ") (final)  -> "
                + l.eliminarEnPosicion(l.getTamanio() - 1));
        mostrar("Resultado:", l);
        try {
            l.eliminarEnPosicion(-1);
        } catch (IllegalStateException e) {
            System.out.println("Posicion -1 invalida -> " + e.getMessage());
        }
        try {
            l.eliminarEnPosicion(l.getTamanio());
        } catch (IllegalStateException e) {
            System.out.println("Posicion " + l.getTamanio() + " invalida (== tamanio) -> " + e.getMessage());
        }
        System.out.println();

        System.out.println("4. eliminarUltimo");
        System.out.println("eliminarUltimo() -> " + l.eliminarUltimo());
        System.out.println("eliminarUltimo() -> " + l.eliminarUltimo());
        mostrar("Lista vacia:", l);
        System.out.println("eliminarUltimo() en vacia -> " + l.eliminarUltimo());
        System.out.println();

        System.out.println("5. insertarOrdenado");
        int[] datos = {50, 20, 80, 10, 90, 50, 35, 5};
        for (int d : datos) {
            l.insertarOrdenado(d);
            System.out.println("insertarOrdenado(" + d + ") -> " + l);
        }
        System.out.println();
        mostrar("Resultado final (ambos sentidos):", l);

        System.out.println("6. eliminarInicio hasta vaciar");
        while (!l.estaVacia()) {
            System.out.println("eliminarInicio() -> " + l.eliminarInicio() + "   " + l);
        }
        mostrar("Final:", l);
        System.out.println("estaVacia: " + l.estaVacia());
    }
}