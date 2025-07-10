package adder

import chisel3._
import chisel3.simulator.EphemeralSimulator._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AdderTest extends AnyFlatSpec with Matchers {
  behavior of "Adder"

  it should "add two numbers correctly without carry" in {
    simulate(new Adder(8)) { dut =>
      dut.io.a.poke(5.U)
      dut.io.b.poke(3.U)
      dut.clock.step(1)
      dut.io.sum.expect(8.U)
      dut.io.cout.expect(false.B)
    }
  }

  it should "add two numbers correctly with carry" in {
    simulate(new Adder(8)) { dut =>
      dut.io.a.poke(255.U)
      dut.io.b.poke(1.U)
      dut.clock.step(1)
      dut.io.sum.expect(0.U)
      dut.io.cout.expect(true.B)
    }
  }
}