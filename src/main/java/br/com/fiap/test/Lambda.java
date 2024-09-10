package br.com.fiap.test;

import java.util.ArrayList;

public class Lambda {

	public static void main(String[] args) {

		ArrayList<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);
		numeros.add(3);
		numeros.add(5);
		numeros.add(7);
		numeros.forEach((n) -> {
			System.out.println(n);
			
			
			
			
			
		});

		numeros.forEach(System.out::println);
		
		//expressão lambda
		
		for(Integer numero: numeros) {
			System.out.println(numero);
		}
		
		
	}

}
