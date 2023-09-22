package View;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import Controller.casinoController;
import Model.HoguDAO;
import Model.HoguDTO;
import Model.casinoMusic;

public class Main {
	// main 함수를 가지고 있는 클래스
	// GameMenu 화면 출력
	// [1]회원가입 [2]로그인 [3]회원탈퇴 [4]게임종료
	public Main() {
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
		
		ViewMethod vm = new ViewMethod();

		// 시작할 때 메인 화면에 조커를 출력
		sprintMain();
		
		conm.start(2);
		vm.sleep(8000);
		
		while (true) {
			// 4번을 눌러서 break가 실행되면 반복문이 종료된다.

			// 음악파일 등의 첨부파일에 오류가 있을 경우 안내메시지를 출력하고 프로그램을 종료한다.
			if(!conm.pathTest()) {
				try {
					System.out.println("\n음악파일의 경로를 확인해주세요.");
					System.out.println("\n음악파일을 exe파일이 있는 폴더 내에 player 폴더 안에 넣어주세요.");
					Thread.sleep(3000);
					System.out.println("\n잠시 후 프로그램이 종료됩니다.");
					Thread.sleep(3000);
					break;
		        } catch (InterruptedException e) {
		            // Handle interrupted exception if needed
		            e.printStackTrace();
		        }
			}
			
			int input = 0;
			
			System.out.println("====메인화면====");

			// 메뉴 입력
			System.out.print("\n[1]회원가입 [2]로그인 [3]회원탈퇴 [4]게임종료 >> ");

			// scanner에 정수가 아닌 다른 자료형을 입력할 경우 예외처리를 하지 않으면 컴파일러가 프로그램을 종료한다.
			// 프로그램을 종료하지 않고 다시 정수를 입력받을 수 있게 하는 코드
			// sc.nextLine()은 int형을 입력받고 메모리를 비우기 위해 추가했다.
			while (!sc.hasNextInt() || (input = sc.nextInt()) < 0) {
				// ViewMethod.clearScreen();
				System.out.println("\n정수를 입력해주세요.");
				System.out.print("\n[1]회원가입 [2]로그인 [3]회원탈퇴 [4]게임종료 >> ");
				sc = new Scanner(System.in);
			}
			sc.nextLine();

			// 입력값에 따른 클래스 호출
			if (input == 1) {
				// 회원가입 클래스로 이동
				new CasinoJoin();

			} else if (input == 2) {
				// 로그인 클래스로 이동
				new CasinoLogIn();

			} else if (input == 3) {
				// 회원탈퇴 클래스로 이동
				new CasinoWithdraw();

			} else if (input == 4) {
				// 게임종료
				System.out.println("\n게임을 종료합니다.");
				
				vm.sleep(3000);
				
				break;

			} else {
				System.out.println("\n잘못된 숫자를 입력하셨습니다.");
			}
			
			ViewData vd = new ViewData();
			vm.clearScreen();
		}
	}
	
