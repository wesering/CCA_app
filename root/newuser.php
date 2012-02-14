<?php
header("Content-type: text/xml");
$host = "localhost";
$user = "657027_ccaweb";
$pass ="bowles492";
$database = "ccagency_99k_ccadb";
$iusername = $_POST['iuser'];
$ipassword = $_POST['ipass'];
 
$iname = $_POST['iname'];
$iaddress = $_POST['iaddress'];
$iemail = $_POST['iemail'];
$iphone = $_POST['iphone'];
  

$con = mysql_connect($host,$user,$pass) or die ("Connection to DB host failed: Invalid Config");
mysql_select_db($database, $con) or die ("Database not found");

$query = sprintf("INSERT INTO usr_detail(user_name, address_line, city, state, zip, email, phone, work_phone)
				  VALUES ('%s',
						  '%s',
						  '',
						  '',
						  '',
						  '%s',
						  '%s',
						  '1234567890')",  
						  mysql_real_escape_string($iname),
						  mysql_real_escape_string($iaddress),
						  mysql_real_escape_string($iemail),
					      mysql_real_escape_string($iphone));
mysql_query($query, $con) or die ("Insert into usr_detail failed. " . $query);
$query = sprintf("INSERT INTO sec_user(username, password, dte_created, user_level)
				  VALUES ('%s',
						  '%s',
						  '01/01/12',
						  'USER')",
						  mysql_real_escape_string($iusername),
						  mysql_real_escape_string($ipassword));
mysql_query($query, $con) or die ("Insert into sec_user failed." . $query);				   
$query = "select MAX(user_id) AS user_id FROM sec_user";				   
$resultID = mysql_query($query, $con) or die ("Data not found");
if (mysql_errno()) { 
    header("HTTP/1.1 500 Internal Server Error");
    echo $query.'\n';
    echo mysql_error(); 
}
else{
	// we produce XML
	header("Content-type: text/xml");
	$XML = "<?xml version=\"1.0\"?>\n";
	 
	// root node
	$XML .= "<result>\n";
	// rows
	while ($row = mysql_fetch_array($resultID, MYSQL_ASSOC)) {    
	  $XML .= "\t<row>\n"; 
	  $i = 0;
	  // cells
	  foreach ($row as $cell) {
		// Escaping illegal characters - not tested actually ;)
		$cell = str_replace("&", "&amp;", $cell);
		$cell = str_replace("<", "&lt;", $cell);
		$cell = str_replace(">", "&gt;", $cell);
		$cell = str_replace("\"", "&quot;", $cell);
		$col_name = mysql_field_name($resultID, $i);
		// creates the "<tag>contents</tag>" representing the column
		$XML .= "\t\t<" . $col_name . ">" . $cell . "</" . $col_name . ">\n";
		$i++;
	  }
	  $XML .= "\t</row>\n"; 
	 }
	$XML .= "</result>\n";
		
  print $XML;
  mysql_close($con);
  //print json_encode($resultID);
}
?>