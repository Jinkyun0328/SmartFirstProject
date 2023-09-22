package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class SelectGame {
	public SelectGame(HoguDTO dto) {
		System.out.println("게임조회 화면입니다.");

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

		// 게임조회 화면에서는 1번의 게임이 끝나면 GameMenu 화면으로 이동한다.
		// 따라서 반복문 없음

		int input = 0;
		System.out.print("[1]슬롯머신 [2]블랙잭 [3]룰렛 >> ");

		// scanner에 정수가 아닌 다른 자료형을 입력할 경우 예외처리를 하지 않으면 컴파일러가 프로그램을 종료한다.
		// 프로그램을 종료하지 않고 다시 정수를 입력받을 수 있게 하는 코드
		// sc.nextLine()은 int형을 입력받고 메모리를 비우기 위해 추가했다.
		while (!sc.hasNextInt() || (input = sc.nextInt()) < 0) {
			// ViewMethod.clearScreen();
			System.out.println("정수를 입력해주세요.");
			System.out.print("[1]슬롯머신 [2]블랙잭 [3]룰렛 >> ");
			sc = new Scanner(System.in);
		}
		sc.nextLine();

		if (input == 1) {
			// 슬롯머신 페이지로 이동
			new SlotMachine(dto);

			// 게임이 끝나면 money를 업데이트한다.
			dto.setMoney(dao.search(dto.getNickname()).getMoney());

		} else if (input == 2) {
			// 블랙잭 페이지로 이동
			new BlackJack(dto);

			// 게임이 끝나면 money를 업데이트한다.
			dto.setMoney(dao.search(dto.getNickname()).getMoney());

		} else if (input == 3) {
			// 룰렛 페이지로 이동
			new Rulette(dto);

			// 게임이 끝나면 money를 업데이트한다.
			dto.setMoney(dao.search(dto.getNickname()).getMoney());

		} else {
			System.out.println("잘못된 숫자를 입력하셨습니다.");
		}
	}
}
