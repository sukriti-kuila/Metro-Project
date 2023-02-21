import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Util 
{
	public static List<Integer> dijsktraAlgo(int n, int m, int s, int dest, int edges[][], int dist[]) 
	{
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i=0; i<n; i++)
        {
            adj.add(new ArrayList<Pair>());
        }
        for (int i=0; i<m; i++)
        {
            adj.get(edges[i][0]).add(new Pair(edges[i][2], edges[i][1]));
            adj.get(edges[i][1]).add(new Pair(edges[i][2], edges[i][0]));
        }
        List<Integer> ans = new ArrayList<Integer>();
        int [] parent = new int [n];
        PriorityQueue <Pair> pq = new PriorityQueue<Pair>((x,y)-> x.dist-y.dist);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        for (int i=0; i<n ; i++)
        {
            parent[i]=i;
        }
        pq.add(new Pair(0,s));
        while (!pq.isEmpty())
        {
            Pair p = pq.remove();
            int node = p.node;
            int dis = p.dist;
            for (Pair it : adj.get(node))
            {
                    int d = it.dist;
                    int no = it.node;
                    if (dis+d<dist[no])
                    {
                        dist[no] = dis+d;
                        parent[no] = node;
                        pq.add(new Pair(dis+d,no));
                    }
            }
                
        }  
        if (dist[dest]==Integer.MAX_VALUE) 
            return new ArrayList<Integer>(Arrays.asList(-1));
        int i = dest;
        ans.add(dest);
        while(i != s)
        {
        	i = parent[ans.get(0)];
        	ans.add(0,i);
        }
        return ans;
    }
	public static int estimatedTime(int totaldist, int stop)
	{
		int s = 55; // 55kmh
		int km = totaldist/1000; 
		int t = (km*60)/s;
		return t+stop-2; //stop-2 @reason -> stop => total no. stations in the journey including source and destination station, halting time 1 min at each station so t+stop-2
	}
	public static int fair(int totaldist)
	{
		double km = totaldist/1000.0;
		if (km>=25.0)
			return 25;
		if (km>=20.0 && km<25.0)
			return 20;
		if (km>=10.0 && km<20.0)
			return 15;
		if (km>=5.0 && km<10.0)
			return 10;
		else
			return 5;
	}
	
//	public static int fairLine234(int totaldist)
//	{
//		double km = totaldist/1000.0;
//		if (km>=10.0)
//			return 30;
//		if (km>=5.0 && km<15.0)
//			return 20;
//		if (km>=2.0 && km<5.0)
//			return 10;
//		else
//			return 5;
//	}
	
//	public static int calculateFare(HashMap<Integer, String>map, int source, int dest, int dist)
//	{
//		ArrayList<Integer> line1 = new ArrayList();
//		line1.add(3);
//		for (int i=22;i<=46;i++)
//			line1.add(i);
//		if(line1.contains(source) && line1.contains(dest))
//			return fairLine1(dist);
//		else if (!line1.contains(source) && !line1.contains(dest))
//		{
//			return fairLine234(dist);
//		}
//		else
//		{
//			
//		}
//	}

}
