package dataconn

import slick.jdbc.H2Profile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await

class NA_data_stats(tag: Tag) extends Table[(String,Long,Long,Long,String,Long)](tag, "na_data_statistics") {
  def reason = column[String]("reason")
  def total = column[Long]("total")
  def rejected = column[Long]("rejected")
  def repTs = column[Long]("evt_ts")
  def interface = column[String]("interface")
  def evtTs = column[Long]("evt_ts")
  def * = (reason,total,rejected,repTs,interface,evtTs)
}

class NA_control_plane_kpi(tag: Tag) extends Table[(String,String,String)](tag, "na_control_plane_kpi") {
  def siteName = column[String]("site_name")
  def longitude = column[String]("longitude")
  def latitude = column[String]("latitude")
/*  
	def cluster = column[String]("cluster")
  def circle = column[String]("circle")
  def imsi = column[String]("imsi")  
  def msisdn = column[String]("msisdn")
  def subscriberClass = column[String]("subscriber_class")
  def subscriberPlan = column[String]("subscriber_plan")
  def subscriberCategory = column[String]("subscriber_category")
  def handsetType = column[String]("handset_type")
  def handsetModel = column[String]("handset_model")
  def reportReason = column[String]("report_reason")
  def causeCode = column[String]("cause_code")
  def enbId = column[String]("enb_id")
  def mme = column[String]("mme")
  def sgw = column[String]("sgw")
  def eci = column[String]("eci")
  def tac = column[String]("tac")
  def roamerType = column[String]("roamer_type")
  def rat = column[String]("rat")
  def apn = column[String]("apn")
  def duratio = column[Long]("duration")
  def succeeded = column[Int]("succeeded")
  def causeCategory = column[String]("cause_category")
  def hoSucceeded = column[Int]("ho_succeeded")  
  def hoAttempt = column[Int]("ho_attempt")  
  def eventCount = column[Int]("event_count")  
  def interface = column[String]("interface")
  def evtTs = column[Long]("evt_ts")
*/
  def * = (siteName,longitude,latitude)
}


object Hive2Slick extends HiveConf {
  
  val na_control_plane_kpi = TableQuery[NA_control_plane_kpi]
  val na_data_stats_tbl = TableQuery[NA_data_stats]
  
  def main(args: Array[String]): Unit = {
    
    Class.forName(driverName)
    println("=============== Hive2Slick starts =================")
	  val db = Database.forURL(connString, driverName)


/*	  
	  db.run(na_data_stats_tbl.result).map(_.foreach {
	    case _ => println("record ...")
	  })
*/
	  
	  db.run(sql"select * from na_control_plane_kpi limit 10".as[String]).map(println)
	  // explicitly shut it down
	  db.close()
  }
}