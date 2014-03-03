package store_13;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String abc = "asdf_uio";
		System.out.println(columnsNameToClassName(abc));;
	}
	private static String columnsNameToClassName(String columnsName){
		String[] temp = columnsName.split("_");
		for (int i=1; i<temp.length; i++) {
			temp[i] = temp[i].substring(0,1).toUpperCase()+temp[i].substring(1);
		}
		StringBuffer result = new StringBuffer();
		for (int j = 0; j < temp.length; j++) {
			   result.append( temp[j] );
			}
		return result.toString();
	}

}
