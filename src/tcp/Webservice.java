package tcp;

public class Webservice {
	
	public String concat(String palavra1, String palavra2) {
		return palavra1.concat(palavra2);
	}

	public String comparar(String palavra1, String palavra2) {
		if (palavra1.equals(palavra2)) return "True";
		return "False";
	}
	
	public String substring(String palavra1, String inicio, String fim) {
		return palavra1.substring(Integer.parseInt(inicio), Integer.parseInt(fim));
	}
	
	public String contem(String palavra1, String palavra2) {
		if (palavra1.contains(palavra2)) return "True";
		return "False";
	}
	
	public String substituir(String palavra, String char1, String char2) {
		return palavra.replace(char1, char2);
	}
}
