<?php 
$db_name = 'mydb';
$db_user = 'root';
$db_pass = '';
$db_host = 'localhost'; 
$response = array();
$inputJSON = file_get_contents('php://input');
$input = json_decode($inputJSON, TRUE); //convert JSON into array
$conn = mysqli_connect($db_host, $db_user, $db_pass,$db_name);

//input taken from android Studio

$college_code = $input['code'];
$college_name = $input['name'];  
$college_website = $input['website'];
$college_location = $input['location'];
$college_cet_score = $input['cet_cutoff'];
$Stream = $input['stream'];
$Category = $input['category'];
if (!$conn ) 
{
    $response["status"] = 3;
	$response["message"] = "Login Error";	
}
else
{
		//$query =  "INSERT INTO college_info (`college_code`, `college_name`, `college_website`, `college_location`, `college_cet_score`, `Stream`, `Category`,'image') VALUES ('$college_code','$college_name','$college_website','$college_location' ,'$college_cet_score','$Stream' ,'$Category','ss')";
		$query = "INSERT INTO college_info (`college_code`, `college_name`, `college_website`, `college_location`, `college_cet_score`, `Stream`, `Category`,`image`) VALUES ('$college_code','$college_name','$college_website','$college_location' ,'$college_cet_score','$Stream' ,'$Category','http://192.168.137.1/college_images/mit.jpg')";
		$result = mysqli_query($conn,$query);  
		
		
		if(!$result)
		{
			$response["status"] = 0;
			$response["message"] = "Error in Update the data";
		}
		else
		{
			$response["status"] = 1;
			$response["message"] = "Update Successfully";
			
		}				
				mysqli_close($conn);
}
echo json_encode($response);
?>


