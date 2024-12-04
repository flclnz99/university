package prim;

import java.util.Comparator;
import graph.*;

public class EdgeComparatorDouble implements Comparator<Edge<String, Double>> {
	@Override
	public int compare(Edge<String, Double> a, Edge<String, Double> b) {
		double x = a.getLabel();
		double y = b.getLabel();
		return x < y ? -1 : x == y ? 0 : 1;
	}
}
