package philo.switch_case;

public class SwitchCaseJava17 {

  enum AType {
    A1, A2
  }

  enum BType {
    B1, B2
  }

  public static void main(String[] args) {
    AType aType = AType.A1;
    BType bType = BType.B2;

//    case_1(aType, bType);
    String case_2_result = case_2(aType, bType);
    System.out.println("case_2_result = " + case_2_result);
  }

  private static void case_1(AType aType, BType bType) {
    switch (aType) {
      case A1 -> {
        System.out.println("AType is A1");
        switch (bType) {
          case B1 -> System.out.println("BType is B1");
          case B2 -> System.out.println("BType is B2");
        }
      }
      case A2 -> {
        System.out.println("AType is A2");
        switch (bType) {
          case B1 -> System.out.println("BType is B1");
          case B2 -> System.out.println("BType is B2");
        }
      }
    }
  }

  private static String case_2(AType aType, BType bType) {
    return switch (aType) {
      case A1 -> {
        var aStr = "AType is A1, ";
        yield switch (bType) {
          case B1 -> aStr + "BType is B1";
          case B2 -> aStr + "BType is B2";
        };
      }
      case A2 -> {
        var aStr = "AType is A2, ";
        yield switch (bType) {
          case B1 -> aStr + "BType is B1";
          case B2 -> aStr + "BType is B2";
        };
      }
    };
  }
}
