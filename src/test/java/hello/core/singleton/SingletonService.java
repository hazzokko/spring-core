package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 내부에 private으로 하나 가지고 있는데, static으로 가지고 있는 것
    // 클래스 레벨에 올라가기 때문에 딱 하나만 존재하게 된다.
    // JVM, 자바가 뜰 때, SingletonService static에 있는 instance 변수를 초기화 하면서 new SingletonService()로 되어있으면
    // 내부적으로 실행해서 자기 자신의 객체 인스턴스를 생성해서 instance에 참조를 넣는다.

    // 1. static 영역에 객체를 딱 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {
    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
