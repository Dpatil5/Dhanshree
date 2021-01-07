<?php

session_start();

$_SESSION["err"] = "";
if (isset($_POST["submit"])) {

    $username = $_POST["txtUsername"];
    $password = $_POST["txtPassword"];

    $host = "localhost";
    $user = "root";
    $pass = "";
    $database = "solid";

    $connection_String = mysqli_connect($host, $user, $pass, $database);

    $username = mysqli_real_escape_string($connection_String, $username);
    $password = mysqli_real_escape_string($connection_String, $password);


    $get_current_user_details = "SELECT * FROM users_table WHERE user_fname = '$username' AND Password = '$password' LIMIT 1";
    $execute_user_details_command = mysqli_query($connection_String, $get_current_user_details);
    $get_additional_info = mysqli_fetch_assoc($execute_user_details_command);

    $users_last_name = $get_additional_info["user_lname"];
    $users_department = $get_additional_info["department"];
    $users_position = $get_additional_info["position"];


    $command_query = "SELECT * FROM users_table WHERE user_fname = '$username' AND Password = '$password' AND user_lname = '$users_last_name'";

    $execute_command_query = mysqli_query($connection_String, $command_query);

    $user_validity = mysqli_num_rows($execute_command_query);

    if ($user_validity > 0) {

        $checking_online_status = "SELECT * FROM users_online WHERE first_name='$username' AND last_name = '$users_last_name'";

        $execute_checking_online_status = mysqli_query($connection_String, $checking_online_status);

        $online_status_validity = mysqli_num_rows($execute_checking_online_status);

        if ($online_status_validity > 0) {

            setcookie("user_first_name", $username, time() + (86400 * 30));
            setcookie("users_last_name", $users_last_name, time() + (86400 * 30));
            setcookie("user_department", $users_department, time() + (86400 * 30));
            setcookie("user_position", $users_position, time() + (86400 * 30));

            setcookie("default_clicked_on_username", "Welcome", time() + (86400 * 30));

            setcookie("clicked_on_user_last_name", $users_last_name, time() + (86400 * 30));

            setcookie("Selected_Username_Table", "dummy_text", time() + (86400 * 30));
            setcookie("Reversed_selected_Username_Table", "dummy_text", time() + (86400 * 30));

            setcookie("selected_Username_Table_uploads", "dummy_text", time() + (86400 * 30));
            setcookie("reversed_selected_Username_Table_uploads", "dummy_text", time() + (86400 *
                30));

            $update_status_command =
                "UPDATE `users_online` SET status = 'online' WHERE first_name ='$username' AND last_name = '$users_last_name'";
            $execute_status_command = mysqli_query($connection_String, $update_status_command);


            header("Location:./Dashboard/Main_Dashboard.php");

        } else {

            $insert_command = "INSERT INTO users_online (`ID`, `first_name`,`last_name`, `Time`,`status`) VALUES (NULL, '$username','$users_last_name', CURRENT_TIMESTAMP,'online')";

            $execute_insert_command = mysqli_query($connection_String, $insert_command);

            $UserID = mysqli_insert_id($connection_String);

            mysqli_close($connection_String);

            setcookie("user_first_name", $username, time() + (86400 * 30));
            setcookie("users_last_name", $users_last_name, time() + (86400 * 30));
            setcookie("user_department", $users_department, time() + (86400 * 30));
            setcookie("user_position", $users_position, time() + (86400 * 30));

            setcookie("default_clicked_on_username", "Welcome", time() + (86400 * 30));

            setcookie("clicked_on_user_last_name", $users_last_name, time() + (86400 * 30));

            setcookie("Selected_Username_Table", "dummy_text", time() + (86400 * 30));
            setcookie("Reversed_selected_Username_Table", "dummy_text", time() + (86400 * 30));

            setcookie("selected_Username_Table_uploads", "dummy_text", time() + (86400 * 30));
            setcookie("reversed_selected_Username_Table_uploads", "dummy_text", time() + (86400 *
                30));

            header("Location:./Dashboard/Main_Dashboard.php");
        }

    } else {
        $_SESSION["err"] = "Wrong Username or Password";
    }
    ;
} else {

}
;

?>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <link 
    rel="stylesheet" 
    href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    />

    <link rel="stylesheet" href="part1.css">
    <title>Sign-In/Sign-Up form</title>
