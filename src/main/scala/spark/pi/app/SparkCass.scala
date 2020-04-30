package spark.pi.app

import org.apache.spark.sql.SparkSession
import com.datastax.spark.connector._
import org.apache.log4j.{Level, LogManager, Logger}


final case class Person(firstName: String, lastName: String,
                        country: String, age: Int)

/** Computes an approximation to pi */
object SparkCass {
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .appName("Spark Pi")
      .config("spark.cassandra.connection.host", "localhost")
      .getOrCreate()

    import spark.implicits._

    spark.sparkContext.setLogLevel("ERROR")
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("akka").setLevel(Level.ERROR)
    LogManager.getRootLogger.setLevel(Level.ERROR)
    println("SPARK VERSION = " + spark.version)

    val sc = spark.sparkContext

    val rows = sc.cassandraTable("cass_drop", "person")
      .filter(r => r.getString("email") == "testj@testj.com")
    println(rows.count())
    println(rows.partitions.size)

    spark.stop()
  }
}
