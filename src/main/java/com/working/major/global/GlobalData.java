package com.working.major.global;

import com.working.major.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
	public static List<Product> cart;
	static {
		cart = new ArrayList<Product>();
	}
}
