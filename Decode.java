package Assignment_6;
public class Decode {

	public static void main(String[] args) {
		StdOut.print("Enter number to be decoded: ");
		int c = StdIn.readInt();
		StdOut.print("Enter the decoding exponent, d: ");
		long d = StdIn.readLong();
		StdOut.print("Enter the modulus, n: ");
		long n = StdIn.readLong();
		
		 // decode c to m = c^d mod n
		 char m = (char)expomod(c,d,n);
		 
		 System.out.println( "Decoding " + c + " to " + m);
	}
	static long expomod(long a, long n, long z) {
		 long r = a % z;
		 
		  for(long i = 1; i < n; i++) {
		 r = (a * r) % z;
		  }
		  return r;
		 }
}