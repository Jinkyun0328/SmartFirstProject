package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class CasinoJoin {
	public CasinoJoin() {
		// 회원가입 화면
		// 회원가입에 필요한 ID, PW, NICKNAME을 입력받아 DTO 자료형에 저장하고 DAO를 사용하여 DB에 저장한다.

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

		while (true) {
			System.out.println("====회원가입 화면입니다====");
			// String형은 숫자, 문자, 특수문자에 관계없이 문자열 형으로 입력받으므로 예외처리를 따로 두지 않음
			System.out.println("-1을 입력하면 회원가입을 하지 않고 탈출합니다.");
			System.out.print("\nID를 입력해주세요 >> ");
			id = sc.nextLine();
			System.out.print("\nPW를 입력해주세요 >> ");
			pw = sc.nextLine();
			System.out.print("\nNICKNAME를 입력해주세요 >> ");
			nickname = sc.nextLine();

			if (id.equals("-1") || pw.equals("-1") || nickname.equals("-1")) {
				System.out.println("\n -1을 입력하여 회원가입을 진행하지 않았습니다.");
				break;
			}

			// 여기서부터는 데이터 이동 클래스가 필요함
			HoguDAO dao = new HoguDAO();
			HoguDTO dto = new HoguDTO(id, pw, nickname);

			if (dao.join(dto) > 0) {
				System.out.println("\n회원가입에 성공하였습니다.");
				conm.start(5);
				
			} else {
				System.out.println("\n회원가입에 실패하였습니다.");
				System.out.println("중복된 아이디(닉네임)가 존재하거나 형식에 맞지 않습니다.");
			}

			System.out.println("\n잠시 후 메인화면으로 이동합니다.");

			vm.sleep(3000);

			break;
		}
		System.out.println();
	}
}
