package View;

import java.util.ArrayList;

public class ViewMethod {
	// cmd에서 화면을 초기화 하기 위한 view의 함수
	// 간단하게 80번 엔터해서 초기화
	public void clearScreen() {
		for (int i = 0; i < 80; i++) {
			System.out.println("");
		}
	}
	
	// String의 2차원 배열을 출력하는 view의 함수
	public void printarray(String[][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
	}
	
	public void printSlot(String[][] a, String[][] b, String[][] c) {
		int left = 10;
		int up = 5;
		int down = 5;
		int gap = 3;
		
		int length = a.length;
		int width = a[0].length;
		
		// up gap // 위쪽 여백
		for(int i = 0; i < up; i++) {
			System.out.println();
		}
		
		for(int i = 0; i < length; i++) {
			// 왼쪽 여백
			for(int j = 0; j < left; j++) {
				System.out.print(" ");
			}
			
			// 첫 번째 카드
			for(int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j]);
			}
			
			// 카드 사이의 간격
			for(int j = 0; j < gap; j++) {
				System.out.print(" ");
			}
			
			// 두 번째 카드
			for(int j = 0; j < b[0].length; j++) {
				System.out.print(b[i][j]);
			}
			
			// 카드사이의 간격
			for(int j = 0; j < gap; j++) {
				System.out.print(" ");
			}
			
			// 세 번째 카드
			for(int j = 0; j < a[0].length; j++) {
				System.out.print(c[i][j]);
			}
			
			// 줄바꿈
			System.out.println();
		}
		
		// 아래 여백
		for(int i = 0; i < down; i++) {
			System.out.println();
		}
	}
	
	public void printBlackJack(ArrayList<String[][]> list) {
		int left = 10;
		int up = 3;
		int down = 3;
		int gap = 3;
		
		// 모든 카드의 규격이 동일하므로 		
		int length = list.get(0).length;
		int width = list.get(0)[0].length;
		
		// up gap // 위쪽 여백
		for(int i = 0; i < up; i++) {
			System.out.println();
		}
		
		for(int i = 0; i < length; i++) {
			// 왼쪽 여백
			for(int j = 0; j < left; j++) {
				System.out.print(" ");
			}
			
			for(int k = 0; k < list.size(); k++) {
				// k번째 카드를 출력

				for(int j = 0; j < list.get(k)[0].length; j++) {
					System.out.print(list.get(k)[i][j]);
				}
				
				// 카드 사이의 간격
				for(int j = 0; j < gap; j++) {
					System.out.print(" ");
				}
			}

			// 줄바꿈
			System.out.println();
		}
		
		// 아래 여백
		for(int i = 0; i < down; i++) {
			System.out.println();
		}
	}
}

