import java.util.Scanner;
public class checksum {
	static Scanner sc=new Scanner(System.in);
	public static void main(String args[])
	{
		int n;
		System.out.println("Enter the number of data-items at sender side:");
		n=sc.nextInt();
		System.out.println("Enter the data-items to be sent:");
		int a[]=new int[n];
		for(int i=0;i<n;i++)
		{
			a[i]=Integer.parseInt(sc.next(),16); //base 16
		}
		int comp=sum_up(a);
		String h_c=Integer.toHexString(comp);
		System.out.println("Checksum to be sent:"+h_c);
		System.out.println("Data-items to be sent:");
		for(int i=0;i<a.length;i++)
			System.out.println(Integer.toHexString(a[i]));
		System.out.println(h_c);
		System.out.println("Enter the number of data-items at receiver side:");
		n=sc.nextInt();
		System.out.println("Enter the data-items received:");
		int b[]=new int[n];
		for(int i=0;i<n;i++)
		{
			b[i]=Integer.parseInt(sc.next(),16); //base 16
		}
		sc.close();
		int f_sum=sum_up(b);
		String syndrome=Integer.toHexString(f_sum);
		if(syndrome.equals("0")) //no error
			System.out.println("Data accepted");
		else
			System.out.println("Data rejected");
	}
	static int sum_up(int a[])
	{
		int sum=0;
		for(int i=0;i<a.length;i++)
			sum=sum+a[i]; 
		String hexa = Integer.toHexString(sum); //generated partial sum
		int wrap=(int)hexa.charAt(0)-48; //getting the carry in wrap variable
		int var=Integer.parseInt(hexa.substring(1),16); //obtaining the remaining 4 hexa digits to perform wrapping up
		sum=var+wrap; //wrapping up
		return 65535-sum; //complementing the final sum
		
	}
}
