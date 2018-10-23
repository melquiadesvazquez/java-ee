package util;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entidades.Libro;

public class Utilidades {
	
	public static String transformarLibrosJson(List<Libro> libros) {
		JSONArray array = new JSONArray();
		
		for(Libro l:libros) {
			JSONObject ob = new JSONObject();
			ob.put("titulo", l.getTitulo());
			ob.put("precio", l.getPrecio());
			ob.put("autor", l.getAutor());
			ob.put("isbn", l.getIsbn());
			array.add(ob);
		}
		
		return array.toJSONString();
	}
	
}
