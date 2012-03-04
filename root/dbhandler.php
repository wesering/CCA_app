<?php
header("Content-type: text/xml");
$host = "localhost";
$user = "657027_ccaweb";
$pass ="bowles492";
$database = "ccagency_99k_ccadb";
$linkID = mysql_connect($databasehost,$databaseusername,$databasepassword) or die(mysql_error());
mysql_select_db($databasename) or die(mysql_error());
$query = file_get_contents("php://input"); 
$resultID = mysql_query($query, $linkID) or die ("Data not found");
if (mysql_errno()) { 
    header("HTTP/1.1 500 Internal Server Error");
    echo $query.'\n';
    echo mysql_error(); 
}
else{
   $xml_output = "<?xml version=\"1.0\"?>\n"; 
   $xml_output .= "<entries>\n"; 

for($x = 0 ; $x < mysql_num_rows($resultID) ; $x++){ 
    $row = mysql_fetch_assoc($resultID); 
    $xml_output .= "\t<entry>\n"; 
    $xml_output .= "\t\t<date>" . $row['date'] . "</date>\n"; 
        // Escaping illegal characters 
        $row['text'] = str_replace("&", "&", $row['text']); 
        $row['text'] = str_replace("<", "<", $row['text']); 
        $row['text'] = str_replace(">", "&gt;", $row['text']); 
        $row['text'] = str_replace("\"", "&quot;", $row['text']); 
    $xml_output .= "\t\t<text>" . $row['text'] . "</text>\n"; 
    $xml_output .= "\t</entry>\n"; 
    } 

    $xml_output .= "</entries>"; 



   


  //  $rows = array();
  //  while($r = mysql_fetch_assoc($sth)) {
  //  $rows[] = $r;
  //  }
  print json_encode($resultID);
  //print json_encode($xml_output);
}
?>