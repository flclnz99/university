package prim;

import java.util.Comparator;
import graph.*;

public class PrimNodeComparatorDouble implements Comparator<PrimNode<String, Double>>{
	@Override
	public int compare(PrimNode<String, Double> a, PrimNode<String, Double> b) {
		double x = a.getCost();
		double y = b.getCost();
		return x < y ? -1 : x == y ? 0 : 1;
	}
}
