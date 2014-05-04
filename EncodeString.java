package Assignment_6;
public class EncodeString {
	public static void main(String[] args) {
		StdOut.print("Enter the string: ");
		String m = StdIn.readLine();
		char[] mChar = m.toCharArray();
		StdOut.print("Enter the encoding exponent, e: ");
		long e = StdIn.readLong();
		StdOut.print("Enter the modulus, n: ");
		long n = StdIn.readLong();
		long c[]= new long[mChar.length];
		
		 // encode m as c = m^e mod n using expomod
		for(int i=0;i<mChar.length;i++){
				c[i] = expomod(mChar[i],e,n);
		}
		
		 // c is sent to the receiver over an open channel
			for(int i=0;i<c.length;i++)
				StdOut.print(c[i]+" ");
		
	}
	static long expomod(long a, long n, long z) {
		 long r = a % z;
		  for(long i = 1; i < n; i++) {
		 r = (a * r) % z;
		  }
		  return r;
		 }
}


