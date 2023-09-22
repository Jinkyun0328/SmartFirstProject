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
		
		// 화면 초기화
		ViewMethod vm = new ViewMethod();
		vm.clearScreen();
		
		System.out.println("====닉네임조회====");
		// 닉네임 입력
		HoguDAO dao = new HoguDAO();
		System.out.print("\n검색하고자 하는 닉네임을 입력하세요 >> ");
		nickname = sc.nextLine();
		HoguDTO dto = dao.search(nickname);
		
		if(dto != null) {
			conm.start(5);
			String msg = "\n";
			msg += dto.getNickname() + "의 금액은 ";
			msg += dto.getMoney() + "만원입니다."; 
			System.out.println(msg);
		}else {
			System.out.println("\n존재하지 않는 닉네임입니다.");
		}
	}
}
