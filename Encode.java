package Assignment_6;
public class Encode {

	public static void main(String[] args) {
		StdOut.print("Enter the character to be encoded: ");
		char m= StdIn.readChar();
		StdOut.print("Enter the encoding exponent, e: ");
		long e = StdIn.readLong();
		StdOut.print("Enter the modulus, n: ");
		long n = StdIn.readLong();
		
		//StdOut.println("Enter character to be encoded, m:");

		 // encode m as c = m^e mod n using expomod
		long c = expomod(m,e,n);
		 
		 // c is sent to the receiver over an open channel
		StdOut.println("Transmitting encoded " + m + " as " + c);
	}
	static long expomod(long a, long n, long z) {
		 long r = a % z;
		  for(long i = 1; i < n; i++) {
		 r = (a * r) % z;
		  }
		  return r;
		 }
}
