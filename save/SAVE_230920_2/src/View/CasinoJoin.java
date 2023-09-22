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
		
		System.out.println("회원가입 화면입니다.");
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
		System.out.print("NICKNAME를 입력해주세요 >> ");
		nickname = sc.nextLine();

		// 여기서부터는 데이터 이동 클래스가 필요함
		HoguDAO dao = new HoguDAO();
		HoguDTO dto = new HoguDTO(id, pw, nickname);
		
		// 회원가입이 완료되면 dao.join(dto)은 0보다 큰 값을 갖는다.
		// 만약 중복 아이디가 있다면?
		// 회원가입이 정상적으로 이루어지지 않는다면 어떻게 될것인가?
		// 오류 메시지를 출력하고 CasinoJoin()이 정상적으로 마무리되며
		// 다시 GameMenu()로 돌아가 회원가입을 선택할 수 있게 한다.
		
		if (dao.join(dto) > 0) {
			System.out.println("회원가입이 완료되었습니다.");
		}else {
			System.out.println("회원가입에 실패하였습니다.");
			System.out.println("중복된 아이디가 존재하거나 형식에 맞지 않습니다.");
		}
	}
}
