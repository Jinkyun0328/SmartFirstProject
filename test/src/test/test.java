package test;

public class test {

	public static void main(String[] args) {
		String[][] sarray = new String[20][20];

		//System.out.println(Math.cos(Math.toRadians(0)));
		//System.out.println(Math.cos(Math.toRadians(180)));
		
		for(int i = 0; i < sarray.length; i++) {
			for(int j = 0; j < sarray[0].length; j++) {
				// 좌표는 (j-10, i-10)
				// 라디안은?
				// 거리가 5이하면 1로 바꾸기
				
				if ((j-10)*(j-10) + (i-10)*(i-10) <= 9*9) {
					sarray[i][j] = "■";
				}else {
					sarray[i][j] = "□";
				}
				
			}
		}
		
		for(int i = 0; i < sarray.length; i++) {
			for(int j = 0; j < sarray[0].length; j++) {
				System.out.print(sarray[i][j]);
			}
			System.out.println();
		}
	}

}
