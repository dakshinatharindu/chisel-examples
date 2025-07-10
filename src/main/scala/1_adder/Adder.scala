package adder

import chisel3._
import chisel3.util._

class Adder(width: Int = 32) extends Module {
  val io = IO(new Bundle {
    val a = Input(UInt(width.W))
    val b = Input(UInt(width.W))
    val sum = Output(UInt(width.W))
    val cout = Output(Bool())
  })

  // Perform addition with carry out
  val result = io.a +& io.b
  io.sum := result(width-1, 0)
  io.cout := result(width)
}