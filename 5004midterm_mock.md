
***

## CS 5004 Object Oriented Design - Midterm (Mock)
**Semester:** Spring 2026  
**Date:** Tuesday, February 24th, 2026

---

### Page 2: Question #1

**Question:**
Create a `Student` class that has instance variables for the student’s last name and ID number, along with appropriate constructors, accessors, and mutators. Make the `Student` class implement the `Comparable` interface. Define the `compareTo` method to order `Student` objects based on the student ID number. In the `main` method, create an array of at least five `Student` objects, sort them using `Arrays.sort`, and output the students. They should be listed by ascending student number.

Next, modify the `compareTo` method so it orders `Student` objects based on the lexicographic ordering of their last name. Without modification to the `main` method, the program should now output the students ordered by name.

Remember: The `Comparable` interface is in the `java.lang` package and so is automatically available to your program. The `Comparable` interface has only the following method heading that must be implemented for a class to implement the `Comparable` interface: `public int compareTo(Object other);`

**Answer (答案):**

```java
import java.util.Arrays;

// Class implements Comparable interface
// 类实现了 Comparable 接口
public class Student implements Comparable {
    
    // Instance variables
    // 实例变量
    private String lastName;
    private int id;

    // Parameterized Constructor
    // 带参构造函数
    public Student(String lastName, int id) {
        this.lastName = lastName;
        this.id = id;
    }
 
    // Default Constructor
    // 默认构造函数 (无参数，初始化默认值)
    public Student() {
        this.lastName = "";
        this.id = 0;
    }

    // Copy Constructor
    // 拷贝构造函数 (复制另一个对象的属性)
    public Student(Student other) {
        if (other != null) {
            this.lastName = other.lastName;
            this.id = other.id;
        }
    }

    // Accessors and Mutators
    // 访问器和修改器
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // toString for easy printing
    // 用于打印输出的 toString 方法
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + lastName;
    }

    // compareTo implementation
    // compareTo 方法实现
    public int compareTo(Object other) {
        // Check for null or type safety usually happens here, but following prompt signature
        Student otherStudent = (Student) other;

        // ---------------------------------------------------------
        // PART 1 CODE (Commented out for Part 2 as requested)
        // 第一部分代码 (为了实现第二部分的按名字排序，此处已注释)
        // ---------------------------------------------------------
        // Sort by ID ascending
        // 按 ID 升序排列
        //*****
        return this.id - otherStudent.id;
        
        // ---------------------------------------------------------
        // PART 2 CODE (Final Answer)
        // 第二部分代码 (最终答案)
        // ---------------------------------------------------------
        // Sort lexicographically by last name
        // 按姓氏的字典顺序排列
        // String's compareTo returns negative/zero/positive logic automatically
        // String 类的 compareTo 方法自动处理负数/零/正数的逻辑

        //*************
        //return this.lastName.compareTo(otherStudent.lastName);
    }

    public static void main(String[] args) {
        // Create an array of at least five Student objects
        // 创建一个包含至少5个学生对象的数组
        Student[] students = new Student[5];
        students[0] = new Student("Zuckerberg", 300);
        students[1] = new Student("Jobs", 101);
        students[2] = new Student("Gates", 500);
        students[3] = new Student("Musk", 205);
        students[4] = new Student("Lovelace", 100);

        // Sort them using Arrays.sort
        // 使用 Arrays.sort 进行排序
        Arrays.sort(students);

        // Output the students
        // 输出学生信息
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
```

---

### Page 4: Question #2

**Question:**
Given the code on pages 5, 6, and 7, Write a description of all classes:
1.  Identifying whether the class is abstract or concrete.
2.  Identifying all superclasses and subclasses of the class.
3.  Explaining all methods in the class: (Input, What they do, What they return).
4.  Explaining all the variables in the class: (Purpose, Access level).

**Answer (答案):**

$\color{red}{\text{1. Class Analysis (类的分析)}}$

