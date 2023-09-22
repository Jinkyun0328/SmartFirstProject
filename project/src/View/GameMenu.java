package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class GameMenu {
	public GameMenu(HoguDTO dto) {
		// 로그인이 완료되면 오는 클래스
		// [1]게임조회 [2]랭킹조회 [3]닉네임조회 [4]로그아웃
		// 중 하나를 선택하며 4번을 입력하면 Main()으로 돌아간다.
		
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
		
		// 자 게임을 시작하지 음악 틀기
		cm = conm.start(11); 	
		
		// 화면 초기화
		ViewMethod vm = new ViewMethod();
		vm.clearScreen();
		
		System.out.println("====로그인 성공!!====");
		System.out.println("\n" + dto.getNickname() + "님의 남은 금액은 " + dto.getMoney() + "만원입니다.");
		
		
		while(true) {
//			dao.updateMoney(dto);
//			dto.setMoney(dao.search(dto.getNickname()).getMoney());
			
			int mdto = dto.getMoney();
			if(mdto < 10) {
				System.out.println("\n" + dto.getNickname() + "님의 남은 잔액이 10만원보다 적습니다.");
				
				System.out.print("\n김성민님께 딱밤을 맞으신 후 50만원을 추가로 받으시겠습니까? (y/n) >> ");
				String yn = sc.nextLine();
				
				if(yn.equals("y") || yn.equals("Y")) {
					System.out.println("\n" + dto.getNickname() + "님께서 50만원을 얻으셨습니다.");
					
					conm.start(21);
					
					// dto에 50만원이 추가되고 DB에도 추가하기
					dto.setMoney(dto.getMoney() + 50);
					dao.updateMoney(dto);
					
					System.out.println("\n" + dto.getNickname() + "님의 남은 금액은 " + dto.getMoney() + "만원입니다.");
					
					
				}
			}
			
			
			int input = 0;
			System.out.print("\n[1]게임조회 [2]랭킹조회 [3]닉네임조회 [4]로그아웃 >> ");
			
			// scanner에 정수가 아닌 다른 자료형을 입력할 경우 예외처리를 하지 않으면 컴파일러가 프로그램을 종료한다.
			// 프로그램을 종료하지 않고 다시 정수를 입력받을 수 있게 하는 코드
			// sc.nextLine()은 int형을 입력받고 메모리를 비우기 위해 추가했다.
			while(!sc.hasNextInt() || (input = sc.nextInt()) < 0){
				// ViewMethod.clearScreen();
				System.out.println("\n정수를 입력해주세요.");
				System.out.print("\n[1]게임조회 [2]랭킹조회 [3]닉네임조회 [4]로그아웃 >> ");
				sc = new Scanner(System.in);
			}
			sc.nextLine();
			
			if(input == 1) {
				// 게임조회 화면으로 넘어가기
				new SelectGame(dto);
			}else if(input == 2) {
				// 랭킹조회 화면으로 넘어가기
				new SearchRank();
			}else if(input == 3) {
				// 닉네임조회 화면으로 넘어가기
				new SearchNickname();
			}else if(input == 4) {
				// 로그아웃
				System.out.println("\n로그아웃 되었습니다.");
				System.out.println("\n" + dto.getNickname() + "님의 남은 금액은 " + dto.getMoney() + "만원입니다.");
				
				// 현재 금액을 db에 저장
				dao.updateMoney(dto);
				
				System.out.println("\n잠시 후 메인화면으로 이동합니다.");

				try {
					// Sleep for 3 seconds (3000 milliseconds)
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// Handle interrupted exception if needed
					e.printStackTrace();
				}
				
				break;
				
			}else {
				System.out.println("\n잘못된 숫자를 입력하셨습니다.");
			}
		}
	}
}
