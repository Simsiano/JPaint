package helpers.sizedobjects;

import java.util.ArrayList;

public class SizedList<E> extends ArrayList<E> {
	private static final long serialVersionUID = 1L;
	
	private int maxSize;

    public SizedList(int size) {
        super();
        this.maxSize = size;
    }

    @Override
    public boolean add(E object) {
        while (this.size() >= maxSize) {
            this.remove(0);
        }
        super.add(object);
        return true;
    }
    
    public E getFirst() {
    	return this.get(0);
    }
    
    public E getLast() {
    	return this.get(this.size()-1);
    }
    
    /**
     * Rimuove e ritorna l'ultimo oggetto della lista.
     */
    public E spit() {
    	E object = this.get(this.size()-1);
    	this.remove(this.size()-1);
    	return object;
    	
    }

}
