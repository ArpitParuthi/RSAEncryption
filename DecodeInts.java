package Assignment_6;
public class DecodeInts {
	public static void main(String[] args) {
	StdOut.print("Enter the number of integers: ");
	int p = StdIn.readInt();
	StdOut.print("Enter numbers to be decoded: ");
	int[]c = new int[p];
	for(int i=0;i<p;i++){
		c[i]=StdIn.readInt();
		}
	
	StdOut.print("Enter the decoding exponent, d: ");
	long d = StdIn.readLong();
	StdOut.print("Enter the modulus, n: ");
	long n = StdIn.readLong();

	// decode c to m = c^d mod n
	char[] m = new char[p];
		for(int i=0;i<p;i++){
			m[i]=(char)expomod(c[i],d,n);
			}
		for(int i=0;i<c.length;i++)	{
				StdOut.print(m[i]);
		}
			// System.out.println( "Decoding " + c + " to " + m);
	}
	static long expomod(long a, long n, long z) {
		 long r = a % z;
		 
		  for(long i = 1; i < n; i++) {
		 r = (a * r) % z;
		  }
		  return r;
		 }
}