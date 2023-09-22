package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class SearchRank {
	public SearchRank() {
		// 랭킹조회에서는 현재 아이디의 정보를 받지 않는다.
		// 기본생성자에서 구현
		
		System.out.println("랭킹조회 화면입니다.");
		
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

		// 데이터 이동 클래스가 필요함
		HoguDAO dao = new HoguDAO();
		
		list = dao.selectRank();
		
		
		
		// 추후에 변경
		String msg = "RANK" + "\t";
		msg += "NICKNAME" + "\t";
		msg += "SCORE" + "\t";
		System.out.println(msg);
		
		for(int i = 0; i < list.size(); i++) {
			msg = "";
			msg += i+1 + "\t";
			msg += list.get(i).getNickname() + "\t\t";
			msg += list.get(i).getMoney() + "\t";
			System.out.println(msg);
		}
	}
}
