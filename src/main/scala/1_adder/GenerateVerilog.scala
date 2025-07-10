package adder

import chisel3._
import circt.stage.ChiselStage
import java.io.{File, FileWriter}

object GenerateVerilog extends App {
  // Create directory if it doesn't exist
  val targetDir = "generated"
  val dir = new File(targetDir)
  if (!dir.exists()) dir.mkdirs()
  
  // Generate Verilog for different bit widths
  def generateVerilog(width: Int, name: String): Unit = {
    val verilog = ChiselStage.emitSystemVerilog(new Adder(width))
    
    val writer = new FileWriter(s"${targetDir}/${name}.v")
    writer.write(verilog)
    writer.close()
    
    println(s"${width}-bit Adder Verilog generated: ${targetDir}/${name}.v")
  }
  
  // Generate different versions
  generateVerilog(8, "Adder8bit")
  generateVerilog(16, "Adder16bit")
  generateVerilog(32, "Adder32bit")
  
  // Also print a 4-bit version to console for quick viewing
  println("\n--- 4-bit Adder Verilog (console output) ---")
  println(ChiselStage.emitSystemVerilog(new Adder(4)))
}