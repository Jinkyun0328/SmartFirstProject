package View;

import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;
import SelectionWheel.MainWheel;

import javax.swing.*;

import java.util.*;

public class Rulette {
	public Rulette(HoguDTO dto){
		System.out.println("룰렛 페이지입니다.");
		
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
		
		HoguDAO dao = new HoguDAO();
		
		// cm = conm.start();
		// 게임 알고리즘
		int mrul = 0; // 돈
		int nrul = 0; // 숫자
		int mdto = dto.getMoney();
		int flag = 0;
		
		while(true) {
			while (true) {
				System.out.print("베팅 금액(최소금액 10만원)(탈출은 -1) : ");

				while (!sc.hasNextInt() || (mrul = sc.nextInt()) < -1) {
					// ViewMethod.clearScreen();
					System.out.println("정수를 입력해주세요.");
					System.out.print("베팅 금액(최소금액 10만원)(탈출은 -1) : ");
					sc = new Scanner(System.in);
				}

				// -1을 입력하면 바로 게임 종료
				if (mrul == -1) {
					flag = 1;
					break;
				} else if (mrul < 10) {
					// 10만원의 최소금액
					System.out.println("최소금액 이상을 넣어주세요.");
				} else if (mdto >= mrul) {
					// 10만원 이상을 베팅하면 게임 시작
					System.out.println(dto.getNickname() + "님께서 " + mrul + "만원을 베팅하셨습니다.");
					break;
				} else {
					// mdto < mrul의 상황
					// 잔액 부족
					System.out.println("잔액이 부족합니다.");
					flag = 1;
					break;
				}
			}
			sc.nextLine();
			
			// 잔액이 부족하거나 -1을 입력하면 게임을 바로 종료한다.
			if(flag == 1) {
				break;
			}
			
			while(true) {
				System.out.print("1, 3, 5, 10, 20 중 하나를 선택하세요 >> ");
				nrul = sc.nextInt();
				
				if(nrul == 1 || nrul == 3 ||nrul == 5 ||nrul == 10 ||nrul == 20) {
					break;
				}else {
					System.out.println("다시 입력하세요.");
				}
			}
			sc.nextLine();

			int[] rulNumber = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 10, 10, 20};
			while(true) {
				System.out.print("룰렛을 돌리시겠습니까?(y/n)");
				String yn = sc.nextLine();
				if(yn.equals("y") || yn.equals("Y")) {
					break;
				}
			}
			
			MainWheel mw = new MainWheel();
			
			int index = rd.nextInt(25);
			// 룰렛 돌아가는 모션
			
			cm = conm.start(6);
			System.out.println("룰렛 결과 : " + rulNumber[index]);
			if(nrul == rulNumber[index]) {
				// 맞추기 성공
				mrul = mrul*rulNumber[index];
				dto.setMoney(dto.getMoney() + mrul);
				cm = conm.start(12);
			}else {
				// 실패
				dto.setMoney(dto.getMoney() - mrul);
				cm = conm.start(15);
			}
			dao.updateMoney(dto);
			System.out.println(dto.getNickname() + "님의 잔액은 " + dto.getMoney() + "만원입니다.");
			
			break;
		}
	}
}
