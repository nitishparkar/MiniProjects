import java.util.*;
/**
 *
 * @author Nitish
 */
public class BollyHolly
{
    static char[] tmp,word;
    public static void main(String args[])
    {
        int count=0,faults=9;
        ArrayList<Character> visited=new ArrayList<Character>();
        final char[] vowels={'U','O','I','E','A'};
        String st=getMovie();
        //System.out.println("mov:"+st);
        word=new char[st.length()];
        tmp=new char[st.length()];
        word=st.toCharArray();
        tmp=st.toCharArray();
        for(int j=0;j<tmp.length;j++)
        {
                if(tmp[j]==' ')
                {
                    tmp[j]='|';
                    count++;
                }
        }
        for(int i=0;i<vowels.length;i++)
        {
            for(int j=0;j<tmp.length;j++)
            {
                if(vowels[i]==tmp[j])
                {
                    tmp[j]='*';
                }
            }
        }
        for(int j=0;j<tmp.length;j++)
        {
                if(tmp[j]!='*'&&tmp[j]!='|')
                {
                    tmp[j]='_';
                }
        }
        char ch='*';
        Scanner ip=new Scanner(System.in);
        
        do
        {
            System.out.println("------------------------");
            display();
            System.out.println("Used Characters:"+visited);
            System.out.println("Eneter a character:");
            ch=ip.nextLine().toUpperCase().charAt(0);
            boolean flag=true;
            if(visited.contains(ch))
            {
                System.out.println("Character Already used");
                continue;
            }
            else
            {
                visited.add(ch);
                for(int j=0;j<tmp.length;j++)
                {
                    if(word[j]==ch)
                    {
                        tmp[j]=ch;
                        count++;
                        flag=false;
                    }
                }
            }
            if(flag)
            {
                faults--;
                System.out.println("Letter not found, Attempts remaining:"+faults);
            }

            if(count==word.length)
            {
                System.out.println("Success!!");
                display();
                break;
            }
            else if(faults==0)
            {
                System.out.println("Failure!!\nThe movie was:"+st);
                break;
            }
        }while(ch!='*');



    }

    static void display()
    {
        for(int j=0;j<tmp.length;j++)
        {
            System.out.print(tmp[j]+" ");
        }
        System.out.println("");
    }

    static String getMovie()
    {
        String st="BOLT";
        Scanner ip=null;
        try
        {
            ip=new Scanner(new java.io.File("c:/movies/mov.txt"));
            int count=new Random().nextInt(10);
            while(ip.hasNext())
            {
                st=ip.nextLine();
                if(--count==0)
                break;
            }
        }
        catch(java.io.FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return st.trim().toUpperCase();
    }
}
