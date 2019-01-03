package algorithm;


import gui.*;

public class Dijkstra {
   
	DrawPad drawPad;
    ShortestPath myshortest = new ShortestPath() ; 
    int length;
    routerTable[] tables;
	public Dijkstra(DrawPad mmm)
	{ 
		drawPad= mmm;
		Router[] router = mmm.getRouterArray();
		int len = mmm.getRoutersCount();
		length=len;
	   
	    tables = new routerTable[len];
	    for(int i = 0 ; i < len ;i++)
	    {	
	    	tables[i]= new routerTable(len);
	    }
	    
	    /*boolean [] visited = new boolean [len];
	    for(int j = 0 ; j < len ;j++)
	    {	
	    	visited[j] = false;
	    }
	    */
		for(int i=0;i<len;i++)
		{  
			//myshortest.myweight=1000000000; 
			//set the distance between the source router and it self = zero
	      //  node(router[i],tables[i],router[i],0); 
	     //   int j = 0;
			node(router[i],tables[i],router[i],0); 
				    	
			for(int k=0;k<tables[i].numOfshortest;k++)
			{
			node(tables[i].tableOfrouter[k].endR,tables[i],router[i],tables[i].tableOfrouter[k].myweight); 
			}
				    	//	for(int k=0;k<(tables[i].numOfshortest);k++)
					//		{   
							//	if(tables[i].tableOfrouter[k].myweight<theweight)
						//		{  
							//		theweight=tables[i].tableOfrouter[k].myweight;
									
							//	}
								
				    	   // }
				    	//	for(int k=0;k<tables[i].numOfshortest;k++)
				    ///		{ if(tables[i].tableOfrouter[k].myweight==theweight)
	    				//		node(tables[i].tableOfrouter[k].endR,tables[i],router[i],tables[i].tableOfrouter[k].myweight); 
    
				    
				    					
				//node(tables[i].tableOfrouter[k].endR,tables[i],router[i],tables[i].tableOfrouter[k].myweight); 
			
	        	//some miricale
	        	//find the distance between the current unvisited node and its connections 
	        	//plus the distance between the current unvisited node and the source node
	        	//, and compare these distances to the current exsisting distances,if smaller then update it
	        	
	        //	visited[j] = true;
	        //	j++;
	        
	       
		}
		for(int x=0;x<len;x++)
		{	 System.out.println("the table of  "+router[x].mylabel);
			for(int y=0;y<len;y++)
			{  for(int z=0;z<(tables[x].tableOfrouter[y].numOfnodes);z++)
			    {   System.out.print(" "+String.valueOf(tables[x].tableOfrouter[y].nodes[z]));} 			
			        System.out.println();
			}
		
		}
	}
	
	public routerTable getRouterTable(String label) {
		for(int i =0; i < tables.length; i++) {
			if(tables[i].tableOfrouter[0].nodes[0] == label)
				return tables[i];
		}
		return null; // should never reach here
	}
	
