I have not used wait methods as it is mentioned in the assignment

For task 2 , I have used yahoo mail as gmail blocks the automation And temp mail also blocks the automation so I have assumed dummy xpath for last steps (It is mentioned as comments in the code)

You have to add Yahoo mail's username, password, subject and body in TestConfig class in src/main/java/config folder 

Project Structure :-

Base folder has TestBase class to invoke webdriver
src\test\java\testcases has 2 classes FlightTestFromMMT.java and TestEmail.java

FlightTestFromMMT.java has test method for Task 1
TestEmail.java has test method for Task 2

src/main/java/config folder has TestConfig.java class which has variables like username , pass , urls etc

src\main\resources folder has screenshot need in Task 2
