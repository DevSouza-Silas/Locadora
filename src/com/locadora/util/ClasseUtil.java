package com.locadora.util;

public class ClasseUtil {
	
	/**
	 * O m�todo empty com esses tr�s par�metros, (int length, int qtdCaracter, String msg), serve para verificar se o tamanho da String (length) passado como pr�metro � menor que a qtd de Caracter passado como par�metro, 
	 * caso sim, retorna a mensagem passada no par�metro."
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
	 * O m�todo empty com esses dois par�metros, (String campo, String msg), serve para verificar se o campo passado como par�metros est� vazio ou nullo, caso sim, retorna a mensagem passada no par�metros."
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
	 * O m�todo empty com esses dois par�metros, (Long campo, String msg), serve para verificar se o campo passado como par�metro est� vazio ou nullo, caso sim, retorna a mensagem passada no par�metro."
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