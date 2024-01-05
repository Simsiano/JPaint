package helpers.sizedobjects;

import java.util.ArrayDeque;

public class SizedDeque<T> extends ArrayDeque<T> {
	private static final long serialVersionUID = 1L;

	private int maxSize;

	public SizedDeque(int size) {
		super();
		this.maxSize = size;
	}

	@Override
	public void push(T object) {
		while (this.size() >= maxSize) {
			this.removeLast();
		}
		super.push(object);
	}
}
