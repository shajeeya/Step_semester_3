# Step_semester_3

## Date: 01-08-2026

**Today's Work:**
- Completed Session 1 on Java Strings.
- Learned the fundamentals and characteristics of Java Strings, including immutability, reference types, and the String pool.
- Studied different ways of creating Strings, including String literals and the `new` keyword.
- Learned about String constructor overloads and compile-time vs runtime String creation.
- Practiced String concatenation and learned why `StringBuilder` is useful for multiple concatenations.
- Studied escape sequences and character representation using ASCII, Unicode, and UTF-16.
- Learned String input handling using `Scanner` and `BufferedReader`, including token-based and line-based input.
- Studied String arrays, array initialization, references, sorting, searching, and conversion to string representation.
- Learned how Strings are passed as method parameters in Java and reviewed pass-by-value behavior and String immutability.
- Introduction to Java exception handling, including the `Throwable`, `Error`, and `Exception` hierarchy.

**Next Session Plan:**
- Continue with the concepts covered in the next STEP session.
- Practice and implement the concepts learned through Java programs and assignments.

**Issues Faced:**
- None


---

## Date: 08-08-2026

**Today's Work:**
- Completed Session 2 on Java Strings and String handling.
- Studied String operations, immutability, String Pool, and the performance implications of String manipulation.
- Learned the difference between `==` and `.equals()` when comparing Strings.
- Studied built-in String methods such as `length()`, `charAt()`, `substring()`, and `indexOf()`.
- Practiced commonly used methods including `contains()`, `toUpperCase()`, `toLowerCase()`, `equalsIgnoreCase()`, `isEmpty()`, `isBlank()`, `startsWith()`, and `endsWith()`.
- Learned String manipulation methods such as `trim()`, `replace()`, `split()`, `concat()`, `String.join()`, and `toCharArray()`.
- Studied ASCII character codes and learned character-to-ASCII and ASCII-to-character conversion using type casting.
- Learned about the relationship between uppercase and lowercase ASCII values and the use of the `Character` class for character operations.
- Studied mutable String handling using `StringBuilder` and `StringBuffer`.
- Understood the differences between `String`, `StringBuilder`, and `StringBuffer` in terms of mutability, thread safety, speed, and use cases.
- Learned why repeated String concatenation using `+` or `+=` inside loops can be inefficient.
- Practiced using `StringBuilder` for efficient text construction and understood when `StringBuffer` is appropriate.
- Reviewed String performance optimization and learned to prefer `StringBuilder` when building text through repeated operations or loops.

**Next Session Plan:**
- Continue with the concepts covered in the next STEP session.
- Practice and implement the String concepts learned through Java programs and assignments.

**Issues Faced:**
- None


---

## Date: 22-08-2026

**Today's Work:**
- Completed Session 3 on Object-Oriented Programming (OOP), Classes, and Objects.
- Learned the purpose of OOP and how it bundles data with the behaviour that acts on it.
- Understood the benefits of OOP, including reusability, security through encapsulation, and extensibility.
- Learned the difference between a class and an object using the blueprint/floor-plan concept.
- Studied class structure consisting of fields, constructors, and methods.
- Learned how constructors initialize objects, including constructor naming rules and the absence of a return type.
- Understood the `this` keyword and its use when constructor or method parameters have the same names as instance fields.
- Studied Java access modifiers: `public`, `private`, `protected`, and default access.
- Learned object creation using the `new` keyword and understood the difference between an object and its reference variable.
- Studied reference assignment, object identity, `==`, `null`, `NullPointerException`, and garbage collection.
- Learned the difference between instance members and `static` members and when each should be used.
- Understood why static methods cannot directly access instance fields or `this`.
- Practiced Java examples involving students, fee accounts, hostel rooms, courses, ID cards, and static members.

**Next Session Plan:**
- Continue with the concepts covered in the next STEP session.
- Practice and implement OOP, classes, objects, constructors, and static member concepts through Java programs and assignments.

**Issues Faced:**
- None


---

## Date: 29-08-2026

**Today's Work:**
- Completed Session 4 on Access Modifiers, Encapsulation, JavaBeans, and Object Modeling.
- Studied the four Java access modifiers: `private`, default (package-private), `protected`, and `public`.
- Learned the visibility rules of access modifiers within the same class, same package, subclasses, and different packages.
- Understood how `protected` members can be accessed by subclasses in different packages through inheritance.
- Studied encapsulation and data hiding using private fields and controlled public methods.
- Learned how encapsulation prevents invalid object state by enforcing rules through methods.
- Studied the JavaBean standard and naming conventions for getters and setters.
- Learned that normal properties use `getX()` and `setX()`, while boolean properties use `isX()`.
- Understood the importance of a public no-argument constructor in a JavaBean.
- Studied read-only and write-only properties using getter-only and setter-only designs.
- Learned the concept of immutable objects and how `final` fields and the absence of setters help maintain immutability.
- Understood that modifying an immutable object means creating and returning a new object instead of changing the existing object.
- Studied the difference between `final` references and the mutability of the objects they reference.
- Learned defensive copying using `clone()` to protect mutable arrays from external modification.
- Understood how access modifiers, encapsulation, JavaBeans, immutability, and defensive copying work together in object modeling.
- Practiced Java examples involving student profiles, fee accounts, enrollment records, and course rosters.