</head>
<body>
    <div class="container" id="container">
        <div class="form-container sign-up-container">
            <form id='form' method="post">
                <h1>Create Account</h1>
               
                <div class="form-control">
                <input type="text" placeholder="Name" id="username" name="txtfname"/>
                <i class="fa fa-check-circle" aria-hidden="true"></i>
                <i class="fa fa-exclamation-circle" aria-hidden="true"></i>
               
            </div>
            <div class="form-control">
                <input type="text" placeholder="Last name" id="email" name="txtlname" />
                <i class="fa fa-check-circle" aria-hidden="true"></i>
                <i class="fa fa-exclamation-circle" aria-hidden="true"></i>

                
            </div>
            <div class="form-control">
                <input type="text" placeholder="Job/Startup/PG" id="username" name="selected_position_option"/>
                <i class="fa fa-check-circle" aria-hidden="true"></i>
                <i class="fa fa-exclamation-circle" aria-hidden="true"></i>
               
            </div>

            <div class="form-control">
                <input type="text" placeholder="Department" id="username" name="selected_department_option"/>
                <i class="fa fa-check-circle" aria-hidden="true"></i>
                <i class="fa fa-exclamation-circle" aria-hidden="true"></i>
                
            </div>

            <div class="form-control">
                <input type="password" placeholder="Password" id="password" name="txtpassword" />
                <i class="fa fa-check-circle" aria-hidden="true"></i>
                <i class="fa fa-exclamation-circle" aria-hidden="true"></i>
                
            </div>
             <div class="form-control">
                <input type="text" placeholder="Security Key" id="username" name="txtsecurity_key />
                <i class="fa fa-check-circle" aria-hidden="true"></i>
                <i class="fa fa-exclamation-circle" aria-hidden="true"></i>
                
            </div>
           
                <button name="submitt">Submit</button>
            </form>
        </div>
        <div class="form-container sign-in-container">
            <form method="post">
                <h1>Sign in</h1>
                <div class="social-container">
                    <a href="https://accounts.google.com/AccountChooser?service=lso" class="social"><i class="fa fa-google" aria-hidden="true"></i></a>
                    <a href="https://www.icloud.com/" class="social"><i class="fa fa-apple" aria-hidden="true"></i></a>
                </div>
                <span>or use your account</span>
                <input type="text" placeholder="Email" name="txtUsername" />
                <input type="password" placeholder="Password" name="txtPassword" />
                <a href="#">Forgot your password?</a>
                <button name="submit">Sign In</button>
            </form>
        </div>
         <label id="lf--errormsg"><?php echo ($_SESSION["err"]); ?></label>
        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
                    <h1>Welcome Back!</h1>
                    <p>To keep connected with us please login with your personal info</p>
                    <button class="ghost" id="signIn">Sign In</button>
                </div>
                <div class="overlay-panel overlay-right">
                    <h1>Hello, Alumni!</h1>
                    <p>Enter your personal details and start journey with us</p>
                    <button class="ghost" id="signUp">Sign Up</button>
                </div>
            </div>
        </div>
    </div>
    <script src="part1.js"></script>
</body>
</html>
<?php

  if(isset($_POST["submitt"])){

    $first_name = $_POST["txtfname"];
    $last_name = $_POST["txtlname"];
    $user_password = $_POST["txtpassword"];
    $user_position = $_POST["selected_position_option"];
    $user_department = $_POST["selected_department_option"];
    $security_key = $_POST["txtsecurity_key"];

    if($first_name!="" && $last_name!="" && $user_password!="" && $user_position!="" && $user_department!="" && $security_key!=""){

      $security_key_check_command = "SELECT * FROM users_security_keys WHERE password = '$security_key' AND users_fname='$first_name' AND users_lname = '$last_name'";
      $execute_check_command = mysqli_query($connection_String,$security_key_check_command);
      $check_user_validity_status = mysqli_num_rows($execute_check_command);
      if($check_user_validity_status==1){
        $check_validity_status = mysqli_fetch_assoc($execute_check_command);
      if($check_validity_status["status"] == "not_used"){
        $register_user_command = "INSERT INTO users_table (`Users_ID`, `user_fname`, `user_lname`, `department`, `position`, `Password`, `Profile_Picture`) VALUES (NULL, '$first_name', '$last_name', '$user_department', '$user_position', '$user_password', '')";
        if($execute_register_command = mysqli_query($connection_String,$register_user_command)){
        $update_user_status_command = "UPDATE users_security_keys SET status = 'used' WHERE users_fname='$first_name' AND users_lname = '$last_name'";
        $execute_update_command = mysqli_query($connection_String,$update_user_status_command);
        echo "<script>alert('You have registered Successfully')</script>";
      }else{
        "<script>alert('Sorry, You cannot be registered, Contact The Administrator')</script>";
      }
      }else{
        echo "<script>alert('Sorry, You have already registered')</script>";
      }
    }else{
        echo "<script>alert('Please Contact Adminstrator For A Valid Security Key')</script>";
    }
  }else{
    echo "<script>alert('Please Fill All Spaces Provided')</script>";
  }
}
 ?>



