import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int[][] array = new int[20][20];
		
		Math.cos(Math.toRadians(0));
		int length = array.length;
		int width = array[0].length;
		int mid = length/2;
		
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if((mid-i)*(mid-i)+(mid-j)*(mid-j) <= 8*8) {
					array[i][j] = 1;
				}
			}
		}
		
		String[][] sarray = new String[20][20];
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if(array[i][j] == 1) {
					sarray[i][j] = "■";
				}else if(array[i][j] == 0) {
					sarray[i][j] = " ";
				}
			}
		}
		
		
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print(sarray[i][j]);
			}
			System.out.println();
		}
		
		int n = sc.nextInt();
	}

}
