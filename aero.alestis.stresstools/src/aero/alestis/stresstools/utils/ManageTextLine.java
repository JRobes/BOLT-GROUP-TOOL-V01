package aero.alestis.stresstools.utils;

public final class ManageTextLine {
	public static String header = "$1      2       3       4       5       6       7       8       9       10     #";
	public ManageTextLine() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param doubleToConvert
	 */
	public static String doubleToNastranField8(double doubleToConvert) {
		String str1 = Double.toString(doubleToConvert);
		//System.out.println("str1\t" + str1);
		if(str1.contains("E")) {
			String[] splitted;
			//System.out.println("EEEE");
			splitted = str1.split("E");
			if(splitted[1].startsWith("-")) {
				int aa = splitted[1].length();
				return splitted[0].substring(0,8-aa).concat(splitted[1]);
			}
			else {
				if(splitted[1].startsWith("+")){
					int bb = splitted[1].length();
					return splitted[0].substring(0,8-bb).concat(splitted[1]);
				}
				else {
					int cc = splitted[1].length();
					return splitted[0].substring(0,8-cc-1).concat("+").concat(splitted[1]);
				}
			}
		}
		else {
			if(str1.length() < 9) {
				return str1.substring(0,str1.length());
			}
			else {
				return str1.substring(0, 8);
			}
		}
		
	}
	public static String fillWithUpTo80Blancks(String string){
		
		if (string.length()<80){
			//System.out.println("eee"+string.length());
			
			int pepe = 80 - string.length();
			//System.out.println("peep" +pepe);
			for(int i=0;i < pepe;i++){
				string =  string.concat(" ");
				//System.out.println(i);
				//System.out.println(string);
				
			}
		}
		//System.out.println("wwww");
		return string;
		
	}
	
	public String colocarCadenaPosicion(String linea, String cadena, int pos){
		
		String pre_aux=null;
		String pos_aux =null;
		if (pos < 0 || pos>10){
			System.out.println("hay te has pasado");
			linea ="";
		}
		else{
			
			pre_aux = linea.substring(0, (pos-1)*8);
			pos_aux = linea.substring((pos)*8);
			linea = pre_aux.concat(cadena).concat(pos_aux);
		}
				
		return linea;
		
	}

	public String fromStringTo8String(String cadena){
		
		if (cadena.length() < 8){
			int longitud = cadena.length();
			for (int i = 0; i < (8 - longitud);i++){
				cadena = cadena.concat(" ");
			}
		}	
		else{
			cadena =cadena.substring(0, 8);	
		}
		
		return cadena;
	}
	
	public String fromFloatTo8String(Float flotante){
		//String cadena;
		String cadena = flotante.toString();
		if (cadena.length() < 8){
			int longitud = cadena.length();
			for (int i = 0; i < (8 - longitud);i++){
				cadena = cadena.concat(" ");
			}
		}	
		else{
			cadena =flotante.toString().substring(0, 8);	
		}
		
		
		return cadena;
	}	
	public String lineaEnBlanco(){
		String base = "";
		for(int i=0;i<80;i++){
			base = base.concat(" ");
			
		}
		
		
		return base;
		
	}
}
