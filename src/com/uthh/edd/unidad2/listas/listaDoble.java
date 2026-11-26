package com.uthh.edd.unidad2.listas;

/**
 * Lista doblemente ligada de enteros.
 *
 * Usa dos apuntadores a los extremos: cabeza (primer nodo) y cola (ultimo nodo).
 * Cada nodo (nodoD) enlaza al siguiente y al anterior.
 *
 * Las posiciones son base 0: el primer nodo es la posicion 0 y el ultimo es
 * tamanio-1. Un indice invalido lanza IllegalStateException("Valor fuera de rango").
 */
public class listaDoble {
    private int tamanio;   // numero de nodos que tiene la lista
    private nodoD cabeza;  // primer nodo (null si esta vacia)
    private nodoD cola;    // ultimo nodo (null si esta vacia)

    /** Crea una lista vacia. */
    public listaDoble() {
        tamanio = 0;
        cabeza = null;
        cola = null;
    }

    public int getTamanio() {
        return tamanio;
    }

    /** @return true si la lista no tiene nodos. */
    public boolean estaVacia() {
        return cabeza == null && cola == null;
    }

    /** Busca un dato recorriendo de cabeza a cola. */
    public boolean buscar(int dato) {
        nodoD aux = cabeza;
        while (aux != null) {
            if (aux.getDato() == dato) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    /** Recorrido de cabeza a cola, p. ej. null<->[10]<->[20]<->null */
    @Override
    public String toString() {
        String res = "null<->";
        nodoD aux = cabeza;
        while (aux != null) {
            res += "[" + aux.getDato() + "]<->";
            aux = aux.getSiguiente();
        }
        res += "null";
        return res;
    }

    /** Recorrido inverso, de cola a cabeza, usando los enlaces anterior. */
    public String toStringReverso() {
        String res = "null<->";
        nodoD aux = cola;
        while (aux != null) {
            res += "[" + aux.getDato() + "]<->";
            aux = aux.getAnterior();
        }
        res += "null";
        return res;
    }

    /**
     * Devuelve el nodo en la posicion pos (0..tamanio-1).
     * Recorre desde el extremo mas cercano (cabeza o cola).
     */
    public nodoD obtenerNodo(int pos) {
        if (pos < 0 || pos >= tamanio) {
            throw new IllegalStateException("Valor fuera de rango");
        }
        nodoD aux;
        if (pos < tamanio / 2) {
            aux = cabeza;
            for (int i = 0; i < pos; i++) {
                aux = aux.getSiguiente();
            }
        } else {
            aux = cola;
            for (int i = tamanio - 1; i > pos; i--) {
                aux = aux.getAnterior();
            }
        }
        return aux;
    }

    /** Cambia el dato del nodo en la posicion pos. */
    public void actualizar(int pos, int valor) {
        nodoD val = obtenerNodo(pos);
        val.setDato(valor);
    }

    /** Inserta un dato al inicio de la lista. */
    public void insertarInicio(int valor) {
        nodoD nuevo = new nodoD(valor);

        if (estaVacia()) {
            cola = nuevo;                  // unico nodo: tambien es la cola
        } else {
            nuevo.setSiguiente(cabeza);    // nuevo -> antigua cabeza
            cabeza.setAnterior(nuevo);     // antigua cabeza -> nuevo
        }
        cabeza = nuevo;
        tamanio++;
    }

    /** Inserta un dato al final de la lista. */
    public void insertarFinal(int valor) {
        nodoD nuevo = new nodoD(valor);

        if (estaVacia()) {
            cabeza = nuevo;                // unico nodo: tambien es la cabeza
        } else {
            cola.setSiguiente(nuevo);      // antigua cola -> nuevo
            nuevo.setAnterior(cola);       // nuevo -> antigua cola
        }
        cola = nuevo;
        tamanio++;
    }

    /**
     * Inserta un dato en la posicion pos (0..tamanio). El nodo que estaba en
     * pos (y los siguientes) se recorren una posicion. pos == tamanio inserta al final.
     */
    public void insertarEnPosicion(int pos, int dato) {
        if (pos < 0 || pos > tamanio) {
            throw new IllegalStateException("Valor fuera de rango");
        }
        if (pos == 0) {
            insertarInicio(dato);
        } else if (pos == tamanio) {
            insertarFinal(dato);
        } else {
            nodoD actual = obtenerNodo(pos);       // quedara despues del nuevo
            nodoD previo = actual.getAnterior();   // quedara antes del nuevo
            nodoD nuevo = new nodoD(dato);

            nuevo.setAnterior(previo);
            nuevo.setSiguiente(actual);
            previo.setSiguiente(nuevo);
            actual.setAnterior(nuevo);
            tamanio++;
        }
    }

    /**
     * Inserta un dato manteniendo la lista ordenada de menor a mayor.
     * Se asume que la lista ya esta ordenada (si solo se llena con este
     * metodo, siempre lo estara). Actualiza cabeza y cola cuando corresponde.
     */
    public void insertarOrdenado(int dato) {
        if (estaVacia() || dato <= cabeza.getDato()) {
            insertarInicio(dato);              // menor o igual que la cabeza
        } else if (dato >= cola.getDato()) {
            insertarFinal(dato);               // mayor o igual que la cola
        } else {
            // Caso intermedio: primer nodo cuyo dato sea mayor o igual al nuevo
            nodoD aux = cabeza.getSiguiente();
            while (aux.getDato() < dato) {
                aux = aux.getSiguiente();
            }
            nodoD previo = aux.getAnterior();
            nodoD nuevo = new nodoD(dato);

            nuevo.setAnterior(previo);
            nuevo.setSiguiente(aux);
            previo.setSiguiente(nuevo);
            aux.setAnterior(nuevo);
            tamanio++;
        }
    }

    /**
     * Metodo auxiliar privado: saca un nodo de la lista reconectando a sus
     * vecinos y actualizando cabeza, cola y tamanio. Cubre los casos de
     * unico nodo, primero, ultimo y nodo intermedio.
     */
    private void desenlazar(nodoD nodo) {
        nodoD previo = nodo.getAnterior();
        nodoD sig = nodo.getSiguiente();

        if (previo == null) {          // era la cabeza
            cabeza = sig;
        } else {
            previo.setSiguiente(sig);
        }
        if (sig == null) {             // era la cola
            cola = previo;
        } else {
            sig.setAnterior(previo);
        }
        nodo.setSiguiente(null);       // el nodo eliminado queda aislado
        nodo.setAnterior(null);
        tamanio--;
    }

    /** Elimina el primer nodo y devuelve su dato. */
    public int eliminarInicio() {
        if (estaVacia()) {
            throw new IllegalStateException("No se puede eliminar con la lista vacia");
        }
        int dato = cabeza.getDato();
        desenlazar(cabeza);
        return dato;
    }

    /** Elimina el ultimo nodo. @return false si la lista estaba vacia. */
    public boolean eliminarUltimo() {
        if (estaVacia()) {
            return false;
        }
        desenlazar(cola);
        return true;
    }

    /** Elimina la primera aparicion de x. @return true si se elimino, false si no existe. */
    public boolean eliminarPorValor(int x) {
        nodoD aux = cabeza;
        while (aux != null) {
            if (aux.getDato() == x) {
                desenlazar(aux);
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    /** Elimina el nodo en la posicion pos (0..tamanio-1) y devuelve su dato. */
    public int eliminarEnPosicion(int pos) {
        if (pos < 0 || pos >= tamanio) {
            throw new IllegalStateException("Valor fuera de rango");
        }
        nodoD nodo = obtenerNodo(pos);
        int dato = nodo.getDato();
        desenlazar(nodo);
        return dato;
    }
}