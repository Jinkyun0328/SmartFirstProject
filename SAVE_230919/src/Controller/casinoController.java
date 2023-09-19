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
		list.add(new casinoMusic("게임보너스", comPath + "게임보너스.mp3"));
		list.add(new casinoMusic("게임스타트", comPath + "게임스타트.mp3"));
		list.add(new casinoMusic("게임오버음성", comPath + "게임오버음성.mp3"));
		list.add(new casinoMusic("당첨", comPath + "당첨.mp3"));
		list.add(new casinoMusic("돈땄을때", comPath + "돈땄을때.mp3"));
		list.add(new casinoMusic("룰렛", comPath + "룰렛.mp3"));
		list.add(new casinoMusic("슬롯머신", comPath + "슬롯머신.mp3"));
		list.add(new casinoMusic("실패방구", comPath + "실패방구.mp3"));
		list.add(new casinoMusic("실패방구2", comPath + "실패방구2.mp3"));
		list.add(new casinoMusic("애기비웃음", comPath + "애기비웃음.mp3"));
		list.add(new casinoMusic("자게임을시작하지", comPath + "자게임을시작하지.mp3"));
		list.add(new casinoMusic("코인와르륵성공", comPath + "코인와르륵성공.mp3"));

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
