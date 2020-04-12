package comjava.webbanhang.other;

public class ProcessUrlImage {
	
	
    public  static String processUrlImae(String urlImage, String name) {
    	String nameReplace = name.replace("/", "-").replace(" ","-").replace("\"", "-");
    	String catChuoi = urlImage.substring(urlImage.lastIndexOf("."));
    	String str = "anh".concat(nameReplace).concat(catChuoi);
    	return str;
    }
}
