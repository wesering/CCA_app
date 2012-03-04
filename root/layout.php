<?php
#Connects to Database
mysql_connect("localhost","657027_ccadmin","bowles");
mysql_select_db("ccagency_99k_ccadb");

#Selects Query
$sql=mysql_query("SELECT * FROM sys_contact");

#Ouputs in JSON Format
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));

mysql_close();
?>
