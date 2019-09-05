<?php 
$db_name = 'mydb';
$db_user = 'root';
$db_pass = '';
$db_host = 'localhost'; 
$response = array();
$inputJSON = file_get_contents('php://input');
$input = json_decode($inputJSON, TRUE); //convert JSON into array
$conn = mysqli_connect($db_host, $db_user, $db_pass,$db_name);
//$user_email = $input['email'];
$user_username = $input['username'];  //passes from android 
//$user_mobile = $input['mobile'];
$user_password = $input['password'];
$user_full_name = $input['full_name'];
if (!$conn ) 
{
    $response["status"] = 3;
	$response["message"] = "Login Error";	
}
else
{
		$query = "INSERT INTO personal_info VALUES ('$user_username','$user_password','$user_full_name')";		
		$result = mysqli_query($conn,$query);  //has to change
		
		
		if(!$result)
		{
			$response["status"] = 0;
			$response["message"] = "Error registering the user";
		}
		else
		{
			$response["status"] = 1;
			$response["message"] = "Registration successful";
			
		}				
				mysqli_close($conn);
}
echo json_encode($response);
?>
