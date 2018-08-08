package dataconn

import java.sql.{DriverManager,Connection}

trait HiveConf {
  val driverName = "org.apache.hive.jdbc.HiveDriver"
  val connString = "jdbc:hive2://localhost:10000/default;user=mapr"
}

object HiveClient extends HiveConf {
  
  def main(args: Array[String]): Unit = {
    // set up connection -> HiveDB
    Class.forName(driverName)
    val conn = DriverManager.getConnection(connString)
    
    // create sql-statement
    val sqlStatement = conn.createStatement()
    
    // run sql query
    val ret = sqlStatement.executeQuery("select site_name,imsi,report_reason from na_control_plane_kpi limit 10")
    println("site_name.idx=" + ret.findColumn("site_name"))
    println("imsi.idx     =" + ret.findColumn("imsi"))
    println("report_reason=" + ret.findColumn("report_reason"))
    while (ret.next()) {
      println(ret.getString("site_name") + ", " + ret.getString("imsi") + ", " + ret.getString("report_reason"))
    }
    
    
    conn.close()
  }
}