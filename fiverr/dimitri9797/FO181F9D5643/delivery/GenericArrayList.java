/**
 * 
 */
package treeAssign;

import java.util.Iterator;

/**
* <h1>GenericArrayList</h1>
* The GenericArrayList program implements a dynamic array of generic 
* data type. It offers various sort and search options.
* <p> 
*
* @author sarwars1
* @version 1.0
* @since   2021-04-14 
*/
public class GenericArrayList<T> implements Iterable<T>{

	private Object array[];
	private int length;
	private int index;
	private int bufferSize;
	private final int blockSize = 20;
	
	public GenericArrayList() {
		this.bufferSize = this.blockSize;
		this.array = new Object[this.bufferSize];
		this.index = -1;
		this.length = 0;
	}
	
	public int size() {
		return this.length;
	}
	
   /**
   * This is the add method that grows by blockSize 
   * @param t which is a generic object of T type 
   * @return true or false
   * @exception OutOfMemoryError On memory allocation error.
   * @see OutOfMemoryError
   */
	public boolean add(T t) {
		boolean result = false;
		
		try {
				if((this.index + 1) < this.bufferSize) {
					result = true;
				}else {
					Object []newArray = new Object[this.bufferSize + this.blockSize];
					for(int i = 0; i < this.length; i++) {
					newArray[i] = this.array[i];
				}
				
				Object [] tempArray = this.array;
				this.array = newArray;
				this.bufferSize+= this.blockSize;
				for(int i = 0; i < tempArray.length; i++) {
					tempArray[i] = null;
				}
				result = true;
			}
		} catch(OutOfMemoryError error) {
			System.out.println("Memory allocation error: " + error);
			result = false;
		}
		
		if(result) {
			this.array[++index] = (Object)t;
			this.length = this.index + 1;
		}
		
		return result;
	}
	
	
   /**
   * This is the add method 
   * @param ndx to indicate the index at which the new object needs to be inserted.
   * @param t the object of generic type T that needs to be inserted in the array
   * @return true or false.
   */
	public boolean add(int ndx, T t) {
		boolean result = false;
		if((ndx>=0) && (ndx < this.length)) {
			array[ndx] = (Object)t;
			result = true;
		}
		
		return result;
	}
	
   /**
   * This is the get method 
   * @param ndx to indicate the index from which the new object needs to be retrieved.
   * @return T type object
   */
	@SuppressWarnings("unchecked")
	public T get(int ndx) {
		T result = null;
		
		if((ndx >= 0) && (ndx < this.length)) {
			result = (T)array[ndx];
		}
		
		return result;
	}
	
   /**
   * This is the delete method 
   * @param ndx to indicate the index from which the object needs to be deleted.
   * @return true or false.
   */
	public boolean delete(int ndx) {
		boolean result = false;
	
		if((ndx>=0) && (ndx < this.length)) {
			for(int i = ndx; i < this.length; i++) {
				this.array[i] = this.array[i+1];
			}
			this.length = --this.index + 1;
			result = true;
		}
		
		
		return result;
	}
	
   /**
   * This is the sort method that uses Selection Sort Algorithm
   * @param ctype
   * @return Nothing.
   */
	@SuppressWarnings("unchecked")
	public void sort(Class<T> ctype) {
		
		// Find out the data type class to adopt a comparison algorithm
		int dtype;
		Class<T> keyType = ctype;
		if (keyType == Integer.class) {
			 dtype = 1;
		}else if(keyType == Double.class) {
			dtype = 2; 
		}else if(keyType == float.class) {
			dtype = 3;
		}else if(keyType == String.class) {
			dtype = 4;
		}else if(keyType == Byte.class) {
			dtype = 5;
		}else {
			return;
		}
	
		// Selection Sort algorithm
		for(int i = 0; i < this.length; i++) {
			for(int j = i + 1; j < this.length; j++) {
				switch(dtype) {
				case 1:
					if((Integer)this.array[i] > (Integer)this.array[j]) {
						Integer temp = (Integer)this.array[j];
						this.array[j] = this.array[i];
						this.array[i] = temp;
					}
					break;
				case 2:
					if((Double)this.array[i] > (Double)this.array[j]) {
						Double temp = (Double)this.array[j];
						this.array[j] = this.array[i];
						this.array[i] = temp;
					}
					break;
				case 3:
					if((Float)this.array[i] > (Float)this.array[j]) {
						Float temp = (Float)this.array[j];
						this.array[j] = this.array[i];
						this.array[i] = temp;
					}
					break;
				case 4:
					if(((String)(this.array[i])).compareTo(((String)this.array[j])) > 0) {
						String temp = (String)this.array[j];
						this.array[j] = this.array[i];
						this.array[i] = temp;
					}
					break;
				case 5:
					if((Byte)this.array[i] > (Byte)this.array[j]) {
						Byte temp = (Byte)this.array[j];
						this.array[j] = this.array[i];
						this.array[i] = temp;
					}
					break;
					default:
				}
			}
		}
	}

	/*
	 * Search method
	 * 
	 */
	public int search(Class<T> ctype, T t) {
		int result = -1;
		boolean found = false;
		int dtype;
		
		if(this.length == 0)
			return result;
		
		Class<T> keyType = ctype;
		
		if (keyType == Integer.class) {
			 dtype = 1;
		}else if(keyType == Double.class) {
			dtype = 2; 
		}else if(keyType == float.class) {
			dtype = 3;
		}else if(keyType == String.class) {
			dtype = 4;
		}else if(keyType == Byte.class) {
			dtype = 5;
		}else {
			return -1;
		}
		
		result = this.recursiveSearch(dtype, t, 0, this.length);
		return result;
	}
	
	private int recursiveSearch(int dtype, T t, int start, int end) {	
		int result = -1;
		
		return result;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void forEach(Function func) {
		for(int i = 0; i < this.length; i++) {
			func.forEach((Node<T>)this.get(i));
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new CustomIterator<T>(this);
	}
	
	class CustomIterator<T> implements Iterator<T>{

		private GenericArrayList<T> collection;
		int cursor;
		
		
		public CustomIterator(GenericArrayList<T> collection) {
			super();
			this.collection = collection;
			this.cursor = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			boolean result = false;
			if(this.collection.get(this.cursor) != null) {
				result = true;
			}
			return result;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			T result = this.collection.get(this.cursor++);
			return result;
		}
		
	}
}
