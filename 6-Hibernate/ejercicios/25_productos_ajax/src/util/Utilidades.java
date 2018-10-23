package util;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entidades.Producto;

public class Utilidades {
	
	public static String transformarProductosJson(List<Producto> productos) {
		JSONArray array = new JSONArray();
		
		for(Producto p:productos) {
			JSONObject ob = new JSONObject();
			ob.put("idProducto", p.getIdProducto());
			ob.put("producto", p.getNombre());
			ob.put("precio", p.getPrecio());
			array.add(ob);
		}
		
		return array.toJSONString();
	}
	
}
