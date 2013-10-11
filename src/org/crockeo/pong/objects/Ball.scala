package org.crockeo.pong.objects

import org.lwjgl.opengl.GL11.GL_POLYGON

import org.crockeo.pong.geom.{Rectangle, Vector}
import org.crockeo.pong.{Renderable, Renderer, State, Game}

class Ball(val pos: Vector, val dir: Vector, val radius: Float) extends Renderable
		with Updateable {
  // The collision rectangle for the ball
  val collisionRect = new Rectangle(new Vector(pos.x - radius, pos.y - radius), new Vector(radius * 2, radius * 2))
  
  // The render detail of the ball
  val detail = 24
  
  // Resetting the position of the ball
  def resetPos(s: State) =
    new Ball(s.gameSize / 2, Vector.randomDirectionPair, radius)
  
  // Translating the ball
  def translate(dir: Vector) =
    new Ball(pos + dir, this.dir, radius)
  
  // Resizing the ball
  def resize(r: Float) =
    new Ball(pos, this.dir, radius + r)
  
  override def toString: String =
    "Pos: " + pos + "\n" +
    "Radius: " + radius
     
  // Renderable
  def render(s: State): Unit = {
    def generateRenderPoints(c: Float): List[Vector] = {
      def fCos(f: Float): Float = Math.cos(f.toDouble.toRadians).toFloat
      def fSin(f: Float): Float = Math.sin(f.toDouble.toRadians).toFloat
      
      if (c >= 360) List()
      else          new Vector(pos.x + fCos(c) * radius, pos.y + fSin(c) * radius) +: generateRenderPoints(c + (360 / detail))
    }
    
    Renderer.renderVertecies(generateRenderPoints(0), GL_POLYGON)
  }
  
  // Updateable
  def update(dt: Float, s: State): Ball = {
    def gameBound(b: Ball): Ball =
      if (b.pos.y - b.radius < 0)                 new Ball(new Vector(b.pos.x,                b.radius), dir.reverseY, radius)
      else if (b.pos.y + b.radius > s.gameSize.y) new Ball(new Vector(b.pos.x, s.gameSize.y - b.radius), dir.reverseY, radius)
      else if (b.pos.x - (b.radius / 2) < 0)            { s.score.shouldScoreForRightNextUpdate; resetPos(s) }
      else if (b.pos.x + (b.radius / 2) > s.gameSize.x) { s.score.shouldScoreForLeftNextUpdate; resetPos(s) }
      else                                        b
          
    def paddleBound(b: Ball): Ball =
      if (b.collisionRect.intersects(s.lPaddle))      new Ball(b.pos, new Vector( 1, b.dir.y), radius)
      else if (b.collisionRect.intersects(s.rPaddle)) new Ball(b.pos, new Vector(-1, b.dir.y), radius)
      else                                            b
    
    gameBound(paddleBound(translate(dir * Game.ballSpeed * dt)))
  }
}