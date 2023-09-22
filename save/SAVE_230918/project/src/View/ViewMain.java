package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Model.HoguDAO;
import Model.HoguDTO;

public class ViewMain {

	public ViewMain() {
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		
		String id = null;
		String pw = null;
		String nickname = null;
		int money = 0;
		
		ArrayList<HoguDTO> list = new ArrayList<HoguDTO>();

		while(true) {
			// First Window
			int input = 0;
			System.out.print("[1]회원가입 [2]로그인 [3]회원탈퇴 [4]게임종료 >> ");
			while(!sc.hasNextInt() || (input = sc.nextInt()) < 0){
				System.out.println("Enter interger please");
				System.out.print("[1]회원가입 [2]로그인 [3]회원탈퇴 [4]게임종료 >> ");
				sc = new Scanner(System.in);
			}
			sc.nextLine();
			
			
			if(input == 1) {
				System.out.println("====회원가입====");
				System.out.print("ID를 입력해주세요 >> ");
				id = sc.nextLine();
				System.out.print("PW를 입력해주세요 >> ");
				pw = sc.nextLine();
				System.out.print("NICKNAME을 입력해주세요 >> ");
				nickname = sc.nextLine();
				
				// 여기서부터는 데이터 이동 클래스가 필요함
				HoguDAO dao = new HoguDAO();
				HoguDTO dto = new HoguDTO(id, pw, nickname);
				
				if (dao.join(dto) > 0) {
					System.out.println("회원가입이 완료되었습니다.");
				}
			}else if(input == 2){
				System.out.println("====로그인====");
				System.out.print("ID를 입력해주세요 >> ");
				id = sc.nextLine();
				System.out.print("PW를 입력해주세요 >> ");
				pw = sc.nextLine();
				
				// 여기서부터는 데이터 이동 클래스가 필요함
				HoguDAO dao = new HoguDAO();
				HoguDTO dto = new HoguDTO();
				dto.setId(id);
				dto.setPw(pw);
				
				dto = dao.login(dto);
				if(dto.getId() != null) {
					System.out.println("====로그인 되었습니다====");
					System.out.println(dto.getNickname() + "님의 남은 금액은 " + dto.getMoney() + "만원입니다.");
					while(true) {
						int input2 = 0;
						System.out.print("[1]게임조회 [2]랭킹조회 [3]닉네임조회 [4]로그아웃 >> ");
						while(!sc.hasNextInt() || (input2 = sc.nextInt()) < 0){
							System.out.println("Enter interger please");
							System.out.print("[1]게임조회 [2]랭킹조회 [3]닉네임조회 [4]로그아웃 >> ");
							sc = new Scanner(System.in);
						}
						sc.nextLine();
						
						if(input2 == 1) {
							System.out.println("====게임조회====");
							System.out.print("[1]슬롯머신 [2]블랙잭 [3]룰렛 >> ");
							int input3 = 0;
							while(!sc.hasNextInt() || (input3 = sc.nextInt()) < 0){
								System.out.println("Enter interger please");
								System.out.print("[1]슬롯머신 [2]블랙잭 [3]룰렛 >> ");
								sc = new Scanner(System.in);
							}
							sc.nextLine();
							
							if(input3 == 1) {
								System.out.println("====슬롯머신====");
								// 게임 알고리즘
								
								
								
							}else if(input3 == 2) {
								System.out.println("====블랙잭====");
								// 게임 알고리즘
								
								
							}else if(input3 == 3) {
								System.out.println("====룰렛====");
								// 게임 알고리즘
								
								
							}else {
								System.out.println("잘못된 숫자를 입력하셨습니다.");
							}
							
							
						}else if(input2 == 2) {
							System.out.println("====랭킹조회====");
							// 여기서부터는 데이터 이동 클래스가 필요함
							list = dao.selectRank();
							
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
							
						}else if(input2 == 3) {
							System.out.println("====닉네임조회====");
							// 여기서부터는 데이터 이동 클래스가 필요함
							System.out.print("검색하고자 하는 닉네임을 입력하세요 >> ");
							nickname = sc.nextLine();
							HoguDTO dto1 = dao.search(nickname);
							
							if(dto1.getId() != null) {
								String msg = "";
								msg += dto1.getNickname() + "의 금액은 ";
								msg += dto1.getMoney() + "만원입니다."; 
								System.out.println(msg);
							}else {
								System.out.println("존재하지 않는 닉네임입니다.");
							}
							
							
						}else if(input2 == 4) {
							System.out.println("====로그아웃되었습니다====");
							break;
						}else {
							System.out.println("잘못된 숫자를 입력하셨습니다.");
						}
					}
				}
				
				
			}else if(input == 3){
				System.out.println("====회원탈퇴====");
				System.out.print("ID를 입력해주세요 >> ");
				id = sc.nextLine();
				System.out.print("PW를 입력해주세요 >> ");
				pw = sc.nextLine();
				
				// 여기서부터는 데이터 이동 클래스가 필요함
				HoguDAO dao = new HoguDAO();
				HoguDTO dto = new HoguDTO(id, pw, "");

				if (dao.delete(dto) > 0) {
					System.out.println("회원탈퇴가 완료되었습니다.");
				}
				
			}else if(input == 4){
				System.out.println("====게임을 종료합니다====");
				break;
			}else{
				System.out.println("잘못된 숫자를 입력하셨습니다.");
			}
		}
	}
	
	public static void main(String[] args) {
		new ViewMain();
	}

}
