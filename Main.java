import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> prime;
    static boolean[] check;
    public static void main(String[] args) {
        prime = new ArrayList<>();
        findPrime();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 테스트 케이스의 개수
        for (int i=0;i<n;i++) {
            int even = sc.nextInt(); // 짝수 입력 받기
            findPartitionNum(even);
        }
    }

    // 먼저 2 이상 1000000 이하의 소수들을 구한다. (입력 범위 내의 소수)
    // 소수들을 구하기 위해서는 에라토스체네스의 체를 사용한다.
    // 약 0.01초보다 덜 걸림
    public static void findPrime() {
        check = new boolean[1000001]; // 에라토스체네스의 체 구현 (true면 지워진 것, false면 지워지지 않은 것)
        for (int i=2;i<=1000000;i++) {
            if (!check[i]) { // 지워지지 않은 가장 작은 값을 구하기
                prime.add(i); // 해당 수는 소수이다. (소수 리스트에 추가)
            }
            for (int j=i*2;j<=1000000;j+=i) {
                // i의 최대값이 1000000이므로 i*i가 int 범위를 넘어가기 때문에 i*2부터 시작
                // i가 백만 이하의 수라면 i*i가 int의 범위 이내이므로 i*i부터 시작
                // i*i부터 시작하는 이유는 i*i보다 작은 i의 배수들은 이미 i보다 작은 소수들의 배수에 의해 처리되었기 때문에
                // 따로 처리할 필요가 없기 때문이다.
                check[j] = true; // 소수의 배수들 지워주기
            }
        }
    }

    public static void findPartitionNum(int even) {
        int cnt = 0; // 골드바흐 파티션의 개수
        for (int x : prime) { // 범위 내 모든 소수들에 대해 확인
            if (x <= even/2) { // 두 소수의 합의 순서만 다른 것은 같은 파티션 취급하므로 짝수의 반보다 같거나 작은 소수들에 대해 판별
                if (!check[even-x]) cnt+=1; // 짝수에서 해당 소수를 뺀 값이 소수라면 파티션 개수 하나 증가
            } else break; // 짝수의 반보다 큰 값에 대해서는 처리할 필요 없음 (중복이거나 소수다 짝수보다 큰 경우임)
        }
        System.out.println(cnt); // 해당 짝수의 골드바흐 파티션의 개수 출력
    }
}
