package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class CasinoWithdraw {
	public CasinoWithdraw() {
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
		
		ViewMethod vm = new ViewMethod();
		vm.clearScreen();
		
		System.out.println("====회원탈퇴====");
		System.out.print("\nID를 입력해주세요 >> ");
		id = sc.nextLine();
		System.out.print("\nPW를 입력해주세요 >> ");
		pw = sc.nextLine();
		
		// 여기서부터는 데이터 이동 클래스가 필요함
		HoguDAO dao = new HoguDAO();
		HoguDTO dto = new HoguDTO(id, pw, "");

		if (dao.delete(dto) > 0) {
			System.out.println("\n회원탈퇴가 완료되었습니다.");
		}else {
			System.out.println("\n잘못된 아이디 혹은 비밀번호입니다.");
		}
		
		vm.sleep(3000);
	}
}
