package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class SearchNickname {
	public SearchNickname() {
		System.out.println("닉네임조회 화면입니다.");
		
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

		// 닉네임 입력
		HoguDAO dao = new HoguDAO();
		System.out.print("검색하고자 하는 닉네임을 입력하세요 >> ");
		nickname = sc.nextLine();
		HoguDTO dto = dao.search(nickname);
		
		if(dto != null) {
			String msg = "";
			msg += dto.getNickname() + "의 금액은 ";
			msg += dto.getMoney() + "만원입니다."; 
			System.out.println(msg);
		}else {
			System.out.println("존재하지 않는 닉네임입니다.");
		}
	}
}
