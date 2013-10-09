package org.crockeo.pong

import org.lwjgl.input.Keyboard
import java.io._

class Config(val w: Int, val h: Int,
			 val p1u: Int, val p1d: Int,
			 val p2u: Int, val p2d: Int) {
  
  override def toString =
    Config.wParseString + " " + w + "\n" +
    Config.hParseString + " " + h + "\n" +
    Config.p1uParseString + " " + p1u + "\n" +
    Config.p1dParseString + " " + p1d + "\n" +
    Config.p2uParseString + " " + p2u + "\n" +
    Config.p2dParseString + " " + p2d
}

object Config {
  // Parse strings
  val wParseString = "w"
  val hParseString = "h"
  val p1uParseString = "p1u"
  val p1dParseString = "p1d"
  val p2uParseString = "p2u"
  val p2dParseString = "p2d"
   
  // The default location of the config file
  val defaultLocation = "config.cfg"
    
  // The default configuration
  val defaultConfig = new Config(640, 480,
		  						 Keyboard.KEY_W,  Keyboard.KEY_S,
		  						 Keyboard.KEY_UP, Keyboard.KEY_DOWN)
  
  // Reading a config from a file
  def loadConfig(path: String): Config = {
    // Reading a list of lines from a file
    def readLines(br: BufferedReader): List[String] =
      if (!br.ready) { br.close; List() }
      else             br.readLine +: readLines(br)
    
    // Iterating through a list of strings to modify the config
    def loadConfigIter(s: List[String], cfg: Config): Config = {
        
      // Parsinga  single line
      def parseLine(s: List[String], cfg: Config): Config = {
        def cfgVal = s.apply(1).toInt
        
        if (s.isEmpty) cfg
        else s.head match {
          case `wParseString`   => new Config(cfgVal, cfg.h , cfg.p1u, cfg.p1d, cfg.p2u, cfg.p2d)
          case `hParseString`   => new Config(cfg.w , cfgVal, cfg.p1u, cfg.p1d, cfg.p2u, cfg.p2d)
          case `p1uParseString` => new Config(cfg.w , cfg.h , cfgVal , cfg.p1d, cfg.p2u, cfg.p2d)
          case `p1dParseString` => new Config(cfg.w , cfg.h , cfg.p1u, cfgVal , cfg.p2u, cfg.p2d)
          case `p2uParseString` => new Config(cfg.w , cfg.h , cfg.p1u, cfg.p1d, cfgVal , cfg.p2d)
          case `p2dParseString` => new Config(cfg.w , cfg.h , cfg.p1u, cfg.p1d, cfg.p2u, cfgVal )
          case default          => cfg
        }
      }
      
      if (s.isEmpty) cfg
      else           loadConfigIter(s.tail, parseLine(s.head.split(" ").toList, cfg))
    }
    
    val f = new File(path)
    if (!f.exists) writeConfig(path, defaultConfig)
    loadConfigIter(readLines(new BufferedReader(new FileReader(f))), defaultConfig)
  }
  
  // Writing a config to a file
  def writeConfig(path: String, cfg: Config) = {
    def writeRaw(bw: BufferedWriter, str: String) = { bw.write(str); bw.close }
    writeRaw(new BufferedWriter(new FileWriter(new File(path))), cfg.toString)
  }
}