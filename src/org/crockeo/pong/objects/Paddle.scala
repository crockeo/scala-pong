package org.crockeo.pong.objects

import org.lwjgl.opengl.GL11.GL_QUADS

import org.crockeo.pong.geom.Rectangle
import org.crockeo.pong.geom.Vector
import org.crockeo.pong.InputConfig
import org.crockeo.pong.InputState
import org.crockeo.pong.State
import org.crockeo.pong.Game

class Paddle(pos: Vector, size: Vector, val inputConfig: InputConfig, val accelRate: Float, val currSpeed: Float, val maxSpeed: Float) extends Rectangle(pos, size)
		with Updateable {
  override def translate(dir: Vector): Paddle =
    new Paddle(pos + dir, size, inputConfig, accelRate, currSpeed, maxSpeed)
  
  // Updateable
  override def update(dt: Float, s: State): Paddle = {
    def move(is: InputState): Paddle = {
      // TODO: Fix never stopping
	  def accelerate: Paddle = {
	    def near(n: Float, target: Float, margin: Float) =
	      (n >= target - margin) && (n <= target + margin)
	    
	    def accelRaw(dt: Float): Paddle =
	      new Paddle(pos, size, inputConfig, accelRate, dt, maxSpeed)
	    
	    if (is.up)
	      if (Math.abs(currSpeed - accelRate * dt) > maxSpeed)       accelRaw(-maxSpeed)
	      else                                                       accelRaw(currSpeed - accelRate * dt)
	    else if (is.down)
	      if (Math.abs(currSpeed + accelRate * dt) > maxSpeed)       accelRaw( maxSpeed)
	      else                                                       accelRaw(currSpeed + accelRate * dt)
	    else
	      if      (currSpeed < 0)                                    accelRaw(currSpeed + accelRate * dt)
	      else if (currSpeed > 0)                                    accelRaw(currSpeed - accelRate * dt)
	      else                                                       accelRaw(currSpeed)
	  }
	  
	  accelerate.translate(new Vector(0, dt * currSpeed))
    }
    
    def bound(p: Paddle): Paddle = {      
      if (p.pos.y < 0)                            new Paddle(new Vector(p.pos.x, 0), p.size, p.inputConfig, accelRate, 0, maxSpeed)
      else if ((p.pos + p.size).y > s.gameSize.y) new Paddle(new Vector(p.pos.x, s.gameSize.y - p.size.y), p.size, p.inputConfig, accelRate, 0, maxSpeed)
      else                                        p
    }
    
    bound(move(inputConfig.getInputState))
  }
}