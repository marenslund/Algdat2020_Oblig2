package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
    }

    public DobbeltLenketListe(T[] a) {

        if (a.length == 0) {
            return;
        }

        hode = hale = new Node<>(null);
        for (T t : a) {

            if (t != null) {
                hale = hale.neste = new Node<>(t, hale, null);
                antall++;
            }

        }
        if (antall == 0) {
            hode = hale = null;
        } else {
            hode = hode.neste;
            hode.forrige = null;
        }

        Objects.requireNonNull(a, "Tabellen a er null!");
    }

    private void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0) {
            throw new IndexOutOfBoundsException("fra er negativ!");
        }

        if (til > antall) {
            throw new IndexOutOfBoundsException("til er større enn antall!");
        }

        if (fra > til) {
            throw new IllegalArgumentException("fra er større enn til!");
        }
    }

    public Liste<T> subliste(int fra, int til){
        fratilKontroll(antall, fra, til);

        DobbeltLenketListe<T> liste = new DobbeltLenketListe<>();
        int listeLengde = til - fra;

        if (listeLengde < 1) {
            return liste;
        }

        Node<T> node = finnNode(fra);

        while (listeLengde > 0) {
            liste.leggInn(node.verdi);
            node = node.neste;
            listeLengde--;
        }

        return liste;

    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return (antall== 0 && hode == null && hale == null);
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Null-verdier er ikke tillat!");

        if (tom()) {
            hode = hale = new Node<>(verdi);
        }
        else {
            hale = hale.neste = new Node<>(verdi, hale, null);
        }

        antall++;
        endringer++;
        return true;

    }

    @Override
    public void leggInn(int indeks, T verdi) {

        indeksKontroll(indeks, true);

        Objects.requireNonNull(verdi, "Verdi er null!");

        if (tom()) {
            hode = hale = new Node<>(verdi);
        }
        else if (indeks == 0) {
            hode = new Node<>(verdi, null, hode);
            hode.neste.forrige = hode;
        }
        else if (indeks == antall) {
            hale = hale.neste = new Node<>(verdi, hale, null);
        } else {
            Node<T> node = hode;

            for (int i = 0; i < indeks; i++) {
                node = node.neste;
            }

            node = new Node<>(verdi, node.forrige, node);
            node.neste.forrige = node.forrige.neste = node;
        }

        endringer++;
        antall++;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    private Node<T> finnNode(int indeks) {

        indeksKontroll(indeks, false);

        Node<T> finnNode;
        if (indeks < antall/2) {
            finnNode = hode;
            for (int i = 0; i < indeks; i++) {
                finnNode = finnNode.neste;
            }
        }

        else {
            finnNode = hale;
            for (int i = antall-1; i > indeks; i--) {
                finnNode = finnNode.forrige;
            }
        }
        return finnNode;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) {
            return -1;
        }

        Node<T> node = hode;

        for (int i = 0; i < antall; i++) {
            if (node.verdi.equals(verdi)) {
                return i;
            }
            node = node.neste;
        }

        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false);
        Objects.requireNonNull(nyverdi, "Ny verdi kan ikke være null!");

        Node<T> nyNode = finnNode(indeks);
        T gammelVerdi = nyNode.verdi;

        nyNode.verdi = nyverdi;

        endringer++;
        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        if (verdi == null) {
            return false;
        }

        Node<T> node = hode;

        if (hode.verdi.equals(verdi)) {
            if (node.neste != null) {
                hode = node.neste;
                hode.forrige = null;
            } else {
                hode = null;
                hale = null;
            }
            antall--;
            endringer++;
            return true;
        }

        else if (hale.verdi.equals(verdi)) {
            node = hale;
            hale = node.forrige;
            hale.neste = null;
            antall--;
            endringer++;
            return true;
        }

        else {
            node = hode.neste;
            while (node != null) {
                if (verdi.equals(node.verdi)) {
                    node.forrige.neste = node.neste;
                    node.neste.forrige = node.forrige;
                    antall--;
                    endringer++;
                    return true;
                }
                node = node.neste;
            }
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);

        T returnVerdi;
        Node<T> node = hode;

        if (indeks == 0) {
            returnVerdi = node.verdi;

            if (antall == 1) {
                hale = null;
                hode = null;
            }
            else {
                hode = node.neste;
                hode.forrige = null;
            }

        }

        else if (indeks == antall-1) {
            node = hale;
            returnVerdi = hale.verdi;

            hale = node.forrige;
            hale.neste = null;
        }

        else {
            for (int i = 0; i < indeks; i++) {
                node = node.neste;
            }
            returnVerdi = node.verdi;

            node.forrige.neste = node.neste;
            node.neste.forrige = node.forrige;
        }

        antall--;
        endringer++;

        return returnVerdi;
    }

    @Override
    public void nullstill() {
        this.hode = null;
        this.hale = null;
        antall = 0;
        endringer++;
    }

    @Override
    public String toString() {
        Node<T> node = hode;
        StringBuilder str = new StringBuilder();
        str.append("[");

        if (!tom()) {
            str.append(node.verdi);

            node = node.neste;

            while (node != null) {
                str.append(", ");
                str.append(node.verdi);
                node = node.neste;
            }
        }

        str.append("]");
        return str.toString();
    }

    public String omvendtString() {
        Node<T> node = hale;
        StringBuilder str = new StringBuilder();
        str.append("[");

        if (!tom()) {
            str.append(node.verdi);

            node = node.forrige;

            while (node != null) {
                str.append(", ");
                str.append(node.verdi);
                node = node.forrige;
            }
        }

        str.append("]");
        return str.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Iterator endringer er ikke lik endringer!");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("hasNext() er false!");
            }

            fjernOK = true;
            T temp = denne.verdi;
            denne = denne.neste;

            return temp;

        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


