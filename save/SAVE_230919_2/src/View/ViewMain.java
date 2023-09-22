package View;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class ViewMain {

	public ViewMain() {
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		
		String id = null;
		String pw = null;
		String nickname = null;
		int money = 0;
		
		ArrayList<HoguDTO> list = new ArrayList<HoguDTO>();
		casinoController conm = new casinoController();
		casinoMusic cm;

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
				// cm = conm.start(1);

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
					cm = conm.start(11); 	// 자 게임을 시작하지 음악 틀기
					
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
								// 사용자 정보는 dto에 저장중
								System.out.println("====슬롯머신====");
								cm = conm.start(2);
								
								// 게임 알고리즘
								int slot = 0;
								while(true) {
									System.out.print("베팅 금액(최소금액 5만원) : ");
									slot = sc.nextInt();
									
									
									if(slot < 5) {
										System.out.println("최소금액 이상을 넣어주세요.");
									}
									else if(dto.getMoney() >= slot) {
										break;
									}else {
										System.out.println("잔액이 부족합니다.");
									}
								}

								sc.nextLine();
								int[] slotNumber = new int[3];
								for(int index = 0; index < slotNumber.length; index++) {
									while(true) {
										System.out.print(index + 1 + "번 슬롯을 돌리시겠습니까?(y/n) >> ");
										String yn = sc.nextLine();
										if(yn.equals("Y") || yn.equals("y")) {
											// 룰렛 돌아가서 숫가자 정해짐
											cm = conm.start(13);
											slotNumber[index] = rd.nextInt(10);
											break;
										}
									}
									
									System.out.print("slot : ");
									for(int i = 0; i <= index; i++) {
										System.out.print(slotNumber[i] + " ");
									}
									System.out.println();
								}
								
								// slotNumber[3]에는 랜덤 숫자가 입력되어 있어요.
								// index 0, 1, 2의 숫자가 모두 같으면? 담첨. 7.7.7이면 잭팟
								// 3개면 당첨. 2개 이상이면 배팅금 회수. 1개 이하면 꽝
								// 잭팟이면 20배, 당첨이면 10배
								
								int mslot = dto.getMoney();
								if(slotNumber[0] == slotNumber[1] && slotNumber[0] == slotNumber[2]) {
									// 당첨인경우
									if(slotNumber[0] == 7) {
										// 잭팟인 경우
										// 배팅금도 회수하고 배팅금의 20배의 돈을 가질 수 있음
										// dto.money *= 21;
										mslot += (slot * 20);
										cm = conm.start(4);
										
									}else {
										// 당첨인데 잭팟은 아님
										// 배팅금도 회수하고 배팅금의 10배의 돈을 가질 수 있음
										// dto.money *= 11;
										mslot += (slot * 10);
										cm = conm.start(4);
									}
									
									cm = conm.start(12);
								}else if(slotNumber[0] == slotNumber[1] || slotNumber[0] == slotNumber[2] || slotNumber[1] == slotNumber[2] ) {
									// 3개의 숫자 중에서 3개 모두가 같은 경우를 제외하고
									// 2개의 숫자만 같은 경우
									// 배팅금만 회수한다.
									// 아무것도 실행 안함
									mslot += 0;
									cm = conm.start(19);
								}else {
									// 3개의 숫자 중에서 같은 숫자가 하나도 없는 경우. 꽝인 경우
									// 배팅금을 잃는다.
									// dto.meney -= slot
									mslot -= slot;
									cm = conm.start(15);
								}
								
								dto.setMoney(mslot);
								dao.updateMoney(dto);
								System.out.println(dto.getNickname() + "님의 잔액은 " + dto.getMoney() + "만원입니다.");
								
							}else if(input3 == 2) {
								System.out.println("====블랙잭====");
								cm = conm.start(2);
								// cm = conm.start();
								// 게임 알고리즘
								int mblack = 0; // 돈
								int nblack = 0; // 숫자
								int cntUser = 2;
								int cntDeal = 2; // 가지고 있는 카드의 개수
								int sumUser = 0; // 가지고 있는 카드 숫자의 합
								int sumDeal = 0;
								int victory = 0;
								
								// 1~13 스페이드 14~26 하트 27~39 클로버 40~52 다이아
								// 유저와 딜러가 가지고 있는 카드
								int[] user = new int[20];
								int[] deal = new int[20];
								
								// 배팅금
								while(true) {
									System.out.print("베팅 금액(최소금액 10만원) : ");
									mblack = sc.nextInt();
									
									
									if(mblack < 10) {
										System.out.println("최소금액 이상을 넣어주세요.");
									}
									else if(dto.getMoney() >= mblack) {
										break;
									}else {
										System.out.println("잔액이 부족합니다.");
									}
								}
								
								sc.nextLine();
								
								/////////////////////// 카드 분배 /////////////////////////////
								// 유저에게 카드를 분배
								for(int i = 0; i < cntUser; i++) {
									user[i] = rd.nextInt(52) + 1;		// 1~52까지의 랜덤변수
									for(int j = 0; j < i; j++) {
										if(user[i] == user[j]) {
											i--;
											break;
										}
									}
								}
								
								cm = conm.start(16);

								// 딜러에게 카드를 분배
								for(int i = 0; i < cntDeal; i++) {
									deal[i] = rd.nextInt(52) + 1;		// 1~52까지의 랜덤변수
									
									for(int j = 0; j < cntUser; j++) {
										if(deal[i] == user[j]) {
											i--;
											break;
										}
									}
									
									for(int j = 0; j < i; j++) {
										if(deal[i] == deal[j]) {
											i--;
											break;
										}
									}
								}
								
								// 유저가 가지고 있는 카드의 숫자의 합
								for(int i = 0; i < cntUser; i++) {
									sumUser += (user[i]-1)%13+1;
								}
								
								// 딜러가 가지고 있는 카드의 숫자의 합
								for(int i = 0; i < cntDeal; i++) {
									sumDeal += (deal[i]-1)%13+1;
								}
								
								// 중복되지 않는 카드를 두 장씩 가진 상태
								// 현재 상황 출력
								System.out.print(dto.getNickname() + "님의 카드는 ");
								for(int i = 0; i < cntUser; i++) {
									System.out.print((user[i]-1)%13 + 1 + " ");
								}
								System.out.println("입니다.");
								System.out.println("숫자의 합 : " + sumUser);
								
								// 중복되지 않는 카드를 두 장씩 가진 상태
								System.out.print("딜러의 카드는 ");
								for(int i = 0; i < cntDeal; i++) {
									System.out.print((deal[i]-1)%13 + 1 + " ");
								}
								System.out.println("입니다.");
								System.out.println("숫자의 합 : " + sumDeal);
								

								if(sumDeal > 21) {
									// 딜러가 뽑은 카드 숫자의 합이 21보다 크면 유저의 패배
									// 바로 반복문 탈출
									victory = 1;
									System.out.println("딜러의 버스트입니다.");
									break;
								}else if(sumUser > 21) {
									// 유저가 뽑은 카드 숫자의 합이 21보다 크면 유저의 패배
									// 바로 반복문 탈출
									victory = 0;
									System.out.println("유저의 버스트입니다.");
									break;
								}

								int brUser = 0;
								int brDeal = 0;
								// 유저와 딜러가 번갈아가면서 카드를 뽑을지 결정한다.
								while(sumDeal <= 21 && sumUser <= 21) {
									
									// 먼저 유저가 카드를 뽑을지 선택한다.
									// y/n에 관계없이 그 다음에는 딜러가 선택
									// 만약 유저가 n을 선택하면 그 다음 딜러가 선택하고 반복문을 종료한다.
									
									if (brUser == 0) {
										System.out.print("카드를 한 장 더 뽑으시겠습니까?(y/n) >> ");
										String yn = sc.nextLine();
										if(yn.equals("Y") || yn.equals("y")) {
											// 카드를 한 장 더 뽑음
											cm = conm.start(16);
											
											while(true) {
												// flag가 1이면 중복. 0이면 중복 아님
												// 1~52 사이의 숫자 중 랜덤하게
												int flag = 0;
												nblack = rd.nextInt(52) + 1;
												for(int i = 0; i < cntUser; i++) {
													if(nblack == user[i]) {
														flag = 1;
													}
												}
												
												for(int i = 0; i < cntDeal; i++) {
													if(nblack == deal[i]) {
														flag = 1;
													}
												}
												
												// 중복되지 않은 숫자가 선택되면
												if(flag == 0) {
													// 배열에 추가, 개수 증가
													// 카드의 합 증가
													user[cntUser++] = nblack;
													sumUser += (nblack-1)%13+1;

													// 중복을 위한 반복문 종료
													break;
												}
											}
											
											if(sumUser > 21) {
												// 유저가 뽑은 카드 숫자의 합이 21보다 크면 유저의 패배
												// 바로 반복문 탈출
												victory = 0;
												System.out.println("유저의 버스트입니다.");
												break;
											}
											
										}else {
											// y말고 다른 거 입력하면 그만 뽑음
											System.out.println("더 이상 카드를 뽑을 수 없습니다.");
											brUser = 1;	// 유저는 더 이상 카드를 뽑지 않음.
										}
									}
									
									
									// 과연 딜러는 카드를 뽑을 것인가
									int selDeal = 1;		// selDeal이 1이면 딜러는 카드를 뽑는다.
									
									// 카드의 합이 18이상이다? 그럼 안 뽑는다.
									// 17이하라면 확률적으로 뽑느다.
									if(sumDeal >= 18) {
										// 안뽑음
										selDeal = 0;
										brDeal = 1;
									}else if(sumDeal <= 8) {
										// 무조건 뽑음
										selDeal = 1;
									}else if(sumDeal <= 17) {
										// 확률적으로 뽑음
										if (21-sumDeal > rd.nextInt(13)) {
											selDeal = 1;
										}else {
											selDeal = 0;
											brDeal = 1;
										}
									}
									
									if(selDeal == 1) {
										// 딜러가 카드를 뽑느다.
										
										while(true) {
											// flag가 1이면 중복. 0이면 중복 아님
											// 1~52 사이의 숫자 중 랜덤하게
											int flag = 0;
											nblack = rd.nextInt(52) + 1;
											for(int i = 0; i < cntUser; i++) {
												if(nblack == user[i]) {
													flag = 1;
												}
											}
											
											for(int i = 0; i < cntDeal; i++) {
												if(nblack == deal[i]) {
													flag = 1;
												}
											}
											
											// 중복되지 않은 숫자가 선택되면
											if(flag == 0) {
												// 배열에 추가, 개수 증가
												// 카드의 합 증가
												deal[cntDeal++] = nblack;
												sumDeal += (nblack-1)%13+1;

												// 중복을 위한 반복문 종료
												break;
											}
										}
										
										if(sumDeal > 21) {
											// 딜러가 뽑은 카드 숫자의 합이 21보다 크면 유저의 패배
											// 바로 반복문 탈출
											victory = 1;
											System.out.println("딜러의 버스트입니다.");
											break;
										}
									}
	
									System.out.print(dto.getNickname() + "님의 카드는 ");
									for(int i = 0; i < cntUser; i++) {
										System.out.print((user[i]-1)%13 + 1 + " ");
									}
									System.out.println("입니다.");
									System.out.println("숫자의 합 : " + sumUser);
									
									System.out.print("딜러의 카드는 ");
									for(int i = 0; i < cntDeal; i++) {
										System.out.print((deal[i]-1)%13 + 1 + " ");
									}
									System.out.println("입니다.");
									System.out.println("숫자의 합 : " + sumDeal);
									
									if(brUser == 1 && brDeal == 1) {
										// 유저와 딜러 모두 카드를 더 이상 뽑지 않을 때 반복문 탈출
										break;
									}
								}

								// 승리조건
								// 1. 딜러의 버스트
								// 2. 유저가 21이하면서 딜러보다 크다
								
								if(sumDeal > 21) {
									// 딜러의 버스트
									victory = 1;
								}else if(sumUser > 21){
									// 유저의 버스트
									victory = 0;
								}else if(sumUser <= 21 && sumUser > sumDeal) {
									// 유저가 21이하면서 딜러보다 크다.
									victory = 1;
								}else if(sumUser == sumDeal) {
									// 같으면 무승부
									victory = 2;
								}else {
									// 이외에는 패배
									victory = 0;
								}
								
								
								System.out.print(dto.getNickname() + "님의 카드는 ");
								for(int i = 0; i < cntUser; i++) {
									System.out.print((user[i]-1)%13 + 1 + " ");
								}
								System.out.println("입니다.");
								System.out.println("숫자의 합 : " + sumUser);
								
								System.out.print("딜러의 카드는 ");
								for(int i = 0; i < cntDeal; i++) {
									System.out.print((deal[i]-1)%13 + 1 + " ");
								}
								System.out.println("입니다.");
								System.out.println("숫자의 합 : " + sumDeal);
								
								// 승리문구 출력
								if(victory == 0) {
									// 패배
									System.out.println("패배하셨습니다.");
									dto.setMoney(dto.getMoney()-mblack);
									cm = conm.start(8);
								}else if(victory == 1) {
									dto.setMoney(dto.getMoney()+mblack);
									System.out.println("승리하셨습니다.");
									cm = conm.start(5);
								}else if(victory == 2) {
									System.out.println("무승부입니다.");
									cm = conm.start(19);
								}
								
								dao.updateMoney(dto);
								System.out.println(dto.getNickname() + "님의 잔액은 " + dto.getMoney()+"만원입니다.");
								
								
							}else if(input3 == 3) {
								System.out.println("====룰렛====");
								// cm = conm.start();
								// 게임 알고리즘
								int mrul = 0; // 돈
								int nrul = 0; // 숫자
	
								while(true) {
									System.out.print("베팅 금액(최소금액 10만원) : ");
									mrul = sc.nextInt();
									
									
									if(mrul < 10) {
										System.out.println("최소금액 이상을 넣어주세요.");
									}
									else if(dto.getMoney() >= mrul) {
										break;
									}else {
										System.out.println("잔액이 부족합니다.");
									}
								}
								
								while(true) {
									System.out.print("1, 3, 5, 10, 20 중 하나를 선택하세요 >> ");
									nrul = sc.nextInt();
									
									if(nrul == 1 || nrul == 3 ||nrul == 5 ||nrul == 10 ||nrul == 20) {
										break;
									}else {
										System.out.println("다시 입력하세요.");
									}
								}
								sc.nextLine();
								
								int[] rulNumber = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 10, 10, 20};
								while(true) {
									System.out.print("룰렛을 돌리시겠습니까?(y/n)");
									String yn = sc.nextLine();
									if(yn.equals("y") || yn.equals("Y")) {
										break;
									}
								}
								
								int index = rd.nextInt(25);
								// 룰렛 돌아가는 모션
								
								cm = conm.start(6);
								System.out.println("룰렛 결과 : " + rulNumber[index]);
								if(nrul == rulNumber[index]) {
									// 맞추기 성공
									mrul = mrul*rulNumber[index];
									dto.setMoney(dto.getMoney() + mrul);
									cm = conm.start(12);
								}else {
									// 실패
									dto.setMoney(dto.getMoney() - mrul);
									cm = conm.start(15);
								}
								dao.updateMoney(dto);
								System.out.println(dto.getNickname() + "님의 잔액은 " + dto.getMoney() + "만원입니다.");
								
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
							System.out.println(dto.getNickname() + "님의 잔액은 " + dto.getMoney() + "만원입니다.");
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