**Next Session Plan:**
- Continue with the concepts covered in the next STEP session.
- Practice and implement access modifiers, encapsulation, JavaBeans, immutability, and object modeling through Java programs and assignments.

**Issues Faced:**
- None


---

## Date: 05-09-2026

**Today's Work:**
- Completed Session 5 on Inheritance and Polymorphism.
- Learned the concept of inheritance and how it represents an "is-a" relationship between a parent class and a subclass.
- Studied Single Inheritance, where one class extends exactly one direct parent class.
- Learned how inheritance promotes code reuse by allowing subclasses to use inherited fields and methods without duplicating the same implementation.
- Studied the use of the `extends` keyword to establish an inheritance relationship between classes.
- Learned how the `super()` constructor call passes the shared part of object construction to the parent class.
- Understood that `super(...)` must be the first statement inside a subclass constructor.
- Learned that private fields of a parent class cannot be accessed directly by a subclass and that inherited protected or public members can be used when appropriate.
- Studied Multilevel Inheritance, where classes form a chain of inheritance such as parent, child, and further specialized subclasses.
- Studied Hierarchical Inheritance, where multiple independent subclasses extend the same parent class.
- Learned that Java supports Single, Multilevel, and Hierarchical Inheritance for classes but does not support multiple inheritance of classes.
- Studied Constructor Chaining and understood that constructors execute from the topmost parent class down to the child class.
- Learned that Java automatically inserts `super()` when a subclass constructor does not explicitly call a parent constructor, provided an accessible no-argument parent constructor exists.
- Understood that a subclass cannot rely on an implicit `super()` call when the parent class has only parameterized constructors.
- Learned Method Overriding, where a subclass redefines an inherited method using the same method signature.
- Studied the `@Override` annotation and understood how it helps the compiler detect mistakes in intended method overriding.
- Learned how `super.method()` can be used inside an overridden method to reuse the parent's implementation while extending its behaviour.
- Studied the concept of Polymorphism and understood how the same method call can produce different behaviour depending on the actual object involved.
- Learned Runtime Polymorphism through Dynamic Method Dispatch, where Java selects the overridden method based on the actual object at runtime rather than only the declared reference type.
- Studied Method Overloading as Compile-Time or Static Polymorphism, where multiple methods in the same class have the same name but different parameter lists.
- Learned that method overloading can differ by parameter type, number, or order, but return type alone cannot distinguish overloaded methods.
- Understood the difference between Method Overloading and Method Overriding, including their requirements and when each is resolved.
- Studied Upcasting, where a subclass object is treated through a reference of its parent type.
- Learned that upcasting is implicit and safe because a subclass object is also an instance of its parent class.
- Studied Downcasting, where a parent-type reference is explicitly converted back to a more specific subclass type.
- Learned that downcasting should be checked using `instanceof` before performing the cast to avoid `ClassCastException`.
- Understood that a parameter mismatch in an intended override can create a new overloaded method instead of overriding the parent method.
- Practiced Java examples involving event tickets, workshop tickets, premium workshop tickets, and hackathon tickets to understand inheritance, constructor chaining, overriding, polymorphism, upcasting, and downcasting.

**Next Session Plan:**
- Continue with the concepts covered in the next STEP session.
- Practice and implement inheritance, polymorphism, method overriding, method overloading, constructor chaining, upcasting, and downcasting through Java programs and assignments.

**Issues Faced:**
- None


---

## Date: 12-09-2026

**Today's Work:**
- Studied the concept of Abstraction and understood how abstract classes can contain both concrete methods and abstract methods.
- Learned that an abstract class cannot be instantiated directly and that concrete subclasses must implement all inherited abstract methods.
- Studied Abstract Methods and understood that they have no method body and must be implemented by concrete subclasses.
- Learned about Interfaces as contracts that define capabilities which implementing classes must provide.
- Understood that a class can extend only one class but can implement multiple interfaces.
- Studied the difference between an Abstract Class and an Interface, including state, constructors, methods, and inheritance.
- Learned the difference between IS-A and CAN-DO relationships, where `extends` represents IS-A and `implements` represents CAN-DO.
- Understood when to use a plain class, abstract class, or interface based on the design requirements.
- Studied the smart home device examples involving `Device`, `Remoteable`, `Schedulable`, `EnergyMonitorable`, `SmartLight`, `SmartThermostat`, `BasicLamp`, and `SmartDoorLock`.

**Next Session Plan:**
- Continue with the concepts covered in the next STEP session.
- Practice and implement abstract classes, abstract methods, interfaces, multiple interfaces, IS-A, CAN-DO, and abstract class vs interface concepts.

**Issues Faced:**
- None