*   **Movie**:
    *   **Type**: Concrete (具体类). *Note: It has no abstract methods and is not declared abstract.*
    *   **Superclass**: `Object` (implicit).
    *   **Subclasses**: `ActionMovie`, `ComedyMovie`, `DramaMovie`.
*   **ActionMovie**:
    *   **Type**: Concrete (具体类).
    *   **Superclass**: `Movie`.
    *   **Subclasses**: None (in this context).
*   **ComedyMovie**:
    *   **Type**: Concrete (具体类).
    *   **Superclass**: `Movie`.
*   **DramaMovie**:
    *   **Type**: Concrete (具体类).
    *   **Superclass**: `Movie`.

$\color{red}{\text{2. Methods Explanation (方法解释)}}$

*   **In Class `Movie`**:
    *   `Constructors`: Initialize `MovieID`, `title`, and `rating`.
    *   `getID`, `getTitle`, `getRating`:
        *   **Input**: None.
        *   **Do**: Return the value of the respective instance variable. (返回实例变量的值).
        *   **Return**: `int` (ID) or `String` (title/rating).
    *   `setID`, `setTitle`, `setRating`:
        *   **Input**: `int` or `String` value.
        *   **Do**: Update the instance variable with the new value. (更新实例变量).
        *   **Return**: `void`.
    *   `equals(Object other)`:
        *   **Input**: An object `other`.
        *   **Do**: Checks equality based on `MovieID`. It checks if `other` is null, if classes match, and finally if the IDs are identical. (基于 MovieID 检查对象是否相等).
        *   **Return**: `boolean` (true if IDs match, false otherwise).
    *   `calcLateFees(int daysLate)`:
        *   **Input**: `int daysLate` (number of days late).
        *   **Do**: Calculates fees using the base multiplier 2.0. (使用基础倍率 2.0 计算费用).
        *   **Return**: `double` (Calculation: $2.0 \times daysLate$).

*   **In Subclasses (`ActionMovie`, `ComedyMovie`)**:
    *   `calcLateFees(int daysLate)` (Overridden):
        *   **Input**: `int daysLate`.
        *   **Do**: Calculates fees using specific multipliers. (Action: 3.0, Comedy: 2.5). `DramaMovie` does not override, so it uses the base 2.0.
        *   **Return**: `double`.

$\color{red}{\text{3. Variables Explanation (变量解释)}}$

*   **Variables in `Movie`**: `MovieID`, `title`, `rating`.
    *   **Purpose**: To store the state/attributes of a movie object (ID number, Name, Age Rating). (存储电影的状态/属性).
    *   **Access Level**: `private`. They are encapsulated and accessed via public getters/setters. (私有，通过公共方法访问).

---

### Page 8: Question #3

**Question:**
Given the classes of Question #2 give what would be printed on the screen when we run the class `Question3Movie`.

**Answer (答案):**

<span style="color:red; font-family: monospace;">
If Kill Bill: Volume 2 is 2 days late the fee is 6.0<br>
If Kill Bill: Volume 2 is 3 days late the fee is 9.0<br>
If Mean Girls is 3 days late the fee is 7.5<br>
If Mystic River is 3 days late the fee is 6.0<br>
Is Kill Bill: Volume 2 equal Mystic River? false<br>
Is Mean Girls equal Mystic River? false<br>
Is Mystic River equal Mystic River, Second Copy? true
</span>

**Logic (逻辑解析):**
1.  **KillBill (ActionMovie):** Rate is 3.0.
    *   2 days: $3.0 \times 2 = 6.0$.
    *   3 days: $3.0 \times 3 = 9.0$.
2.  **MeanGirls (ComedyMovie):** Rate is 2.5.
    *   3 days: $2.5 \times 3 = 7.5$.
3.  **Mystic (DramaMovie):** Inherits `Movie` rate (2.0).
    *   3 days: $2.0 \times 3 = 6.0$.
4.  **Equals Check:**
    *   `Movie.equals` only compares `MovieID`.
    *   KillBill (ID 0) vs Mystic (ID 2) -> `false`.
    *   MeanGirls (ID 1) vs Mystic (ID 2) -> `false`.
    *   Mystic (ID 2) vs MysticCopy2 (ID 2) -> `true`.

