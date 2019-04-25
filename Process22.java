package s;

public class Process22 {
private static double num1 ;
private static double num2;
public Process22() {
	
}
public Process22(double num1 , double num2) {
	this.num1 = num1;
	this.num2= num2;
}
public static double Addition() {
	return (num1+num2);
}
public static double Subtraction() {
	return (num1-num2);
}
public static double Multiply() {
	return (num1*num2);
}
public static double Divide() {
	return (num1/num2);
}
}
