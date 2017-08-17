package br.com.opencare.dao;

public class PAGINING {
	public static int pageSize = 10;

	public static int pages(long count) {
		return (int) Math.ceil(1.0 * count / pageSize);
	}
}
