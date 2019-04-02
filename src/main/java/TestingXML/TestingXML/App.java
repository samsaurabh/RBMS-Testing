package TestingXML.TestingXML;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import jaxb.*;

public class App 
{
	private static ResultSet output;
	
    public static void main( String[] args )
    {
    	Connect c = new Connect();
        c.setConnection("RBMS","postgres","clause@DM19");
        Connection conn = c.getConnection();
        
        Sql sql = new Sql(conn);
    	
        System.out.println( "Hello World!" );
        
        XMLParsing xparse = new XMLParsing();
	    
	    xparse.marshallList();
	    ArrayList<ArrayList<String>> result = xparse.unmarshallList();
	    if(result.size() > 0) {
	    	System.out.println(result);
	    
	    	try{
		    	
		    	for(int i = 0;i < result.size();i++) {
		    	
		    		if(result.get(i).get(1).equals("acc_balance") ) {
			    		//System.out.println(result.get(i).get(1));
			    		String query = "select * from "+ result.get(i).get(0) + " where " + result.get(i).get(1)  + result.get(i).get(2) + result.get(i).get(4) + ";";
			    		System.out.println(query);
			    		output = sql.select(query);
			    		System.out.println(output);
			    		
			    		while (output.next())
			            {
			    			ArrayList<String> temp = new ArrayList<String>();  
			    			int acc_no = output.getInt("acc_no");
			    			String Branch_Code = output.getString("Branch_Code");
			    			int cust_id = output.getInt("Cust_ID");
			    			String Acc_Type = output.getString("Acc_Type");
			    			float Acc_Balance = output.getFloat("Acc_Balance");
			              
			              
//							temp.add(output.getInt("acc_no")+"");
//							temp.add(output.getString("br")+"");
//							temp.add(output.getString("address")+"");
//							temp.add(output.getString("age")+"");
//							temp.add(output.getInt("salary")+"");
				              //res.add(temp);
			              
			              
			    			// print the results
			    			System.out.format("%d, %s, %d, %s, %f", acc_no, Branch_Code, cust_id, Acc_Type, Acc_Balance);
			    			System.out.println();
			              
			    			//String query_update = "insert into premium_account values("+acc_no+",'"+Branch_Code+"',"+cust_id+",'"+Acc_Type+"',"+Acc_Balance+");";
			    			//System.out.println(query_update);
			              
			    			//sql.insert(query_update);
			    			//System.out.println("done");
			    			
			    			String query_delete = "delete from account where acc_no="+acc_no;
			    			System.out.println(query_delete);
			    			sql.update(query_delete);
			    			System.out.println("done2");
			    			
			            }
			    	}
			    }
		    }
	    	catch (SQLException e) {
	    		
	    		System.out.println(e.getMessage());
	    	}
	    }
    }
}
