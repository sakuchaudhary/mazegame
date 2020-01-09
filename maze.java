import java.util.*;
class Node
{
	int x, y, dist;

	Node(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
};

public class Main
{
    static final int row[] = { -1, 0, 0, 1 };
	static final int col[] = { 0, -1, 1, 0 };

    public static boolean valid(char arr[][],boolean visited[][],int s1,int s2,int n)
    {
        return (s1>=0) && (s2>=0) && (s1<n) && (s2<n) && arr[s1][s2]=='1' && !visited[s1][s2];
    }
    public static int bfs(char arr[][],int s1,int s2,int d1,int d2,int n)
    {
        boolean visited[][]=new boolean[n][n];
        Queue<Node> q = new LinkedList<>();
        visited[s1][s2]=true;
        q.add(new Node(s1,s2,0));
        
        int mindis = Integer.MAX_VALUE;
        
        while(!q.isEmpty())
        {
            Node nn = q.poll();
            s1=nn.x;
            s2=nn.y;
            int dist=nn.dist;
            
            if(s1==d1 && s2==d2)
            {
                mindis=dist;
                break;
            }
            for(int k=0;k<4;k++)
            {
                if(valid(arr,visited,s1+row[k],s2+col[k],n))
                {
                    visited[s1+row[k]][s2+col[k]]=true;
                    q.add(new Node(s1+row[k],s2+col[k],dist+1));
                }
            }
        }
        if(mindis!=Integer.MAX_VALUE)
            return mindis;
        else
            return -1;
    }
    
    public static void printmat(char arr[][],int n)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void  clearScrean()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Size either 6 or 10");
        int n = sc.nextInt();
        char arr[][]= new char[n][n];
        int visited[][]=new int[n][n];
        if(n==10)
        {
        arr[0][1]=arr[0][4]=arr[1][1]=arr[1][4]=arr[3][0]=arr[3][3]=arr[4][2]=arr[4][3]=arr[4][5]
        =arr[0][2]=arr[6][7]=arr[7][8]=arr[9][6]=arr[6][9]=arr[7][8]=arr[2][3]=arr[0][7]=arr[1][4]=arr[3][8]
        =arr[8][9]=arr[9][3]=arr[9][5]=arr[6][9]=arr[9][7]=arr[9][3]=arr[6][2]=arr[7][4]=arr[9][2]=arr[6][4]
        =arr[2][9]=arr[5][8]=arr[1][9]=arr[5][4]=arr[8][2]=arr[8][5]=arr[5][9]=arr[8][6]=arr[8][8]=arr[5][7]
        =arr[9][2]=arr[6][3]=arr[7][1]=arr[4][4]=arr[6][6]=arr[9][9]='0';
        }
        if(n==6)
        {
        arr[0][1]=arr[0][4]=arr[1][1]=arr[1][4]=arr[3][0]=arr[3][3]=arr[4][2]=arr[4][3]=arr[4][5]='0';
        }
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(arr[i][j]=='0')
                arr[i][j]='0';
                else
                arr[i][j]='1';
            }
        }
        
        System.out.println("Enter Source coordinates");
        int s1 = sc.nextInt();
        int s2 = sc.nextInt();
        System.out.println("Enter Destination");
        int d1 = sc.nextInt();
        int d2 = sc.nextInt();
        
        int dis=bfs(arr,s1,s2,d1,d2,n);
        
        arr[s1][s2] = '-';
        arr[d1][d2] = 'D';
        
        printmat(arr,n);
        int move=0;
        int userd=0;
        
        while (s1!=d1 || s2!=d2)
        {
            move=0;
            char ch=sc.next().charAt(0);
            if(ch=='a')
            {
                if(s2>0 && arr[s1][s2-1]!='0')
                {
                    arr[s1][s2-1]='-';
                    arr[s1][s2]='1';
                    s2--;
                    move=1;
                    userd++;
                }
            }
            if(ch=='d')
            {
                if(s2<n && arr[s1][s2+1]!='0')
                {
                    arr[s1][s2+1]='-';
                    arr[s1][s2]='1';
                    s2++;
                    move=1;
                    userd++;
                }
            }
            if(ch=='w')
            {
                if(s1>0 && arr[s1-1][s2]!='0')
                {
                    arr[s1-1][s2]='-';
                    arr[s1][s2]='1';
                    s1--;
                    move=1;
                    userd++;
                }
            }
            if(ch=='s')
            {
                if(s1<n && arr[s1+1][s2]!='0')
                {
                    arr[s1+1][s2]='-';
                    arr[s1][s2]='1';
                    s1++;
                    move=1;
                    userd++;
                }
            }
            printmat(arr,n);
            if(move==0)
            System.out.println("Invalid Move");
        }
        if(dis!=-1)
            System.out.println("Shortest distance was "+dis);
        System.out.println("Your distance Moved "+userd);
    }
}
