package algorithm;

public class routerTable {
    public ShortestPath[]  tableOfrouter;
    public int numOfshortest=0;
    
	public routerTable(int numOfRouters) {
		tableOfrouter = new ShortestPath[numOfRouters];
		 for(int i = 0 ; i < numOfRouters ;i++)
		    	tableOfrouter[i]= new ShortestPath();
	}
}
