<?php 
$db_name = 'mydb';
$db_user = 'root';
$db_pass = '';
$db_host = 'localhost'; 
$response = array();
$inputJSON = file_get_contents('php://input');
$input = json_decode($inputJSON, TRUE); //convert JSON into array
$conn = mysqli_connect($db_host, $db_user, $db_pass,$db_name);
	
$user_username = $input['username'];      //changes from the android 
$user_password = $input['password'];
if (!$conn ) 
{
    $response["status"] = 3;
	$response["message"] = "Login Error";	
}
else
{
		$query = "SELECT * FROM  personal_info WHERE username = '$user_username' AND password ='$user_password'";		
		$retval = mysqli_query($conn,$query);
		
		
		if(mysqli_num_rows($retval)<=0)
		{
			$response["status"] = 0;
			$response["message"] = "Email or passWord is incorrect";
		}
		else
		{
		    while($row = mysqli_fetch_assoc($retval)) 
			{ 
			    $username=$row['username'];
			}
			$response["status"] = 1;
			$response["username"] = $username;
			$response["message"] = "Login successful";
			
		}				
				mysqli_close($conn);
}
echo json_encode($response);
?>