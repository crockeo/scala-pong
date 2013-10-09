package org.crockeo.pong

import org.crockeo.pong.objects.Renderable
import org.crockeo.pong.objects.Paddle
import org.crockeo.pong.objects.Ball
import org.crockeo.pong.geom.Vector

import org.crockeo.pong.geom.Rectangle

class State(val running: Boolean, val gameSize: Vector, val lPaddle: Paddle, val rPaddle: Paddle, val ball: Ball) {  
  def update(dt: Float): State =
    new State(running, gameSize, lPaddle.update(dt, this), rPaddle.update(dt, this), ball.update(dt, this))
  def getRenderables: List[Renderable] =
    List(lPaddle, rPaddle, ball)
}

object State {
  private val paddleSize = new Vector(20, 70)
  
  def createState(cfg: Config): State =
    new State(true,                                                                                                                                // Running
              new Vector(cfg.w, cfg.h),                                                                                                            // Game Size
    		  new Paddle(new Vector(         paddleSize.x     , (cfg.h / 2) - (paddleSize.y / 2)), paddleSize, new InputConfig(cfg.p1u, cfg.p1d)), // Left paddle
    		  new Paddle(new Vector(cfg.w - (paddleSize.x * 2), (cfg.h / 2) - (paddleSize.y / 2)), paddleSize, new InputConfig(cfg.p2u, cfg.p2d)), // Right paddle
    		  new Ball(new Vector(cfg.w / 2, cfg.h / 2), Vector.randomDirectionPair, 12.5f))                                                       // Ball
}