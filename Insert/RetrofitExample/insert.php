<?php
   
    $name=$_REQUEST['name'];
	$email=$_REQUEST['email'];
	$address=$_REQUEST['address'];
	$mobile=$_REQUEST['mobile'];
    
	// array for JSON response
    $response = array();
	
     // include db connect class
     require_once  'database/db_connect.php';
	
	// array for JSON response
    $response = array();
     // connecting to db
    $db= new DB_CONNECT();
	
   if(isset($name)){
   
			
$query="insert into insertTb (name ,email ,address,phone) value ('$name','$email','$address','$mobile')";

$result = mysqli_query($db->connect(), $query);
	
	if(isset($result))
{
	 // successfully 
        $response["success"] = true;
		$response["error"]="";
         
        // echoing JSON response
        
        echo json_encode($response);
}
else
{
	errorMessage("value is not saved");
}
	
}
else
{
	errorMessage("enter all value");
}
   function errorMessage($message)
   {
	  $response["success"] = false;
		$response["error"]=$message;
         
        // echoing JSON response
        
        echo json_encode($response);  
   }
	
?>
