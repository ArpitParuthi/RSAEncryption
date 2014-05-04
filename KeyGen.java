package Assignment_6;
public class KeyGen {
 
 public static void main(String args[]) { 
 // choose two distinct primes with p < q
 StdOut.print("Enter smaller prime number p: ");
 long p = StdIn.readLong();
 StdOut.print("Enter larger prime number q: ");
 long q = StdIn.readLong(); 
 
 if(p>q){
	 System.err.print("p must be smaller than q");
	 }
 else{
	 StdOut.println("p = " + p + " q = " + q );
	  
 // choose n as the product of p and q
 // no known algorithm can recompute p and q from n within  
 // a reasonable period of time for large n.
 
 long n = p * q;
 StdOut.println("The value of n = " + n);
 
 // Compute phi = (p-1)*(q-1). 
 long phi = (p - 1) * ( q - 1);
  StdOut.println("The value of phi = " + phi);
 
 // choose a random prime e between 1 and phi, exclusive,  
 // so that e has no common factors with phi.
 
 long e = findfirstnocommon(phi);
 
 StdOut.println("The value of e = " + e);
 
 // Compute d as the multiplicative inverse of e
 // modulo phi(n).
 
 long d = findinverse(e,phi); 
 StdOut.println("The value of d = " + d);
 
 }
 }
 
 // Let a and n be two long integers with n > 0. We wish to
 // compute x = a^n mod z.
 
 
 
 static long findfirstnocommon(long n) {
 
  long j;
  for(j = 2; j < n; j++)
  if(euclid(n,j) == 1) return j;
  return 0;
 }
 
 static long findinverse(long n, long phi) {
 
  long i = 2;
  while( ((i * n) % phi) != 1) i++;
  return i;
 }
 
 
 static long euclid(long m, long n) {
 
 // pre: m and n are two positive integers (not both 0)
 // post: returns the largest integer that divides both
 // m and n exactly
 
 while(m > 0) {
 long t = m;
 m = n % m;
 n = t;
  }
  return n;
 }
 
}
