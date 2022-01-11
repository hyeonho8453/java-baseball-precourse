package baseball;

import nextstep.utils.Randoms;
import nextstep.utils.Console;

public class Application {

    // 랜덤 정답 생성 함수 (중복 X)
    public static String RandomAnswerGenerator(){
        String ans1, ans2, ans3; // 3자리 정답 숫자

        ans1 = String.valueOf(Randoms.pickNumberInRange(1, 9));

        while(true){        // 첫번째 숫자와 중복 체크
            ans2 = String.valueOf(Randoms.pickNumberInRange(1, 9));
            if (ans2.equals(ans1)){
                continue;
            } else{
                break;
            }
        }

        while(true){        // 첫번째,두번째 숫자와 중복 체크
            ans3 = String.valueOf(Randoms.pickNumberInRange(1, 9));
            if ((ans3.equals(ans1)) || (ans3.equals(ans2))){
                continue;
            } else{
                break;
            }
        }

        String ans = ans1 + ans2 + ans3;    // 하나의 문자열로 결합
        return ans;
    }


    // 사용자 입력값
    public static String num_input() {
        String num_input;
        while (true) {
            System.out.print("숫자를 입력해 주세요 : ");
            num_input = Console.readLine();
            if (num_input.length() != 3) {      // 0~9 사이 정수 3개여야 함
                System.out.println("입력값은 3자리의 정수여야 합니다.");
                continue;
            } else {    //  옳은 입력값
                break;
            }
        }
        return num_input;
    }

    // 스트라이크 계산
    public static int get_strike(String ans, String num_input){         // 메개변수로 정답과 입력값 문자열 -> 한 숫자씩 쪼개서 확인

        int strike = 0;

        // 정답과 입력값을 한자리씩 분리
        String ans1s = Character.toString(ans.charAt(0));
        String ans2s = Character.toString(ans.charAt(1));
        String ans3s = Character.toString(ans.charAt(2));

        String num_input_1 = Character.toString(num_input.charAt(0));
        String num_input_2 = Character.toString(num_input.charAt(1));
        String num_input_3 = Character.toString(num_input.charAt(2));

        // strike 계산
        if (ans1s.equals(num_input_1)){             // 자리와 숫자가 일치하면 스트라이크 +1
            strike++;
        }
        if (ans2s.equals(num_input_2)){
            strike++;
        }
        if (ans3s.equals(num_input_3)){
            strike++;
        }

        return strike;
    }

    // Ball 계산
    public static int get_ball(String ans, String num_input){         // 메개변수로 정답과 입력값 문자열 -> 한 숫자씩 쪼개서 확인

        int ball = 0;

        // 정답과 입력값을 한자리씩 분리
        String ans1s = Character.toString(ans.charAt(0));
        String ans2s = Character.toString(ans.charAt(1));
        String ans3s = Character.toString(ans.charAt(2));

        String num_input_1 = Character.toString(num_input.charAt(0));
        String num_input_2 = Character.toString(num_input.charAt(1));
        String num_input_3 = Character.toString(num_input.charAt(2));

        // ball 계산
        if ((!ans1s.equals(num_input_1)) && (ans.contains(num_input_1))){       // 자리는 일치하지 않지만 포함은 되는 경우
            ball++;
        }
        if ((!ans2s.equals(num_input_2)) && (ans.contains(num_input_2))){       // 자리는 일치하지 않지만 포함은 되는 경우
            ball++;
        }
        if ((!ans3s.equals(num_input_3)) && (ans.contains(num_input_3))){       // 자리는 일치하지 않지만 포함은 되는 경우
            ball++;
        }

        return ball;
    }

    // 결과 출력
    public static boolean ResultPrint(int strike, int ball){    // strike와 ball을 통해서 출력할 문장 생성
        boolean ans_loop = true;

        if (strike == 3){
            ans_loop = false; // 3스트라이크 -> 정답. 정답 입력 while문 탈출을 위해 result = false
        }

        if ((strike == 0) && (ball == 0)){
            System.out.println("낫싱");
        } else if ((strike > 0) && (ball > 0)) {
            System.out.println(strike + "스트라이크 " + ball + "볼");
        } else if(ball == 0){
            System.out.println(strike + "스트라이크");
        } else{
            System.out.println(ball + "볼");
        }

        return ans_loop;
    }

    public static void main(String[] args) {
        // TODO 숫자 야구 게임 구현
        while(true) {
            String ans = RandomAnswerGenerator();
            // System.out.println(ans);

            // 입력값 비교
            boolean ans_loop = true;
            while (ans_loop) {
                // 값 입력
                String num_input = num_input();

                // 스트라이크 계산
                int strike = get_strike(ans, num_input);
                // 볼 계산
                int ball = get_ball(ans, num_input);
                // 결과 출력
                ans_loop =  ResultPrint(strike, ball); //  정답을 맞추면 false -> while문 탈출
            }

            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            int continue_finish = Integer.parseInt(Console.readLine());

            if (continue_finish == 1){
                continue;
            } else{
                System.out.println("게임 종료");
                break;
            }

        }

    }

}

