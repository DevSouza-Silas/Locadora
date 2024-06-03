package com.locadora.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import javax.faces.model.SelectItem;

public class ClasseUtil {
	
	/**
	 * O método initCombo é genérico e serve para carregar uma lista de SelectItem. 
	 * São passados três parâmetros que são: Uma Collection e duas String. 
	 *
	 @param Collection Tipo da Collection.
	 @param String O nome do valor. 
	 @param String O nome da label.
	 */
	public static List<SelectItem> initCombo(Collection<?> collection, String value, String label){
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		
		for (Object object : collection) {
			
			try {
				list.add(new SelectItem(BeanUtils.getProperty(object, value), BeanUtils.getProperty(object, label)));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * O método empty com esses três parâmetros, (int length, int qtdCaracter, String msg), serve para verificar se o tamanho da String (length) passado como prâmetro é menor que a qtd de Caracter passado como parâmetro, 
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
	
	public static boolean emptyDate(Date campo, String msg) {
		
		if (campo == null) {
			
			Message.erro(msg); 
			return true;
		}
		
		return false;
	}
	
	public static boolean emptyCalendar(Calendar campo, String msg) {
		
		if (campo == null) {
			
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