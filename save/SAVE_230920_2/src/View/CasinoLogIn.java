package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class CasinoLogIn {
	public CasinoLogIn(){
		System.out.println("로그인 화면입니다.");
	
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

		// String형은 숫자, 문자, 특수문자에 관계없이 문자열 형으로 입력받으므로 예외처리를 따로 두지 않음
		System.out.print("ID를 입력해주세요 >> ");
		id = sc.nextLine();
		System.out.print("PW를 입력해주세요 >> ");
		pw = sc.nextLine();

		// 데이터 이동 클래스가 필요함
		HoguDAO dao = new HoguDAO();
		HoguDTO dto = new HoguDTO();

		dto.setId(id);
		dto.setPw(pw);
		
		dto = dao.login(dto);
		if(dto != null) {
			new GameMenu(dto);
		}else {
			System.out.println("로그인에 실패했습니다.");
			System.out.println("아이디와 비밀번호를 확인해주시기 바랍니다.");
		}
	}
}