	public void node(Router mynode,routerTable myRoutertable,Router currentrouter, int currentshortestweight) {
        Connection[] nodeConnections = mynode.getConnectionsArray();
        int numConnections= mynode.getConnectionsCount(); 
        int myWeight;
        int numNodes;
        boolean flag=false;
        int m=0;
        int z=0;
        boolean myflag=false;
        int connectionWeight=0; 
        int yy=0;
        
		for(int c=0;c<numConnections;c++)
		{   z=0; 
		    myflag=false;
		    numNodes=0;
			connectionWeight=nodeConnections[c].getWeight(); 
			 System.out.println("the current router is "+currentrouter.mylabel+"  cuurent node is "+mynode.mylabel+"  current connection router1 "+nodeConnections[c].router1.mylabel+" current connection router2 "+nodeConnections[c].router2.mylabel+" 1");
	
			 flag=false;
             for(int b=0;b<myRoutertable.numOfshortest;b++)
	             {    if(nodeConnections[c].router1==mynode)
		                 {
		            	     if((myRoutertable.tableOfrouter[b].endR==nodeConnections[c].router2 || nodeConnections[c].router2==currentrouter) && myRoutertable.tableOfrouter[b].startR==currentrouter)
		               	       {   
		            	    	 System.out.println("the current router is "+currentrouter.mylabel+"  cuurent node is "+mynode.mylabel+"  current connection router1 "+nodeConnections[c].router1.mylabel+" current connection router2 "+nodeConnections[c].router2.mylabel+" 2");
		            	      	 m=b;
		            		     flag =true;
		            		     break;
		            	       } 
		                 }
	                else if(nodeConnections[c].router2==mynode)
			             {
			        	     if((myRoutertable.tableOfrouter[b].endR==nodeConnections[c].router1 || nodeConnections[c].router1==currentrouter) && myRoutertable.tableOfrouter[b].startR==currentrouter)
			           	       {   
			        	    	 System.out.println("the current router is "+currentrouter.mylabel+"  cuurent node is "+mynode.mylabel+"  current connection router1 "+nodeConnections[c].router1.mylabel+" current connection router2 "+nodeConnections[c].router2.mylabel+" 3");
			        	      	 m=b;
			        		     flag =true;
			        		     break;
			        	       }
			             }
                 }
			
			if(flag==false) 
			{	
			 if(nodeConnections[c].router1==mynode && currentrouter != nodeConnections[c].router2 )
			     { 	     	     	
				 System.out.println("the current router is "+currentrouter.mylabel+"  cuurent node is "+mynode.mylabel+"  current connection router1 "+nodeConnections[c].router1.mylabel+" current connection router2 "+nodeConnections[c].router2.mylabel+" 4");
			        for(int y = 0;y<myRoutertable.numOfshortest;y++)
			        {
			        	if(myRoutertable.tableOfrouter[y].endR==mynode && myRoutertable.tableOfrouter[y].startR==currentrouter)
			        	{
			        		numNodes=myRoutertable.tableOfrouter[y].numOfnodes;
			        		z=y;
			        		myflag=true;
			         		break;			        		
			        	}		        	
			        }	 
			        
			        if(myflag==false) 
	 		        {   	 System.out.println("the current router is "+currentrouter.mylabel+"  cuurent node is "+mynode.mylabel+"  current connection router1 "+nodeConnections[c].router1.mylabel+" current connection router2 "+nodeConnections[c].router2.mylabel+" 5");
			     	    myRoutertable.tableOfrouter[myRoutertable.numOfshortest].startR=currentrouter;
				        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].endR =nodeConnections[c].router2;
				         
					    myRoutertable.tableOfrouter[myRoutertable.numOfshortest].nodes[1]=nodeConnections[c].router2.mylabel; 
				        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].nodes[0]=currentrouter.mylabel;  
                        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].numOfnodes=2;
                        
                        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].myweight =connectionWeight;	
                        
      					myRoutertable.numOfshortest++;
			        } 
			        else
			        {  
			       	 System.out.println("the current router is "+currentrouter.mylabel+"  cuurent node is "+mynode.mylabel+"  current connection router1 "+nodeConnections[c].router1.mylabel+" current connection router2 "+nodeConnections[c].router2.mylabel+" 6");
			        	for(int n=0;n<numNodes;n++)
			        	{
			        		myRoutertable.tableOfrouter[myRoutertable.numOfshortest].nodes[n]=myRoutertable.tableOfrouter[z].nodes[n]; 			        		
			        	} 
			     	    myRoutertable.tableOfrouter[myRoutertable.numOfshortest].startR=currentrouter;
				        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].endR =nodeConnections[c].router2;
				        
