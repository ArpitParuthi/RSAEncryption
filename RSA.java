package Assignment_6;
import java.math.BigInteger;
import java.util.Random;

public class RSA {
 
public void Keygen(int primeBits){
	 // get a random number
	 Random rnd = new Random();
	 // get two distinct primes of size primeBits
	 BigInteger p = new BigInteger(primeBits,128,rnd);
	 BigInteger q;
	 do q = new BigInteger(primeBits,128,rnd);
	 while(p.compareTo(q) == 0);
	 // compute the modulus
	 BigInteger n = p.multiply(q);
	 // compute m = phi(n)
	 BigInteger pMinus1 = p.subtract(BigInteger.valueOf(1));
	 BigInteger qMinus1 = q.subtract(BigInteger.valueOf(1));
	 BigInteger m = pMinus1.multiply(qMinus1);
	 // get e relatively prime to m
	 BigInteger e = BigInteger.valueOf(3);
	 while(e.gcd(m).compareTo(BigInteger.valueOf(1)) > 0)
	 e = e.add(BigInteger.valueOf(2));
	 // compute d the decryption exponent
	 BigInteger d = XGCD(e,m);
	 StdOut.println ("e = " + e + "\nd = " + d + "\nn = " + n + "\nphi = "+m);
	 Out out = new Out("public.txt");
	 out.println(n);
	 out.println(e);
	 Out out2 = new Out("private.txt");
	 out2.println(n);
	 out2.println(d);
	 StdOut.print("Keys successfully saved to public.txt and private.txt!");
	 }

 public void Encode(String message, String filename) {
	 char[] mChar =	message.toCharArray();
	 int[] numArray = new int[mChar.length];
	 BigInteger b[] = new BigInteger[mChar.length];
	 In in = new In("public.txt");
	 String d[] = new String[2];
	 while (in.hasNextLine()) {
		 for(int i=0;i<2;i++){
			 d[i] = in.readLine();
		 }
     }
	BigInteger n = new BigInteger(d[0]);
	BigInteger e = new BigInteger(d[1]);
	for (int i = 0; i < mChar.length; i++)
    {
            numArray[i] = mChar[i];
            b[i] = BigInteger.valueOf(numArray[i]);
    }
			
	Out out = new Out(filename+".enc");
	for(int i=0;i<mChar.length;i++){
		out.println(expomod(b[i],e,n));
	}
	StdOut.print(filename+" successfully encoded to " + filename+".enc");
 }	
 
 public void Decode(String filename) {
	 String file= filename.replace(".enc", "");
	 In in = new In("private.txt");
	 String x[] = new String[2];
	 while (in.hasNextLine()) {
		 for(int i=0;i<2;i++){
			 x[i] = in.readLine();
		 }
     }
	 BigInteger n = new BigInteger(x[0]);
	 BigInteger d = new BigInteger(x[1]);
	 In in2 = new In(filename);
	 String[] ary = in2.readAllLines();
	
	 BigInteger a[] = new BigInteger[ary.length];
	 	for(int i=0;i<ary.length;i++) {
	 		a[i]= new BigInteger(ary[i]);
	 	}
	 String[] m = new String[ary.length];
	 	for(int i=0;i<m.length;i++){
			m[i] = new String(expomod(a[i],d,n).toByteArray());
			}
		Out out = new Out(file+".cop");
		for(int i=0;i<m.length;i++){
			out.print(m[i]);
		}
		StdOut.print(filename+" successfully decoded to " + file+".cop");
 }
 
 static BigInteger XGCD(BigInteger a, BigInteger b)
 {
 	BigInteger d=a;
     BigInteger x = BigInteger.ZERO, y = BigInteger.ONE, lastx = BigInteger.ONE, lasty = BigInteger.ZERO, temp;
     while (b.compareTo(BigInteger.ZERO) != 0)
     {
         BigInteger r = a.mod(b);
         BigInteger q = a.divide(b);
         a = b;
         b = r;

         temp = x;
         x = lastx.subtract(q.multiply(x));
         lastx = temp;

         temp = y;
         y = lasty.subtract(q.multiply(y));
         lasty = temp;            
     }
     if(lasty.compareTo(BigInteger.ZERO)<0) {lasty = d.add(lasty);}
     return(lasty);   	
 }
 
 static BigInteger expomod(BigInteger a, BigInteger n, BigInteger z) {
	 BigInteger r = a.mod(z);
	 
	  for(BigInteger i = BigInteger.valueOf(1); i.compareTo(n)<0; i = i.add(BigInteger.ONE)) {
		  r = (a.multiply(r)).mod(z);
	  }
	  return r;
	 }
 
 public static void main(String args[]) {
	 try {
	 	int primeBits = Integer.valueOf(args[0]);
	 	RSA big = new RSA();
		big.Keygen(primeBits);
	 }
	 catch (NumberFormatException e) {
		 if (args[0].equals("encrypt")) {
 		 In in = new In(args[1]);
 		 String filename= args[1];
		 String message = in.readAll();
		 RSA big = new RSA();
 		 big.Encode(message,filename);
 		}
		 else if (args[0].equals("decrypt")) {
 		String filename = args[1];
 		RSA big = new RSA();
		big.Decode(filename);
 		}
		 else {
			 System.err.println("Invalid input!");
		 }	 
	 }
   }
}