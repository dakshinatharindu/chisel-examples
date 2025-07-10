package adder

import chisel3._
import circt.stage.ChiselStage
import java.io.{File, FileWriter}

object GenerateVerilog extends App {
  // Create directory if it doesn't exist
  val targetDir = "generated"
  val dir = new File(targetDir)
  if (!dir.exists()) dir.mkdirs()
  
  val verilog = ChiselStage.emitSystemVerilog(new Adder(8))
  val verilogFile = new File(s"$targetDir/Adder.v")
  val writer = new FileWriter(verilogFile)
  writer.write(verilog)
  writer.close()
}