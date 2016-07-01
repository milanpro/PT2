package assignment5.sequences;

import java.util.NoSuchElementException;

/**
 * A sequence which manages it's elements as a singly linked list.
 *
 * @param <T> The type of elements stored in this sequence.
 */
public class SinglyLinkedSequence<T> implements Sequence<T>, List<T>, Stack<T>, Queue<T> {

    private Node head, tail; //Kopf = erstes Element; Schwanz = letztes ELement
    private int size; //Größe des Feldes

    public SinglyLinkedSequence() { //Erstellen einer leeren einfach-verketteten Liste
        head = null;
        tail = null;
        size = 0;

    }

    @Override //fügt Element in die einfach verketteten Liste an der Stelle des Index ein
    public void insert(int index, T element) {

        if(element==null) return; //kein Element gegeben

        if(index<0||this.size<index){ //Index nicht möglich
            throw new IndexOutOfBoundsException();
        }

        if(index == 0){ //Am Anfang etwas einfügen
            push_top(element);
        }

        else if(index == size){
            Node n = new Node(element, null);
            this.tail.setNext(n);
            this.tail = n;
            this.size ++;
        }

        else{
            Node temp = new Node(element,null); //neuer Knoten ohne Zuweisung des Nachfolgers
            Node curr = this.head; //aktueller Knoten

            for(int i = 1; i< index; i++){
                curr=curr.getNext(); //curr nach Durchlauf der Schleife ist Knoten an Stelle Index-1
            }
            temp.setNext(curr.getNext()); //Nachfolger des neuen Knotens ist der alte Nachfolger von curr
            curr.setNext(temp); //curr setzt seinen Nachfolger auf den neuen Knoten
            this.size ++; //Feld vergrößern
        }

    }

    @Override //setzt das Element an der Stelle des Index
    public T set(int index, T element) {

        if(0>index||index>=this.size){ //Index nicht möglich
            throw new NoSuchElementException();
        }
        Node curr = this.head; //aktueller Knoten

        for(int i = 0; i < index; i++){
            curr = curr.getNext(); //curr nach Durchlauf der Schleife Knoten an Stelle Index
        }
        T temp = curr.getElement(); //temp speichert den alten Wert des Knotens
        curr.setElement(element); //neuen Wert setzen

        return temp; //alten Wert zurückgeben
    }

    @Override //Rückgabe Element an Indexstelle
    public T get(int index) {

        if(0>index||index>=this.size){ //Index nicht möglich
            throw new NoSuchElementException();
        }
        Node curr = this.head; //aktueller Knoten

        for(int i = 0; i < index; i++){
            curr = curr.getNext(); //curr nach Durchlauf der Schleife Knoten an Stelle Index
        }
        return curr.getElement(); //Wert an Stelle Index zurückgeben
    }

    @Override //Löscht den Knoten an der Indexstelle und gibt den Wert des gelöschten Knotens aus
    public T remove(int index) {

        if(index<0||size<=index){ //Index nicht möglich
            throw new NoSuchElementException();
        }

        T r; //r = removedElement

        if(index == 0) { //head soll gelöscht werden

            r = this.head.getElement();

            if(this.size==1){ //head = tail
                this.head = null;
                this.tail = null;
            }

            else{this.head = this.head.getNext();}
        }
        else{
            Node curr = this.head;
            for(int i = 1; i <index; i++){
                curr=curr.getNext(); //curr nach Durchlauf der Schleife Knoten an Stelle Index-1
            }
            r = curr.getNext().getElement();

            if(curr.getNext()== this.tail){ //tail soll gelöscht werden
                this.tail = curr;
            }
            curr.setNext(curr.getNext().getNext()); //Knoten "überspringen"
        }
        this.size --;
        return r;
    }

    @Override //Element hinten anfügen
    public void push_back(T element) {
        insert(this.size, element);
    }

    @Override
    public T pop_front() {
        return remove(0);
    }

    @Override
    public void push_top(T element) { //Element vorne anfügen

        if(head == null){ //leere Liste
            this.head = new Node(element, null);
            this.tail = this.head;
        }
        else {
            Node n = new Node(element, this.head);
            this.head = n;
        }
        this.size ++;
    }

    @Override
    public T pop_top() {
        return pop_front();
    }

    @Override
    public int size() {
        return this.size;
    }

    private class Node{

        private T e; /*e = element*/
        private Node next;

        public Node(){
            this(null, null);
        }

        public Node(T element, Node next){
            this.e = element;
            this.next = next;
        }
        public T getElement(){
            return e;
        }

        public Node getNext(){
            return next;
        }

        public void setElement(T element){
            this.e = element;
        }

        public void setNext(Node n){
            this.next = n;
        }
    }
}
