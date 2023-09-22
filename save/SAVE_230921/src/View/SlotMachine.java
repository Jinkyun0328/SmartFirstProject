package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class SlotMachine {
	public SlotMachine(HoguDTO dto) {
		System.out.println("슬롯머신 페이지입니다.");

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

		cm = conm.start(2);
		HoguDAO dao = new HoguDAO();

		// 게임 알고리즘
		// 베팅금 입력
		// 만원단위로 최소 5만원 이상 입력해야한다.
		int mslot = 0;
		int mdto = dto.getMoney();
		int flag = 0;

		while (true) {
			while (true) {
				System.out.print("베팅 금액(최소금액 5만원)(탈출은 -1) : ");

				while (!sc.hasNextInt() || (mslot = sc.nextInt()) < -1) {
					// ViewMethod.clearScreen();
					System.out.println("정수를 입력해주세요.");
					System.out.print("베팅 금액(최소금액 5만원)(탈출은 -1) : ");
					sc = new Scanner(System.in);
				}

				// -1을 입력하면 바로 게임 종료
				if (mslot == -1) {
					flag = 1;
					break;
				} else if (mslot < 5) {
					// 10만원의 최소금액
					System.out.println("최소금액 이상을 넣어주세요.");
				} else if (mdto >= mslot) {
					// 10만원 이상을 베팅하면 게임 시작
					System.out.println(dto.getNickname() + "님께서 " + mslot + "만원을 베팅하셨습니다.");
					break;
				} else {
					// mdto < mrul의 상황
					// 잔액 부족
					System.out.println("잔액이 부족합니다.");
					flag = 1;
					break;
				}
			}

			sc.nextLine();

			if (flag == 1) {
				break;
			}

			ViewMethod vm = new ViewMethod();
			ViewData vd = new ViewData();
			
			// 3개의 슬롯에서 숫자를 결정한다.
			int[] slotNumber = {10, 10, 10};
			for (int index = 0; index < slotNumber.length; index++) {
				while (true) {
					// 화면에 슬롯머신을 출력하기 위한 함수 
					vm.printSlot(vd.nlist.get(slotNumber[0]), vd.nlist.get(slotNumber[1]), vd.nlist.get(slotNumber[2]));
					
					System.out.print(index + 1 + "번 슬롯을 돌리시겠습니까?(y/n) >> ");
					String yn = sc.nextLine();
					if (yn.equals("Y") || yn.equals("y")) {
						// 룰렛 돌아가서 숫가자 정해짐
						cm = conm.start(13);
						slotNumber[index] = rd.nextInt(10);

						break;
					}
				}

//				System.out.print("slot : ");
//				for (int i = 0; i <= index; i++) {
//					System.out.print(slotNumber[i] + " ");
//				}
//				System.out.println();
			}
			
			
			vm.printSlot(vd.nlist.get(slotNumber[0]), vd.nlist.get(slotNumber[1]), vd.nlist.get(slotNumber[2]));
			
			// slotNumber[3]에는 랜덤 숫자가 입력되어 있어요.
			// index 0, 1, 2의 숫자가 모두 같으면? 담첨. 7.7.7이면 잭팟
			// 3개면 당첨. 2개 이상이면 배팅금 회수. 1개 이하면 꽝
			// 잭팟이면 20배, 당첨이면 10배

			// 유저가 가지고 있는 돈 mdto
			// 베팅금 mslot
			// 슬롯머신 결과에 따라 mdto의 값이 달라진다.

			if (slotNumber[0] == slotNumber[1] && slotNumber[0] == slotNumber[2]) {
				// 당첨인경우
				if (slotNumber[0] == 7) {
					// 잭팟인 경우
					// 배팅금도 회수하고 배팅금의 20배의 돈을 가질 수 있음
					// dto.money *= 21;
					mdto += (mslot * 20);
					cm = conm.start(4);

				} else {
					// 당첨인데 잭팟은 아님
					// 배팅금도 회수하고 배팅금의 10배의 돈을 가질 수 있음
					// dto.money *= 11;
					mdto += (mslot * 10);
					cm = conm.start(4);
				}

				cm = conm.start(12);
			} else if (slotNumber[0] == slotNumber[1] || slotNumber[0] == slotNumber[2]
					|| slotNumber[1] == slotNumber[2]) {
				// 3개의 숫자 중에서 3개 모두가 같은 경우를 제외하고
				// 2개의 숫자만 같은 경우
				// 배팅금만 회수한다.
				// 아무것도 실행 안함
				mdto += 0;
				cm = conm.start(19);
			} else {
				// 3개의 숫자 중에서 같은 숫자가 하나도 없는 경우. 꽝인 경우
				// 배팅금을 잃는다.
				// dto.meney -= slot
				mdto -= mslot;
				cm = conm.start(15);
			}
			dto.setMoney(mdto);
			dao.updateMoney(dto);

			System.out.println(dto.getNickname() + "님의 잔액은 " + dto.getMoney() + "만원입니다.");
//
//			// 바로 끝내지 않고 결과를 확인할 시간이 필요
//			try {
//	            // Sleep for 3 seconds (3000 milliseconds)
//	            Thread.sleep(10000);
//	        } catch (InterruptedException e) {
//	            // Handle the InterruptedException
//	            e.printStackTrace();
//	        }
			
			
			break;

		}
	}
}