			        	myRoutertable.tableOfrouter[myRoutertable.numOfshortest].nodes[numNodes]=nodeConnections[c].router2.mylabel;
                        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].numOfnodes=numNodes+1;

                        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].myweight =myRoutertable.tableOfrouter[z].myweight+connectionWeight;	
     			       
      					myRoutertable.numOfshortest++;
			        }
  					
		         }
			  else if(nodeConnections[c].router2==mynode && currentrouter != nodeConnections[c].router1) 
			     {
			     
					 System.out.println("the current router is "+currentrouter.mylabel+"  cuurent node is "+mynode.mylabel+"  current connection router1 "+nodeConnections[c].router1.mylabel+" current connection router2 "+nodeConnections[c].router2.mylabel+" 7");
			        for(int y = 0;y<myRoutertable.numOfshortest;y++)
			        {
			        	if(myRoutertable.tableOfrouter[y].endR==mynode && myRoutertable.tableOfrouter[y].startR==currentrouter)
			        	{
			        		numNodes=myRoutertable.tableOfrouter[y].numOfnodes;
			        		z=y;
			        		myflag=true;
			        		break;			        		
			        	}		        	
			        }	 
			        
			        if(myflag==false)
			        {
			       	 System.out.println("the current router is "+currentrouter.mylabel+"  cuurent node is "+mynode.mylabel+"  current connection router1 "+nodeConnections[c].router1.mylabel+" current connection router2 "+nodeConnections[c].router2.mylabel+" 8");
			            myRoutertable.tableOfrouter[myRoutertable.numOfshortest].startR=currentrouter;
				        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].endR =nodeConnections[c].router1;	 
			        	
					    myRoutertable.tableOfrouter[myRoutertable.numOfshortest].nodes[1]=nodeConnections[c].router1.mylabel; 
				        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].nodes[0]=currentrouter.mylabel;  
                        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].numOfnodes=2;
                      
                        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].myweight =connectionWeight;
                        
    					myRoutertable.numOfshortest++;
			        } 
			        else
   			        { 
			       	 System.out.println("the current router is "+currentrouter.mylabel+"  cuurent node is "+mynode.mylabel+"  current connection router1 "+nodeConnections[c].router1.mylabel+" current connection router2 "+nodeConnections[c].router2.mylabel+" 9");
			        	for(int n=0;n<numNodes;n++)
			        	{
			         		myRoutertable.tableOfrouter[myRoutertable.numOfshortest].nodes[n]=myRoutertable.tableOfrouter[z].nodes[n];			        		
			        	}
			            myRoutertable.tableOfrouter[myRoutertable.numOfshortest].startR=currentrouter;
				        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].endR =nodeConnections[c].router1;	
			        	
			        	myRoutertable.tableOfrouter[myRoutertable.numOfshortest].nodes[numNodes]=nodeConnections[c].router1.mylabel;
                        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].numOfnodes=numNodes+1;

                        myRoutertable.tableOfrouter[myRoutertable.numOfshortest].myweight =myRoutertable.tableOfrouter[z].myweight+connectionWeight;	
			        	
    					myRoutertable.numOfshortest++;

			       }
			}
		        
			
			}
			
			
			 
			else if(flag==true)
			{ 
				if(nodeConnections[c].router1==mynode && currentrouter != nodeConnections[c].router2 )
				   {   
					 myWeight=nodeConnections[c].getWeight();
					  if(myRoutertable.tableOfrouter[m].myweight > (myWeight+currentshortestweight))
					  {      
						     myRoutertable.tableOfrouter[m].myweight=myWeight+currentshortestweight;
						 //    numNodes=myRoutertable.tableOfrouter[m].numOfnodes;		
						     
						     for(int y = 0;y<myRoutertable.numOfshortest;y++)
						        {
						        	if(myRoutertable.tableOfrouter[y].endR==mynode && myRoutertable.tableOfrouter[y].startR==currentrouter)
						        	{
						        		myRoutertable.tableOfrouter[m].numOfnodes=myRoutertable.tableOfrouter[y].numOfnodes+1;
						        		yy=y;
						        		break;
						        	}		        	 
						        }
						     	for(int n=0;n<myRoutertable.tableOfrouter[m].numOfnodes-1;n++)
				        	   {
				        		  myRoutertable.tableOfrouter[m].nodes[n]=myRoutertable.tableOfrouter[yy].nodes[n]; 			        		
				               } 
						  
							 myRoutertable.tableOfrouter[m].nodes[myRoutertable.tableOfrouter[m].numOfnodes-1]=nodeConnections[c].router2.mylabel; 
							 myRoutertable.tableOfrouter[m].nodes[myRoutertable.tableOfrouter[m].numOfnodes-2]=mynode.mylabel; 
 
				 	 		 node(nodeConnections[c].router2,myRoutertable,currentrouter, myRoutertable.tableOfrouter[m].myweight);

							
					  }
				   }  
				   else if (nodeConnections[c].router2==mynode && currentrouter != nodeConnections[c].router1)
				   {
					   myWeight=nodeConnections[c].getWeight();
						  if(myRoutertable.tableOfrouter[m].myweight > (myWeight+currentshortestweight))
						  {  
							  myRoutertable.tableOfrouter[m].myweight=myWeight+currentshortestweight; 
							//  numNodes=myRoutertable.tableOfrouter[m].numOfnodes;		
							  
							  for(int y = 0;y<myRoutertable.numOfshortest;y++)
						        {
						        	if(myRoutertable.tableOfrouter[y].endR==mynode && myRoutertable.tableOfrouter[y].startR==currentrouter)
						        	{
						        		myRoutertable.tableOfrouter[m].numOfnodes=myRoutertable.tableOfrouter[y].numOfnodes+1;
						        		yy=y;
						        		break;
						        	}		        	
						        }
								for(int n=0;n<myRoutertable.tableOfrouter[m].numOfnodes-1;n++)
					        	{
					        		myRoutertable.tableOfrouter[m].nodes[n]=myRoutertable.tableOfrouter[yy].nodes[n]; 			        		
					        	} 
							  
					   		  myRoutertable.tableOfrouter[m].nodes[myRoutertable.tableOfrouter[m].numOfnodes-1]=nodeConnections[c].router1.mylabel; 
					   		  myRoutertable.tableOfrouter[m].nodes[myRoutertable.tableOfrouter[m].numOfnodes-2]=mynode.mylabel; 
				 	 		 // myRoutertable.tableOfrouter[m].nodes[numNodes-2]=mynode.mylabel; 
				 	 		 node(nodeConnections[c].router1,myRoutertable,currentrouter, myRoutertable.tableOfrouter[m].myweight);
							  
						  }
				   } 
			   } 
					
		}	   		
}	
}
		