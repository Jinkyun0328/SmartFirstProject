package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class BlackJack {
	public BlackJack(HoguDTO dto) {
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

		cm = conm.start(14);
		
		// cm = conm.start();
		// 게임 알고리즘
		int mblack = 0; // 베팅금
		int mdto = dto.getMoney(); // 유저의 남은 금액
		int nblack = 0;  // 숫자
		int cntUser = 2;
		int cntDeal = 2; // 가지고 있는 카드의 개수
		int sumUser = 0; // 가지고 있는 카드 숫자의 합
		int sumDeal = 0;
		int victory = 0; // 0 패배, 1 승리, 2 무승부
		
		int brUser = 0; // 카드를 더 뽑을지 선택
		int brDeal = 0;

		// 1~13 스페이드 14~26 하트 27~39 클로버 40~52 다이아
		// 유저와 딜러가 가지고 있는 카드
		int[] user = new int[20];
		int[] deal = new int[20];

		int flag = 0;
		
		ViewMethod vm = new ViewMethod();
		ViewData vd = new ViewData();
		vm.clearScreen();
		
		ArrayList<String[][]> userCard = new ArrayList<String[][]>();
		ArrayList<String[][]> dealerCard1 = new ArrayList<String[][]>();
		ArrayList<String[][]> dealerCard2 = new ArrayList<String[][]>();
		
		System.out.println("블랙잭 페이지입니다.");
		
		System.out.println("\n" + dto.getNickname() + "님의 잔액은 " + dto.getMoney() + "만원입니다.");
		
		vm.sleep(3000);
		
		// 배팅금
		while (true) {
			// 만약 소지금액 mdto가 10보다 작은 경우 while문에서 탈출하지 못하기 때문에
			// 게임을 실행하지 않고 바로 종료할 필요가 있다.
			// while문을 사용하여 -1을 입력하면 게임을 바로 종료하고
			// 한 번 실행하면 바로 종료하게 했다.
			
			while (true) {
				System.out.print("\n베팅 금액(최소금액 10만원)(탈출은 -1) : ");

				while (!sc.hasNextInt() || (mblack = sc.nextInt()) < -1) {
					// ViewMethod.clearScreen();
					System.out.println("\n정수를 입력해주세요.");
					System.out.print("\n베팅 금액(최소금액 10만원)(탈출은 -1) : ");
					sc = new Scanner(System.in);
				}

				// -1을 입력하면 바로 게임 종료
				if (mblack == -1) {
					flag = 1;
					break;
				} else if (mblack < 10) {
					// 10만원의 최소금액
					System.out.println("\n최소금액 이상을 넣어주세요.");
				} else if (mdto >= mblack) {
					// 10만원 이상을 베팅하면 게임 시작
					System.out.println("\n" + dto.getNickname() + "님께서 " + mblack + "만원을 베팅하셨습니다.");
					break;
				} else {
					// mdto < mblack의 상황
					// 잔액 부족
					System.out.println("\n잔액이 부족합니다.");
					flag = 1;
					break;
				}
			}
			sc.nextLine();
			
			// 잔액이 부족하거나 -1을 입력하면 게임을 바로 종료한다.
			if(flag == 1) {
				break;
			}

			/////////////////////// 카드 분배 /////////////////////////////
			// 유저에게 카드를 분배
			for (int i = 0; i < cntUser; i++) {
				user[i] = rd.nextInt(52) + 1; // 1~52까지의 랜덤변수
				for (int j = 0; j < i; j++) {
					// 중복되지 않은 랜덤변수
					if (user[i] == user[j]) {
						i--;
						break;
					}
				}
			}


			// 카드를 뽑을 때마다 userCard에 데이터 추가
			// 유저가 가지고 있는 카드의 숫자는 user에 저장, 카드의 수는 cntUser에 저장, 카드의 합은 sumUser에 저장
			// 출력리스트에 카드를 저장
			for(int i = 0; i < cntUser; i++) {
				userCard.add(vd.clist.get(user[i]-1));
			}

			
			// 딜러에게 카드를 분배
			for (int i = 0; i < cntDeal; i++) {
				deal[i] = rd.nextInt(52) + 1; // 1~52까지의 랜덤변수

				// 중복이 발생했을 경우
				for (int j = 0; j < cntUser; j++) {
					if (deal[i] == user[j]) {
						i--;
						break;
					}
				}

				// 중복이 발생했을 경우
				// 이미 유저는 중복이 아닌 숫자이기 때문에
				// 위의 for문과 동시에 발생하지 않는다.
				for (int j = 0; j < i; j++) {
					if (deal[i] == deal[j]) {
						i--;
						break;
					}
				}
			}
			
			for(int i = 0; i < cntDeal; i++) {
				dealerCard1.add(vd.clist.get(52));
				dealerCard2.add(vd.clist.get(deal[i]-1));
			}
			
			vm.printBlackJack(dealerCard1);
			vm.printBlackJack(userCard);

			cm = conm.start(16);
			vm.sleep(2000);
			
			///// 랜덤으로 카드를 받은 직후
			// 카드의 합을 저장
			// 유저가 가지고 있는 카드의 숫자의 합
			for (int i = 0; i < cntUser; i++) {
				sumUser += (user[i] - 1) % 13 + 1;
			}

			// 딜러가 가지고 있는 카드의 숫자의 합
			for (int i = 0; i < cntDeal; i++) {
				sumDeal += (deal[i] - 1) % 13 + 1;
			}

			// 중복되지 않는 카드를 두 장씩 가진 상태
			// 현재 상황 출력
			System.out.print("\n" + dto.getNickname() + "님의 카드는 ");
			for (int i = 0; i < cntUser; i++) {
				System.out.print((user[i] - 1) % 13 + 1 + " ");
			}
			System.out.println("입니다.");
			System.out.println("\n숫자의 합 : " + sumUser);

			// 
			if(sumDeal > 21 && sumUser > 21) {
				victory = 2;
				brUser = 1;
				brDeal = 1;
				System.out.println("\n유저와 딜러의 버스트입니다.");
			}
			else if (sumDeal > 21) {
				// 딜러가 뽑은 카드 숫자의 합이 21보다 크면 유저의 패배
				// 바로 반복문 탈출
				victory = 1;
				brUser = 1;
				brDeal = 1;
				System.out.println("\n딜러의 버스트입니다.");
			} else if (sumUser > 21) {
				// 유저가 뽑은 카드 숫자의 합이 21보다 크면 유저의 패배
				// 바로 반복문 탈출
				victory = 0;
				brUser = 1;
				brDeal = 1;
				System.out.println("\n유저의 버스트입니다.");
			}


			// 유저와 딜러가 번갈아가면서 카드를 뽑을지 결정한다.
			while (sumDeal <= 21 && sumUser <= 21) {

				
				if (brUser == 1 && brDeal == 1) {
					// 유저와 딜러 모두 카드를 더 이상 뽑지 않을 때 반복문 탈출
					break;
				}
				
				// 먼저 유저가 카드를 뽑을지 선택한다.
				// y/n에 관계없이 그 다음에는 딜러가 선택
				// 만약 유저가 n을 선택하면 그 다음 딜러가 선택하고 반복문을 종료한다.

				
				if (brUser == 0) {
					System.out.print("\n카드를 한 장 더 뽑으시겠습니까?(y/n) >> ");
					String yn = sc.nextLine();
					if (yn.equals("Y") || yn.equals("y")) {
						// 카드를 한 장 더 뽑음
						cm = conm.start(16);
						vm.sleep(2000);
						
						while (true) {
							// flag가 1이면 중복. 0이면 중복 아님
							// 1~52 사이의 숫자 중 랜덤하게
							flag = 0;
							nblack = rd.nextInt(52) + 1;
							for (int i = 0; i < cntUser; i++) {
								if (nblack == user[i]) {
									flag = 1;
								}
							}

							for (int i = 0; i < cntDeal; i++) {
								if (nblack == deal[i]) {
									flag = 1;
								}
							}

							// 중복되지 않은 숫자가 선택되면
							if (flag == 0) {
								// 배열에 추가, 개수 증가
								// 카드의 합 증가
								user[cntUser++] = nblack;
								sumUser += (nblack - 1) % 13 + 1;

								// 중복을 위한 반복문 종료
								userCard.add(vd.clist.get(nblack-1));
								
								break;
							}
						}

						if (sumUser > 21) {
							// 유저가 뽑은 카드 숫자의 합이 21보다 크면 유저의 패배
							// 바로 반복문 탈출
							victory = 0;
							System.out.println("\n유저의 버스트입니다.");
							break;
						}

					} else {
						// y말고 다른 거 입력하면 그만 뽑음
						System.out.println("\n더 이상 카드를 뽑을 수 없습니다.");
						brUser = 1; // 유저는 더 이상 카드를 뽑지 않음.
					}
				}

				// 과연 딜러는 카드를 뽑을 것인가
				int selDeal = 1; // selDeal이 1이면 딜러는 카드를 뽑는다.

				// 카드의 합이 18이상이다? 그럼 안 뽑는다.
				// 17이하라면 확률적으로 뽑느다.
				if (sumDeal >= 18) {
					// 안뽑음
					selDeal = 0;
					brDeal = 1;
				} else if (sumDeal <= 8) {
					// 무조건 뽑음
					selDeal = 1;
				} else if (sumDeal <= 17) {
					// 확률적으로 뽑음
					if (21 - sumDeal > rd.nextInt(13)) {
						selDeal = 1;
					} else {
						selDeal = 0;
						brDeal = 1;
					}
				}

				if (selDeal == 1) {
					// 딜러가 카드를 뽑느다.

					while (true) {
						// flag가 1이면 중복. 0이면 중복 아님
						// 1~52 사이의 숫자 중 랜덤하게
						flag = 0;
						nblack = rd.nextInt(52) + 1;
						for (int i = 0; i < cntUser; i++) {
							if (nblack == user[i]) {
								flag = 1;
							}
						}

						for (int i = 0; i < cntDeal; i++) {
							if (nblack == deal[i]) {
								flag = 1;
							}
						}

						// 중복되지 않은 숫자가 선택되면
						if (flag == 0) {
							// 배열에 추가, 개수 증가
							// 카드의 합 증가
							deal[cntDeal++] = nblack;
							sumDeal += (nblack - 1) % 13 + 1;

							dealerCard1.add(vd.clist.get(52));
							dealerCard2.add(vd.clist.get(nblack-1));
							// 중복을 위한 반복문 종료
							break;
						}
					}

					if (sumDeal > 21) {
						// 딜러가 뽑은 카드 숫자의 합이 21보다 크면 유저의 패배
						// 바로 반복문 탈출
						victory = 1;
						System.out.println("\n딜러의 버스트입니다.");
						break;
					}
				}
				

				
				vm.printBlackJack(dealerCard1);
				vm.printBlackJack(userCard);

				System.out.print("\n" + dto.getNickname() + "님의 카드는 ");
				for (int i = 0; i < cntUser; i++) {
					System.out.print((user[i] - 1) % 13 + 1 + " ");
				}
				System.out.println("입니다.");
				System.out.println("\n숫자의 합 : " + sumUser);
			}
			
			vm.printBlackJack(dealerCard2);
			vm.printBlackJack(userCard);

			// 승리조건
			// 1. 딜러의 버스트
			// 2. 유저가 21이하면서 딜러보다 크다

			if (sumDeal > 21) {
				// 딜러의 버스트
				victory = 1;
			} else if (sumUser > 21) {
				// 유저의 버스트
				victory = 0;
			} else if (sumUser <= 21 && sumUser > sumDeal) {
				// 유저가 21이하면서 딜러보다 크다.
				victory = 1;
			} else if (sumUser == sumDeal) {
				// 같으면 무승부
				victory = 2;
			} else {
				// 이외에는 패배
				victory = 0;
			}

			System.out.print("\n딜러의 카드는 ");
			for (int i = 0; i < cntDeal; i++) {
				System.out.print((deal[i] - 1) % 13 + 1 + " ");
			}
			System.out.println("입니다.");
			System.out.println("\n숫자의 합 : " + sumDeal);
			
			System.out.print("\n" + dto.getNickname() + "님의 카드는 ");
			for (int i = 0; i < cntUser; i++) {
				System.out.print((user[i] - 1) % 13 + 1 + " ");
			}
			System.out.println("입니다.");
			System.out.println("\n숫자의 합 : " + sumUser);

			

			// 승리문구 출력
			if (victory == 0) {
				// 패배
				System.out.println("\n패배하셨습니다.");
				dto.setMoney(mdto - mblack);
				cm = conm.start(8);
			} else if (victory == 1) {
				dto.setMoney(mdto + mblack);
				System.out.println("\n승리하셨습니다.");
				cm = conm.start(5);
			} else if (victory == 2) {
				System.out.println("\n무승부입니다.");
				cm = conm.start(19);
			}

			dao.updateMoney(dto);
			System.out.println("\n" + dto.getNickname() + "님의 잔액은 " + dto.getMoney() + "만원입니다.");
			
			break;
		}
	}
}
