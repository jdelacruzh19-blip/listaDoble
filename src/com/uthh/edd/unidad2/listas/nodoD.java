package com.uthh.edd.unidad2.listas;

/**
 * Nodo de una lista doblemente ligada de enteros.
 * Guarda un dato y dos enlaces: al nodo siguiente y al nodo anterior.
 */
public class nodoD {
    private int dato;
    private nodoD siguiente;
    private nodoD anterior;

    /** Crea un nodo sin enlaces (siguiente y anterior en null). */
    public nodoD(int x) {
        dato = x;
        siguiente = null;
        anterior = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public nodoD getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nodoD siguiente) {
        this.siguiente = siguiente;
    }

    public nodoD getAnterior() {
        return anterior;
    }

    public void setAnterior(nodoD anterior) {
        this.anterior = anterior;
    }
}
