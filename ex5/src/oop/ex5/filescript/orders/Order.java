package oop.ex5.filescript.orders;

import java.io.File;
import java.util.Comparator;

/**
 * An abstract order object (files comparator).
 * @author ransha
 */
public interface Order extends Comparator<File> {
	public final static int EQUALS = 0;
	
}
