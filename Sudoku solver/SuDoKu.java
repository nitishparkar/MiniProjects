/**
* @author:Nitish Parkar
*
*/
import java.io.*;
class SuDoKu
{
	public static void main(String args[])throws IOException
	{
		MtdMan m=new MtdMan();
		m.mroot();

	}
}

class MtdMan
{

		int arr[][][]=new int[9][9][9];

		void mroot()throws IOException
		{
		int i,j,k;
//initialize
		for(i=0;i<9;i++)
		{
			for(j=0;j<9;j++)
			{
				for(k=0;k<9;k++)
				{

					arr[i][j][k]=k+1;
				}
			}
		}
//display matrix    disp();

//accept ip
		int num;
		System.out.println("Enter SuDoKu:");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(i=0;i<9;i++)
		{
			for(j=0;j<9;j++)
			{
				num=Integer.parseInt(br.readLine());
				if(num!=0)
				{
					initelm(i,j,num);
					elm(i,j,num);
				}
			}
		}
		System.out.println("\n\nBefore Processing:");
		disp();
//process

		for(int z=0;z<10;z++)
		{
		boolean op;
		for(i=0;i<9;i++)
		{
			for(j=0;j<9;j++)
			{
				int add=0,cnt=0;
				for(k=0;k<9;k++)
				{
					if((arr[i][j][k])!=0)
					{
						op=ifOn(i,j,k);
						if(op==true)
							initelm(i,j,k+1);

					}
					if((arr[i][j][k])==0)
						cnt++;
					add=add+arr[i][j][k];
					if((cnt==8)&&(add!=0))
						elm(i,j,add);
				}

			}

		}
		}
		System.out.println("\nFinal:");
		dispfinal();
	}//eof mtd
	void dispfinal()
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				int cnt=0,add=0;
				for(int k=0;k<9;k++)
				{
					if((arr[i][j][k])==0)
						cnt++;
					add=add+arr[i][j][k];
					if((cnt==8)&&(add!=0))
						System.out.print(add+" ");

				}
			}
			System.out.println();
		}
	}

		boolean ifOn(int i,int j,int k)
		{
			boolean dec=true;
			int a,b;
			//block1
			if((i<3)&&(j<3))
			{
				for(a=0;a<3;a++)
				{
					for(b=0;b<3;b++)
					{
						if((a==i)&&(b==j))
							continue;
						if(arr[a][b][k]!=0)
							dec=false;
					}
				}
			}
//block2
			else if((i<3)&&(j<6))
			{
				for(a=0;a<3;a++)
				{

					for(b=3;b<6;b++)
					{
						if((a==i)&&(b==j))
							continue;
						if(arr[a][b][k]!=0)
							dec=false;
					}
				}
			}
			//block3
			else if((i<3)&&(j<9))
			{
				for(a=0;a<3;a++)
				{

					for(b=6;b<9;b++)
					{
						if((a==i)&&(b==j))
							continue;
						if(arr[a][b][k]!=0)
							dec=false;
					}
				}
			}
			//block4
			else if((i<6)&&(j<3))
			{
				for(a=3;a<6;a++)
				{

					for(b=0;b<3;b++)
					{
						if((a==i)&&(b==j))
							continue;
					if(arr[a][b][k]!=0)
							dec=false;
					}	}
			}
			//block5
			else if((i<6)&&(j<6))
			{
				for(a=3;a<6;a++)
					for(b=3;b<6;b++)
					{
						if((a==i)&&(b==j))
							continue;
						if(arr[a][b][k]!=0)
							dec=false;
					}
			}
			//block6
			else if((i<6)&&(j<9))
			{
				for(a=3;a<6;a++)
				{

					for(b=6;b<9;b++)
					{
						if((a==i)&&(b==j))
							continue;
						if(arr[a][b][k]!=0)
							dec=false;
					}	}
			}
			//b;ock7
			else if((i<9)&&(j<3))
			{
				for(a=6;a<9;a++)
				{

					for(b=0;b<3;b++)
					{
						if((a==i)&&(b==j))
							continue;
					if(arr[a][b][k]!=0)
							dec=false;
					}	}
			}
			//block8
			else if((i<9)&&(j<6))
			{
				for(a=6;a<9;a++)
				{

					for(b=3;b<6;b++)
					{
						if((a==i)&&(b==j))
							continue;
					if(arr[a][b][k]!=0)
							dec=false;
					}
						}
			}
			//block9
			else if((i<9)&&(j<9))
			{
				for(a=6;a<9;a++)
				{
					for(b=6;b<9;b++)
					{
						if((a==i)&&(b==j))
							continue;
					if(arr[a][b][k]!=0)
							dec=false;
					}	}
			}
			return dec;
		}
		void onlyc(int i,int j,int k)
		{
			for(int z=0;z<9;z++)
			{
			 if(z==k)
					continue;
			 arr[i][j][k]=0;
			}
		}

		void elm(int i,int j,int n)
		{
			for(int x=0;x<9;x++)
			{
				if(x==j)
					continue;
				arr[i][x][n-1]=0;
			}
			for(int x=0;x<9;x++)
			{
				if(x==i)
					continue;
				arr[x][j][n-1]=0;
			}
			int a,b;

			//block1
			if((i<3)&&(j<3))
			{
				for(a=0;a<3;a++)
				{
					for(b=0;b<3;b++)
					{
						if((a==i)&&(b==j))
							continue;
						arr[a][b][n-1]=0;
					}
				}
			}
//block2
			else if((i<3)&&(j<6))
			{
				for(a=0;a<3;a++)
				{

					for(b=3;b<6;b++)
					{
						if((a==i)&&(b==j))
							continue;
						arr[a][b][n-1]=0;
					}
				}
			}
			//block3
			else if((i<3)&&(j<9))
			{
				for(a=0;a<3;a++)
				{

					for(b=6;b<9;b++)
					{
						if((a==i)&&(b==j))
							continue;
						arr[a][b][n-1]=0;
					}
				}
			}
			//block4
			else if((i<6)&&(j<3))
			{
				for(a=3;a<6;a++)
				{

					for(b=0;b<3;b++)
					{
						if((a==i)&&(b==j))
							continue;
						arr[a][b][n-1]=0;
					}	}
			}
			//block5
			else if((i<6)&&(j<6))
			{
				for(a=3;a<6;a++)
					for(b=3;b<6;b++)
					{
						if((a==i)&&(b==j))
							continue;
						arr[a][b][n-1]=0;
					}
			}
			//block6
			else if((i<6)&&(j<9))
			{
				for(a=3;a<6;a++)
				{

					for(b=6;b<9;b++)
					{
						if((a==i)&&(b==j))
							continue;
						arr[a][b][n-1]=0;
					}	}
			}
			//b;ock7
			else if((i<9)&&(j<3))
			{
				for(a=6;a<9;a++)
				{

					for(b=0;b<3;b++)
					{
						if((a==i)&&(b==j))
							continue;
						arr[a][b][n-1]=0;
					}	}
			}
			//block8
			else if((i<9)&&(j<6))
			{
				for(a=6;a<9;a++)
				{

					for(b=3;b<6;b++)
					{
						if((a==i)&&(b==j))
							continue;
						arr[a][b][n-1]=0;
					}
						}
			}
			//block9
			else if((i<9)&&(j<9))
			{
				for(a=6;a<9;a++)
				{
					for(b=6;b<9;b++)
					{
						if((a==i)&&(b==j))
							continue;
						arr[a][b][n-1]=0;
					}	}
			}

		}
		void initelm(int i,int j,int num)
		{
			for(int k=0;k<9;k++)
			{
			if(k==(num-1))
				continue;
			arr[i][j][k]=0;
			}
		}
		void disp()
		{
			for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				for(int k=0;k<9;k++)
				{

					System.out.print(arr[i][j][k]);
				}
				System.out.print(" ");
			}
			System.out.println("");
		}
		}
}

/* test ip
0
0
0
1
4
8
0
0
6
0
9
4
3
0
0
0
8
0
0
8
0
0
0
9
0
0
1
0
0
0
9
0
0
4
0
0
0
0
0
0
8
0
0
0
0
0
0
1
0
0
5
0
0
0
5
0
0
8
0
0
0
6
0
0
3
0
0
0
2
7
1
0
2
0
0
5
7
6
0
0
0 */