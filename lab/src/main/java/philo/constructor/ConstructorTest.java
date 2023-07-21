package philo.constructor;

class Parent {
	int x = 100;

	Parent() {
		this(500);
		System.out.println("Parent: 기본생성자 호출 end");
	}

	Parent(int x) {
		System.out.println("Parent: 인자있는 생성자 호출 start");
		this.x = x;
	}

	int getX() {
		return x;
	}
}

class Child extends Parent {
	int x = 4000;

	Child() {
		this(5000);
		System.out.println("Child: 기본생성자 호출 end");
	}

	Child(int x) {
		System.out.println("Child: 인자 있는 생성자 호출 start");
		this.x = x;
	}

	int getX() {
		return x;
	}
}

public class ConstructorTest {

	public static void main(String[] args) {
		Child obj = new Child();
		System.out.println(obj.getX());
	}
}
