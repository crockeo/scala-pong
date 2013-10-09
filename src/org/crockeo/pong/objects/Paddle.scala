package org.crockeo.pong.objects

import org.lwjgl.opengl.GL11.GL_QUADS

import org.crockeo.pong.geom.Rectangle
import org.crockeo.pong.geom.Vector
import org.crockeo.pong.InputConfig
import org.crockeo.pong.InputState
import org.crockeo.pong.State
import org.crockeo.pong.Game

class Paddle(pos: Vector, size: Vector, val inputConfig: InputConfig) extends Rectangle(pos, size)
		with Updateable {
  // Updateable
  override def update(dt: Float, s: State): Paddle = {
    def rawMove(is: InputState): Paddle = {
      var newPos = pos
      
      if (is.up) newPos = pos + (Vector.up * Game.paddleSpeed * dt)
      if (is.down) newPos = pos + (Vector.down * Game.paddleSpeed * dt)
      
      new Paddle(newPos, size, inputConfig)
    }
    
    def bound(p: Paddle): Paddle = {      
      if (p.pos.y < 0)                            new Paddle(new Vector(p.pos.x, 0), p.size, p.inputConfig)
      else if ((p.pos + p.size).y > s.gameSize.y) new Paddle(new Vector(p.pos.x, s.gameSize.y - p.size.y), p.size, p.inputConfig)
      else                                        p
    }
    
    bound(rawMove(inputConfig.getInputState))
  }
}