---

### Page 10: Question #4

**Question:**
Given the classes of Question #2 and the output shown in Figure 4.1 when we run the class `Question4Rental`, write the code for the missing class(es).
(The output implies a calculation of late fees for specific movies).

**Answer (答案):**

```java
// The missing class is Rental
// 缺失的类是 Rental
public class Rental {
    // Instance variables to store the movie and rental details
    // 实例变量，用于存储电影和租赁详情
    private Movie movie;
    private int customerID; // The second parameter in constructor (not explicitly used in calc but present in call)
    private int daysLate;

    // Constructor based on usage: new Rental(killbill2, 1);
    // 根据用法编写构造函数
    public Rental(Movie movie, int customerID) {
        this.movie = movie;
        this.customerID = customerID;
        this.daysLate = 0; // Default initialization / 默认初始化
    }

    // 三种constructor

    // 1. Default Constructor
    // 默认构造函数
    public Rental() {
        this.movie = null;
        this.customerID = 0;
        this.daysLate = 0; 
    }

    // 2. Parameterized Constructor (Used in main method)
    // 带参构造函数 (主函数中使用了此构造函数)
    public Rental(Movie movie, int customerID) {
        this.movie = movie;
        this.customerID = customerID;
        this.daysLate = 0; // Default initialization
    }

    // 3. Copy Constructor
    // 拷贝构造函数
    public Rental(Rental other) {
        if (other != null) {
            this.movie = other.movie; // Shallow copy is standard unless deep copy specified
            this.customerID = other.customerID;
            this.daysLate = other.daysLate;
        }
    }

    // Method to set days late: rental1.setDaysLate(2);
    // 设置逾期天数的方法
    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }

    // Method to calculate fees for this specific rental
    // Used by Question4Rental logic: amount += rentals[i].getLateFees();
    // 计算此租赁费用的方法
    public double getLateFees() {
        // Delegate calculation to the Movie object
        // 将计算委托给 Movie 对象
        return this.movie.calcLateFees(this.daysLate);
    }
}
```

---

### Page 12-13: Question #5

**Question:**
Given the code in Figure 5.1 (`RandomDrawing<T>`), complete the class `Question5RandomDrawing` to produce the outputs shown in Figure 5.2 (Drawing integers then strings).

**Answer (答案):**

```java
public class Question5RandomDrawing {
    public static void main(String[] args) {
        
        // ---------------------------------------------
        // Box of Integers Logic
        // 整数盒子的逻辑
        // ---------------------------------------------
        
        // Instantiate the generic class with Integer
        // 使用 Integer 实例化泛型类
        RandomDrawing<Integer> intBox = new RandomDrawing<Integer>();

        // Add the integers as seen in the usage/screenshot
        // 添加截图中显示的整数
        intBox.add(3);
        intBox.add(10);
        intBox.add(75);
        intBox.add(45);

        System.out.println("Drawing from the box of integers:");
        
        // Loop while box is not empty and draw items
        // 当盒子不为空时循环并抽取元素
        while (!intBox.isEmpty()) {
            // drawItem removes and returns a random item
            // drawItem 移除并返回一个随机元素
            Integer val = intBox.drawItem();
            System.out.println(val);
        }

        // ---------------------------------------------
        // Box of Strings Logic
        // 字符串盒子的逻辑
        // ---------------------------------------------

        // Instantiate the generic class with String
        // 使用 String 实例化泛型类
        RandomDrawing<String> stringBox = new RandomDrawing<String>();

        // Add the strings
        // 添加字符串
        stringBox.add("Carol");
        stringBox.add("Bob");
        stringBox.add("Ted");
        stringBox.add("Alice");

        System.out.println("Drawing from the box of strings:");

        // Loop and draw strings
        // 循环并抽取字符串
        while (!stringBox.isEmpty()) {
            String name = stringBox.drawItem();
            System.out.println(name);
        }
    }
}
```