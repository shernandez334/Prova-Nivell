package EscapeRoomTest.exceptions;

public class NonAlphabeticInputException extends Exception{
   public NonAlphabeticInputException(String error){
       super(error);
   }
}