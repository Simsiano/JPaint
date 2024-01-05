package helpers.sizedobjects;

import java.util.ArrayDeque;

public class SizedBuffer<E> extends ArrayDeque<E>{
	private static final long serialVersionUID = 1L;

	private int maxSize;
	private int halfSize;

	public SizedBuffer(int size) {
		super();
		this.maxSize = size;
		this.halfSize = size/2;
	}

	@Override
	public void push(E object) {
		while (this.size() >= maxSize) {
			copyAllLayers();
			for (int i = 0; i <= halfSize; i++) {
				this.removeLast();
			}
			
		}
		super.push(object);
	}
	
	private void copyAllLayers() {
		
	}

}
