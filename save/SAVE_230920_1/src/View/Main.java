package View;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class Main{
	// main 함수를 가지고 있는 클래스
	// GameMenu 화면 출력
	// [1]회원가입 [2]로그인 [3]회원탈퇴 [4]게임종료
	public Main() {
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		
		// DATA
		String id = null;
		String pw = null;
		String nickname = null;
		int money = 0;
		
		ArrayList<HoguDTO> list = new ArrayList<HoguDTO>();
		casinoController conm = new casinoController();
		casinoMusic cm;
		
		while(true) {
			int input = 0;
			System.out.print("[1]회원가입 [2]로그인 [3]회원탈퇴 [4]게임종료 >> ");
			
			// scanner에 정수가 아닌 다른 자료형을 입력할 경우 예외처리를 하지 않으면 컴파일러가 프로그램을 종료한다.
			// 프로그램을 종료하지 않고 다시 정수를 입력받을 수 있게 하는 코드
			// sc.nextLine()은 int형을 입력받고 메모리를 비우기 위해 추가했다.
			while(!sc.hasNextInt() || (input = sc.nextInt()) < 0){
				// ViewMethod.clearScreen();
				System.out.println("정수를 입력해주세요.");
				System.out.print("[1]회원가입 [2]로그인 [3]회원탈퇴 [4]게임종료 >> ");
				sc = new Scanner(System.in);
			}
			sc.nextLine();
			
			if(input == 1) {
				// 회원가입 클래스로 이동
				new CasinoJoin();
				
			}else if(input == 2) {
				// 로그인 클래스로 이동
				new CasinoLogIn();
			}else if(input == 3) {
				// 회원탈퇴 클래스로 이동
				new CasinoWithdraw();
			}else if(input == 4) {
				// 게임종료
				System.out.println("게임을 종료합니다.");
				break;
				
			}else {
				System.out.println("잘못된 숫자를 입력하셨습니다.");
			}
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
}