	public void sprintMain() {
		System.out.println("\t\t\t  _    _  _____  _      _____  _____ ___  ___ _____             _____  _    _   ___   _   _  _____    ___  _   _              _____   ___   _____  _____  _   _  _____ ");                                                                                                                                                       
        System.out.println("\t\t\t | |  | ||  ___|| |    /  __ \\|  _  ||  \\/  ||  ___|           |  __ \\| |  | | / _ \\ | \\ | ||  __ \\  |_  || | | |            /  __ \\ / _ \\ /  ___||_   _|| \\ | ||  _  |");                                                                                                                                                       
        System.out.println("\t\t\t | |  | || |__  | |    | /  \\/| | | || .  . || |__             | |  \\/| |  | |/ /_\\ \\|  \\| || |  \\/    | || | | |            | /  \\// /_\\ \\\\ `--.   | |  |  \\| || | | |");                                                                                                                                                       
        System.out.println("\t\t\t | |/\\| ||  __| | |    | |    | | | || |\\/| ||  __|            | | __ | |/\\| ||  _  || . ` || | __     | || | | |            | |    |  _  | `--. \\  | |  | . ` || | | |");                                                                                                                                                       
        System.out.println("\t\t\t \\  /\\  /| |___ | |____| \\__/\\\\ \\_/ /| |  | || |___            | |_\\ \\\\  /\\  /| | | || |\\  || |_\\ \\/\\__/ /| |_| |            | \\__/\\| | | |/\\__/ / _| |_ | |\\  |\\ \\_/ /");                                                                                                                                                       
        System.out.println("\t\t\t  \\/  \\/ \\____/ \\_____/ \\____/ \\___/ \\_|  |_/\\____/             \\____/ \\/  \\/ \\_| |_/\\_| \\_/ \\____/\\____/  \\___/              \\____/\\_| |_/\\____/  \\___/ \\_| \\_/ \\___/ ");
        
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("              .");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("           ,: ~.");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("           -   ;~");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("          .*=-.  ...");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("           *~~$,.  ..");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("          ,=*:.,,..*.                       ,");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("   $~    =$@=$~~,-.:    -*,,,,.             .");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("   ..~.,$@@@@#$=   *!,       .~;;;!,  . . .,*");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("   -..-!#@@@@@@#~ .;:~.             .~;~..;.=-");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("     -=#@@@@@@@@$;,!!~.-             .   :#~!$- .");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("   , =@@#@@@@@@@@#$##. *          .,-  ,;@=@!$;,.");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("    .*$#@@@@@@@@@@@@#*~             .,~,*@@$=#=.");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("  ,*!*=@@@@@@@@@@@@@@@-~        .  -,=@$@@@@@$;-");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("    ,$##@@@@@@@@@@@@$@*. =~   : ~,~$#@@@@@@@@#$");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("    .=##!#@;-,;@@$@@##.,==-  ,::!*=#$@@@@@@@@##!");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("     !##.,$*   :$$-~@@! :.. -. $=#@@@@@@@@@@@$#-");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("    . ... -#;.    .:#@@!;~. :;*!@@@@@@@@@@@@$#*");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("        ~=;$@!~!-.=@#@=@,   ~=$#@@; !@@@#@@@@@,~");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("  .  ,.  :!#@@@@@@@@@@;#:   $#@@#:   ~#:.=@@@#$.");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("     ~ .--.*=#@$@@@@.=..    @@@@##:     ,$@@@*");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("   . .  *-.-==@@@@@, -      =$@@@@@#***=@@@@@@:~");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("   .    ., :!#$###-  ~.     -#@@@@@@@@@@@@@@=$#=");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("  ~       :*=!:#!;   .     , =,@@@@@@@@@@@@=!:=;");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("   ,.    :.  - ; !         . - **#@@@@@@@@@!!,*");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("    ;,   ..      *          .  ,,**@@@**@#*;~ -");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("    -;           !            .  , .~,-:!;-  .");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("     !,        -,~              .. ..,,-  .- -");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("     ~!         :.             ,-          .~,");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("     .*        :;!.          .,*~           ,");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("      --        ;            #$#.          ,");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("      .:,                    :##*-        .");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("       !;:,                   ,#~.       !");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("       .*.        ,~          -, .:      .");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("        $~          .       .-,   .!-   ,");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("        $!           .     .~        .,=!");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("         ::!:            .,         *$#");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("         .$##=-                 ,  ;$$,");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("          ~=@@#;..-,           ..;!$$=-");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("            ;@$=$$##=;:,       :###$$:");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("             .===*=##=*===*=-:=*$=;!.");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("            :;$$=**!**=#$=*=$, ,~");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("              .=$=****;!!!=:   .");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("                ,;~$$$**=*,");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("                 ,  -~-**");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("                 ,  ., *$");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("                 .  ., **");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("                  . ,. =*");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("                  -    ;*");
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t");
        System.out.println("                     - ,-");
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
	}

	public static void main(String[] args) {
		new Main();
	}
}
