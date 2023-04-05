import java.util.*;
public class Main 
{
	public static void main(String[] args) throws Exception
	{
		PriorityQueue<Integer>pq = new PriorityQueue();
		Scanner sc = new Scanner(System.in);
		int v=57, e=57;
		int dst = -1, s=-1;
		int fare = 0;
		int totalDist = -2;
		int [] dist = new int [v];
		ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
		List<Integer> path = new ArrayList<Integer>();
		HashMap<Integer, String> map = new HashMap<>();
		HashMap<String, Integer> invertedMap = new HashMap<>();
		stationName(map);
		for (int i=0; i<v; i++)
		{
			invertedMap.put(map.get(i), i);
		}
		int edges [][] = graph();
		adj = adjList(v,e,edges);
//		System.out.println("---- Kolkata Metro Stations Shortest Routes DSA Project ----");
		System.out.println("Choose a numeric value from below list");
		System.out.println("---------------------------------------");
		System.out.println("Enter 0: Exit Menu");
		System.out.println("Enter 1 : Show All Stations");
		System.out.println("Enter 2 : Enter Source Station & Destination Station");
		System.out.println("Enter 3: Distance to be travelled");
		System.out.println("Enter 4: Shortest Path");
		System.out.println("Enter 5: Estimated Time");
		System.out.println("Enter 6: Fare");
		System.out.println("----------------------------------------");
		System.out.println("Enter Your Choice");
		int choice = Integer.parseInt(sc.nextLine()); // to avoid the pbrlm of using sc.nextLine() after sc.nextInt()
		while(choice != 0) 
		{
			switch(choice)
			{
				case 0:
					System.exit(0);
				case 1:
					displayStation(v,map);
					break;
				case 2:
					//if user inputs any wrong station name --> "while loop" is for that reason
					s = -1;
					dst = -1;
					while(s == -1) {
						System.out.println("Enter Source Station");
						String sstr = sc.nextLine();
						s = getInt(title(sstr),invertedMap);
					}
					while(dst == -1) {
						System.out.println("Enter Destination Station");
						String dstr = sc.nextLine();
						dst = getInt(title(dstr),invertedMap);		
					}
					if (dst==s)
						throw new SameNameException("Looks like Source and Destination Stations are same");
					path=Util.dijsktraAlgo(v, e, s, dst, edges, dist);
					break;
				case 3:
					// try-catch reason : asking for distance before entering source and destination
					try {
						System.out.println("Approx Distance "+dist[dst]);	
					}
					catch (Exception ex)
					{
						System.out.println("Pls Enter Source and Destination Station at First");
					}
					
					break;
				case 4:
					if(path.size()==0)
						System.out.println("Pls Enter Source and Destination Station at First");
					else
						showPath(map, path);
					break;
				case 6:
					try {	
					fare = Util.fair(dist[dst]);
					System.out.println("Fare need to be paid "+fare);
					}
					catch (Exception ex)
					{
						System.out.println("Pls Enter Source and Destination Station at First");
					}
					break;
				case 5:
					try {						
						System.out.println("Journey Time "+Util.estimatedTime(dist[dst], path.size())+" min ");
					}
					catch (Exception Ex) {
						System.out.println("Pls Enter Source and Destination Station at First");
					}
					break;
			}
			System.out.println("----------------------------------------");
			System.out.println("Enter Your Choice");
			choice = Integer.parseInt(sc.nextLine());;
		}
	}
	
//      td /views/head

	
	
// 	private static ArrayList<ArrayList<Pair>> adjList(int v, int e,int[][]edges)
// 	{
// 		ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
//         for (int i=0; i<=v; i++)
//         {
//             adj.add(new ArrayList<Pair>());
//         }
//         for (int i=0; i<e; i++)
//         {
//             adj.get(edges[i][0]).add(new Pair(edges[i][2], edges[i][1]));
//             adj.get(edges[i][1]).add(new Pair(edges[i][2], edges[i][0]));
//         }
//         return adj;
// 	}
	
	private static int getInt(String s, HashMap<String, Integer> map) throws NullPointerException
	{
		try {			
			return map.get(s);
		}
		catch (NullPointerException e)
		{
			System.out.println("Looks like you have entered a wrong Station Name -- Re-enter");
		}
		return -1;
	}
	private static String getString(int v, HashMap<Integer, String> map) 
	{
		return map.get(v);
	}
	
	private static void displayStation(int v, HashMap<Integer,String>map)
	{
		for (int i=0; i<v; i++)
		{
			System.out.println(i+1+" "+map.get(i));
		}
	}
	
	private static void showPath(HashMap<Integer,String>map,List<Integer>path)
	{
		for (int i=0; i<path.size(); i++) 
		{
			System.out.print(map.get(path.get(i)));
			if (i != path.size()-1)
				System.out.print(" -> ");
		}
		System.out.println();
	}
	
	public static String title(String text) {
	    if (text == null || text.isEmpty()) {
	        return text;
	    }
	    StringBuilder converted = new StringBuilder();
	    boolean convertNext = true;
	    for (char ch : text.toCharArray()) {
	        if (Character.isSpaceChar(ch)) {
	            convertNext = true;
	        } else if (convertNext) {
	            ch = Character.toTitleCase(ch);
	            convertNext = false;
	        } else {
	            ch = Character.toLowerCase(ch);
	        }
	        converted.append(ch);
	    }
	    return converted.toString();
	}
}
