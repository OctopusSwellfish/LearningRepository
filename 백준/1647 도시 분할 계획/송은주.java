import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Main{
	private static int V;
	private static int E;
	private static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parent = new int[V+1];
		ArrayList<int[]> arr = new ArrayList<>();
		
		init();
		
		for(int i=0; i<E; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			arr.add(new int[] {to, from, dist});
//			arr.add(new int[] {from, to, dist});
		}
		
		Collections.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]- o2[2]; //dist기준으로 오름차순정렬로 정렬
			}
		});
		
		int res = 0;
		int dist = -1;
		for(int i=0; i<arr.size(); ++i) {
			int[] temp = arr.get(i);
			if(findSet(temp[0])!=findSet(temp[1])) {
				union(temp[0], temp[1]);
				res += temp[2];
				dist = temp[2];
			}
		}
		
		System.out.println(res-dist);
	}
	private static int findSet(int x) {
		return parent[x]!=x?parent[x]=findSet(parent[x]):parent[x];
	}

	private static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot!=bRoot) {
			parent[bRoot] = aRoot; //aRoot가 짱
		}
	}
	private static void init() {
		for(int i=0; i<parent.length; ++i) {
			parent[i] = i;
		}
	}
}
