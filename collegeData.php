<?php 

	$db_name = 'mydb';
	$db_user = 'root';
	$db_pass = '';
	$db_host = 'localhost'; 
	
	//connecting to database and getting the connection object
	$conn = mysqli_connect($db_host, $db_user, $db_pass,$db_name);

	//Checking if any error occured while connecting
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		die();
	}
	
	//creating a query
	$stmt = $conn->prepare("SELECT college_code,college_name,college_website,college_location,college_cet_score,image FROM college_info;");
	
	//executing the query 
	$stmt->execute();
	
	//binding results to the query 
	$stmt->bind_result($college_code, $college_name, $college_website, $college_location, $college_cet_score,$image );
	
	$products = array(); 
	
	//traversing through all the result 
	while($stmt->fetch()){
		$temp = array();
		$temp['college_code'] = $college_code; 
		$temp['college_name'] = $college_name; 
		$temp['college_website'] = $college_website; 
		$temp['college_location'] = $college_location; 
		$temp['college_cet_score'] = $college_cet_score; 
		$temp['image'] = $image; 
		array_push($products, $temp);
	}
	
	//displaying the result in json format 
	echo json_encode($products);