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
//		adj = adjList(v,e,edges);
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
	private static int[][] graph()
	{
		int [][] edges = {
				{0,1,2100},
				{1,2,3100},
				{2,3,1000},
				{3,4,3100},
				{3,35,2800},
				{3,36,450},
				{4,5,3100},
				{5,6,2200},
				{6,7,1400},
				{7,8,2900},
				{8,9,900},
				{9,10,800},
				{10,11,1300},
				{11,12,2000},
				{12,13,1600},
				{13,14,2300},
				{14,15,3400},
				{15,16,1800},
				{16,17,900},
				{17,18,1300},
				{18,19,2100},
				{19,20,1000},
				{20,21,2900},
				{21,22,2600},
				{22,23,1200},
				{23,24,1000},
				{24,25,1300},
				{25,26,1100},
				{26,27,1900},
				{27,28,1800},
				{28,29,1800},
				{29,30,1600},
				{30,31,1500},
				{31,32,1100},
				{32,33,1000},
				{33,34,1800},
				{34,35,600},
				{35,47,1900},
				{36,37,950},
				{37,38,1000},
				{38,39,750},
				{39,40,1300},
				{40,41,850},
				{41,42,1800},
				{42,43,2900},
				{43,44,3900},
				{44,45,2100},
				{45,46,2000},
				{47,48,1200},
				{48,49,1900},
				{49,50,1300},
				{50,51,3300},
				{51,52,2000},
				{52,53,1900},
				{53,54,1400},
				{54,55,1300},
				{55,56,1500}
				};
		return edges;
	}
	private static HashMap<Integer,String> stationName(HashMap<Integer,String>map)
	{
		map.put(0,"Howrah Maidan");
		map.put(1,"Howrah");
		map.put(2,"Mahakaran");
		map.put(3,"Esplanade");
		map.put(4,"Sealdah");
		map.put(5,"Phoolbagan");
		map.put(6,"Salt Lake Stadium");
		map.put(7,"Bengal Chemical");
		map.put(8,"City Center");
		map.put(9,"Central Park");
		map.put(10,"Karunamoyee");
		map.put(11,"Salt Lake Sector V");
		map.put(12,"Nicco Park");
		map.put(13,"Gour Kishore Ghosh");
		map.put(14,"Beliaghata");
		map.put(15,"Barun Sengupta");
		map.put(16,"Ritwik Ghatak");
		map.put(17,"VIP Bazar");
		map.put(18,"Hemanta Mukherjee");
		map.put(19,"Kavi Sukanata");
		map.put(20,"Jyotirindra Nath Nandi");
		map.put(21,"Satyajit Ray");
		map.put(22,"Kavi Subhas");
		map.put(23,"Shahid Khudiram");
		map.put(24,"Kavi Nazrul");
		map.put(25,"Gitanjali");
		map.put(26,"Masterda Surya Sen");
		map.put(27,"Netaji");
		map.put(28,"Mahanayak Uttam Kumar");
		map.put(29,"Rabindra Sarobar");
		map.put(30,"Kalighat");
		map.put(31,"Jatin Das Park");
		map.put(32,"Netaji Bhavan");
		map.put(33,"Rabindra Sadan");
		map.put(34,"Maidan");
		map.put(35,"Park Street");
		map.put(36,"Chandni Chowk");
		map.put(37,"Central");
		map.put(38,"Mahatma Gandhi Road");
		map.put(39,"Girish Park");
		map.put(40,"Shobhabazar Sutanuti");
		map.put(41,"Shyambazar");
		map.put(42,"Belgachia");
		map.put(43,"Dum Dum");
		map.put(44,"Noapara");
		map.put(45,"Baranagar");
		map.put(46,"Dakshineswar");
		map.put(47,"Victoria");
		map.put(48,"Kidderpore");
		map.put(49,"Mominpur");
		map.put(50,"Majerhat");
		map.put(51,"Taratala");
		map.put(52,"Behala Bazar");
		map.put(53,"Behala Chowrasta");
		map.put(54,"Sakherbazar");
		map.put(55,"Thakurpukur");
		map.put(56,"Joka");
		return map;
	}
//	private static ArrayList<ArrayList<Pair>> adjList(int v, int e,int[][]edges)
//	{
//		ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
//        for (int i=0; i<=v; i++)
//        {
//            adj.add(new ArrayList<Pair>());
//        }
//        for (int i=0; i<e; i++)
//        {
//            adj.get(edges[i][0]).add(new Pair(edges[i][2], edges[i][1]));
//            adj.get(edges[i][1]).add(new Pair(edges[i][2], edges[i][0]));
//        }
//        return adj;
//	}
	
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
