package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.casinoMusic;
import javazoom.jl.player.MP3Player;

public class casinoController {

	// Controller Class
	// mp3 file path
	String comPath = "C:\\Users\\smhrd\\Desktop\\GitHubDesktop\\SmartFirstProject\\project0\\src\\player\\";
	Scanner sc = new Scanner(System.in);
	int index = 0;

	MP3Player mp3 = new MP3Player();

	ArrayList<casinoMusic> list = new ArrayList<casinoMusic>();

	public casinoController() {

		// 음악 전체 목록
		list.add(new casinoMusic("게임보너스", comPath + "게임보너스.mp3"));					// 1
		list.add(new casinoMusic("게임스타트", comPath + "게임스타트.mp3"));					// 2
		list.add(new casinoMusic("게임오버음성", comPath + "게임오버음성.mp3"));				// 3
		list.add(new casinoMusic("당첨", comPath + "당첨.mp3"));							// 4
		list.add(new casinoMusic("돈땄을때", comPath + "돈땄을때.mp3"));						// 5
		list.add(new casinoMusic("룰렛", comPath + "룰렛.mp3"));							// 6
		list.add(new casinoMusic("슬롯머신", comPath + "슬롯머신.mp3"));						// 7
		list.add(new casinoMusic("실패방구", comPath + "실패방구.mp3"));						// 8
		list.add(new casinoMusic("실패방구2", comPath + "실패방구2.mp3"));					// 9
		list.add(new casinoMusic("애기비웃음", comPath + "애기비웃음.mp3"));					// 10
		list.add(new casinoMusic("자게임을시작하지", comPath + "자게임을시작하지.mp3"));			// 11
		list.add(new casinoMusic("코인와르륵성공", comPath + "코인와르륵성공.mp3"));				// 12
		list.add(new casinoMusic("슬롯머신돌아가는소리", comPath + "슬롯머신돌아가는소리.mp3"));		// 13
		list.add(new casinoMusic("게임스타트최최종", comPath + "게임스타트최최종.mp3"));			// 14
		list.add(new casinoMusic("못맞췄지롱", comPath + "못맞췄지롱.mp3"));					// 15
		list.add(new casinoMusic("카드착착", comPath + "카드착착.mp3"));						// 16
		list.add(new casinoMusic("휴어딨지", comPath + "휴어딨지.mp3"));						// 17
		list.add(new casinoMusic("휴어딨지2", comPath + "휴어딨지2.mp3"));					// 18
		list.add(new casinoMusic("휴우", comPath + "휴우.mp3"));							// 19
	}

	// 1. 노래 정지 기능 메소드
	public boolean stop() {
		boolean result = false;
		if (mp3.isPlaying()) {
			mp3.stop();
			result = true;
		}

		return result;
	}

	// 2. 노래 재생 기능 메소드
	public casinoMusic start(int songN) {
		index = songN - 1;
		mp3.play(list.get(index).getPath());
		return list.get(index);
	}

}
