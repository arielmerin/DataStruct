import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p> Clase concreta para modelar la estructura de datos Pila</p>
 * <p>Esta clase implementa una Pila genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Kevin Ariel Merino <arielmerino@ciencias.unam.mx>
 * @version 1.0
 * @param <T> Tipo que tienen los objetos que guarda esta pila.
 */
public class Pila<T> implements Coleccionable<T> {

    private class Nodo {

        /**
         * El elemento del nodo.
         */
        public T elemento;
        /**
         * El siguiente nodo.
         */
        public Nodo siguiente;

        /**
         * Construye un nodo con un elemento.
         *
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    private class IteradorPila implements Iterator<T> {

        public Nodo siguiente;

        public IteradorPila() {
            siguiente = tope;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
            return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override
        public T next() {
            T aux = siguiente.elemento;
            siguiente = siguiente.siguiente;
            return aux;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("No implementado");
        }

    }
    private Nodo tope;
    private int elementos;

    /**
     * Constructor por omisión de la clase;
     */
    public Pila() {
    }

    /**
     * Constructor que recibe un arreglo de elementos de tipo <code>T</code>.
     * Crea una pila donde el primer elemento del arreglo es el que queda al
     * fondo de la pila, el último elemento del arreglo queda en el tope
     * de la pila.
     * @param elementos
     */
    public Pila(T[] elementos) {
        for(T elem: elementos){
            this.agrega(elem);
        }
    }

    /**
     * Constructor que recibe una colección de tipo {@link Coleccionable}
     * de elementos de tipo <code>T</code>.
     * Crea una pila donde el primer elemento de la colección es el que queda al
     * fondo de la pila, el último elemento de la colección queda en el tope
     * de la pila.
     * @param elementos La colección de elementos a agregar.
     */
    public Pila(Coleccionable<T> elementos) {
        for (T elem: elementos){
            this.agrega(elem);
        }
    }

    /**
     * Constructor de la clase, que recibe una pila y regresa una copia
     * exacta de ésta.
     *
     * @param pila La pila que se va a copiar.
     */
    public Pila(Pila<T> pila){
        Pila<T> tmp = new Pila<>((Coleccionable<T>) pila);
        for (T elem: tmp){
            this.agrega(elem);
        }
    }

    /**
     * Agrega un elemento al tope de la pila.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     * <code>null</code>.
     */
    public void push(T elemento) throws IllegalArgumentException {
        if (elemento == null){
            throw  new IllegalArgumentException("El elemento enviado es nulo");
        }
        Nodo nuevo = new Nodo(elemento);
        if (esVacio()){
            tope = nuevo;
        }else{
            nuevo.siguiente = tope;
            tope = nuevo;
        }
        elementos++;
    }

    /**
     * Elimina el elemento del tope de la pila y lo regresa.
     * @throws NoSuchElementException si la pila es vacía.
     * @return el elemento en el tope de la pila.
     */
    public T pop() throws NoSuchElementException{
        if (esVacio()){
            throw new NoSuchElementException("La pila esta vacia");
        }
        T aux = tope.elemento;
        tope = tope.siguiente;
        elementos--;
        return aux;
    }

    /**
     * Nos permite ver el elemento en el tope de la pila
     *
     * @return el elemento en un extremo de la estructura.
     */
    public T peek() {
        return tope.elemento;
    }

    /**
     * Agrega un elemento a la pila.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     * <code>null</code>.
     */
    @Override
    public void agrega(T elemento) throws IllegalArgumentException{
        push(elemento);
    }

    /**
     * Nos dice si un elemento está contenido en la pila.
     *
     * @param elemento el elemento que queremos verificar si está contenido en
     * la pila.
     * @return <code>true</code> si el elemento está contenido en la pila,
     * <code>false</code> en otro caso.
     */
    @Override
    public boolean contiene(T elemento) {
        Iterator it = iterator();
        while (it.hasNext()){
            if (it.next().equals(elemento)){
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un elemento de la pila.
     * @throws NoSuchElementException si la pila es vacía.
     * @param elemento el elemento a eliminar.
     */
    @Override
    public void elimina(T elemento) throws NoSuchElementException{
        pop();
    }

    /**
     * Nos dice si la pila está vacía.
     *
     * @return <tt>true</tt> si la pila no tiene elementos,
     * <tt>false</tt> en otro caso.
     */
    @Override
    public boolean esVacio() {
        return elementos == 0;
    }

    /**
     * Regresa el número de elementos en la pila.
     *
     * @return el número de elementos en la pila.
     */
    @Override
    public int getTamanio() {
        return elementos;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorPila();
    }

    @Override
    public String toString() {
        String s = "[";
        Nodo n = this.tope;
        while (n != null) {
            if (n.siguiente == null) {
                s += n.elemento;
            } else {
                s += n.elemento + ",";
            }
            n = n.siguiente;
        }
        s += "]";
        return s;
    }
}
