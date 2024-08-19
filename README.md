***
#Fields

필드는 타입과 값을 가집니다. java.lang.reflect.Field 클래스는 주어진 객체의 필드에 대한 타입 정보를 접근하고 값을 설정하고 가져오는 메서드를 제공합니다.
Obtaining Field Types 에서는 필드의 선언된 타입과 제네릭 타입을 얻는 방법을 설명합니다.
Retrieving and Parsing Field Modifiers 에서는 public이나 transient와 같은 필드 선언의 일부를 얻는 방법을 보여줍니다.
Getting and Setting Field Values 에서는 필드 값을 접근하는 방법을 설명합니다.
Troubleshooting 에서는 혼란을 일으킬 수 있는 일반적인 코딩 오류를 설명합니다.
***
#Methods

메서드는 리턴 값, 파라미터를 가지며 예외를 던질 수 있습니다. java.lang.reflect.Method 클래스는 파라미터와 리턴 값의 타입 정보를 얻기 위한 메서드를 제공합니다. 또한 주어진 객체에서 메서드를 호출하는 데도 사용될 수 있습니다.
Obtaining Method Type Information 에서는 클래스에 선언된 메서드를 열거하고 타입 정보를 얻는 방법을 설명합니다.
Obtaining Names of Method Parameters 에서는 메서드 또는 생성자의 파라미터 이름 및 기타 정보를 검색하는 방법을 보여줍니다.
Retrieving and Parsing Method Modifiers 에서는 메서드와 관련된 제어자 및 기타 정보를 접근하고 해석하는 방법을 설명합니다.
Invoking Methods 에서는 메서드를 실행하고 리턴 값을 얻는 방법을 설명합니다.
Troubleshooting 에서는 메서드를 찾거나 호출할 때 발생하는 일반적인 오류를 다룹니다.
***
#Constructors

리플렉션 API에서 생성자는 java.lang.reflect.Constructor에 정의되어 있으며, 메서드에 대한 API와 유사합니다. 그러나 두 가지 주요 예외가 있습니다. 첫째, 생성자는 리턴 값이 없습니다. 둘째, 생성자를 호출하면 주어진 클래스의 새 인스턴스가 생성됩니다.
Finding Constructors 에서는 특정 파라미를 가진 생성자를 검색하는 방법을 설명합니다.
Retrieving and Parsing Constructor Modifiers 에서는 생성자 선언의 제어자 및 기타 정보를 얻는 방법을 보여줍니다.
Creating New Class Instances 에서는 생성자를 호출하여 객체의 인스턴스를 생성하는 방법을 설명합니다.
Troubleshooting 에서는 생성자를 찾거나 호출할 때 발생할 수 있는 일반적인 오류를 설명합니다.
***
