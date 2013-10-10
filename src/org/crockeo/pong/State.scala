package org.crockeo.pong

import org.crockeo.pong.objects.{Paddle, Ball}
import org.crockeo.pong.geom.{Rectangle, Vector}

// TODO: Implement score changing

class State(val running: Boolean, val gameSize: Vector, val score: Score, val lPaddle: Paddle, val rPaddle: Paddle, val ball: Ball) {
  // Updating the state
  def update(dt: Float): State = 
    new State(running, gameSize, score, lPaddle.update(dt, this), rPaddle.update(dt, this), ball.update(dt, this))
  
  // Getting a list of renderables in the state
  def getRenderables: List[Renderable] =
    List(score, lPaddle, rPaddle, ball)
}

object State {
  private val paddleSize = new Vector(20, 70)
  private val accelSpeed: Float = 650
  private val maxSpeed: Float = 300
  
  def createState(cfg: Config): State =
    new State(true,
              new Vector(cfg.w, cfg.h),
              new Score (    0,     0),
              new Paddle(new Vector(         paddleSize.x     , (cfg.h / 2) - (paddleSize.y / 2)), paddleSize, new InputConfig(cfg.p1u, cfg.p1d), accelSpeed, 0, maxSpeed),
              new Paddle(new Vector(cfg.w - (paddleSize.x * 2), (cfg.h / 2) - (paddleSize.y / 2)), paddleSize, new InputConfig(cfg.p2u, cfg.p2d), accelSpeed, 0, maxSpeed),
              new Ball  (new Vector(cfg.w / 2, cfg.h / 2)     , Vector.randomDirectionPair       , 12.5f))
}