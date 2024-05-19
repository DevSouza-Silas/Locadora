package com.locadora.util;

public class ClasseUtil {
	
	/**
	 * O método empty com esses trás parâmetros, (int length, int qtdCaracter, String msg), serve para verificar se o tamanho da String (length) passado como prâmetro é menor que a qtd de Caracter passado como parâmetro, 
	 * caso sim, retorna a mensagem passada no parâmetro."
	 *
	 @param length Tamanho da String
	 @param qtdCaracter Quantidade de Caracteres
	 @param msg Retorno da Mensagem
	 */
	public static boolean empty(int length, int qtdCaracter, String msg) {
		
		if (length < qtdCaracter) {
			
			Message.erro(msg);
			return true;
		}
		
		return false;
	}
	
	/**
	 * O método empty com esses dois parâmetros, (String campo, String msg), serve para verificar se o campo passado como parâmetros está vazio ou nullo, caso sim, retorna a mensagem passada no parâmetros."
	 *
	 @param Campo para validar
	 @param msg Retorno da Mensagem
	 */
	public static boolean empty(String campo, String msg) {
	
		if (campo == "" || campo == null) {
			
			Message.erro(msg); 
			return true;
		}
		
		return false;
	}
	
	/**
	 * O método empty com esses dois parâmetros, (Long campo, String msg), serve para verificar se o campo passado como parâmetro está vazio ou nullo, caso sim, retorna a mensagem passada no parâmetro."
	 *
	 @param Campo para validar
	 @param msg Retorno da Mensagem
	 */
	public static boolean empty(Long campo, String msg) {
		
		if (campo == 0 || campo == null) {
			
			Message.erro(msg); 
			return true;
		}
		
		return false;
	}
}