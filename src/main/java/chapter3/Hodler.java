package chapter3;

/**
 *
 */
public class Hodler {
     private int n;

     public Hodler(int n){
         this.n = n;
     }

     public void asserSanity(){
         if(n!=n){
             throw new AssertionError("this statement is false");
         }
     }
}
