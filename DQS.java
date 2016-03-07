// This jar is all we need to call Prolog from Java
import org.jpl7.*;
import java.util.HashMap;

public class DQS {

    public static void main(String[] args){
	// Call main with:
	//
	// args[0] = SchemaFileName
	//
	// AND
	//
	// (
	//  args[i] = TestName (for i > 0)
	//
	//  OR
	//
	//  no args greater than 1 means *ALL* tests will run.
	// )
	
	String schema;
	if (args.length >= 1){
	    schema = args[0];
	    
	    // First load the prolog file. (We should compile this in). 
	    Term noarg[] = { } ;
	    Query consult = new Query("consult('dqs.pl')",noarg);
	    
	    if(consult.hasSolution()){
		String tests;

		if(args.length > 2){
		    tests = "["+args[1];		
		    for (int i = 2; i < args.length ; i++){
			tests += "," + args[i];
		    }
		    tests += "]";
		}else{
		    tests = "all";
		}
		
		Atom schemaFile = new Atom(schema);
		Atom flags = new Atom(tests);
		Variable Results = new Variable("Results");
		
		Term arg[] = { schemaFile, flags, Results } ;
		
		// Consult a Prolog file
		Query q = new Query("runDQS", arg);
		if (q.hasMoreElements()){
		    Term bound_to_results = (Term) ((HashMap) q.nextElement()).get("Results");
		    System.out.println(bound_to_results);
		}else{
		    System.out.println("Unexpectedly failed to find error results\n");
		}
		q.close();
	    }
	    consult.close();	

	}else{
	    System.out.println("Error: No schema file specified");
	}
    }
}
