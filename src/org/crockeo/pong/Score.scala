package org.crockeo.pong

import org.lwjgl.opengl.GL11._

import org.crockeo.pong.objects.Updateable
import org.crockeo.pong.geom.Vector

class Score(lScore: Int, rScore: Int) extends Renderable
	with Updateable {
  override def toString: String =
    lScore + " | " + rScore
  
  private var sfl = false
  private var sfr = false
    
  def shouldScoreForLeftNextUpdate: Unit =
    sfl = true
  def shouldScoreForRightNextUpdate: Unit =
    sfr = true
    
  private def scoreForLeft: Score =
    new Score(lScore + 1, rScore)
  private def scoreForRight: Score =
    new Score(lScore, rScore + 1)
  
  // Renderable
  def render(s: State) =
    Renderer.renderString(new Vector(s.gameSize.x / 2, 25), toString)
    
  // Updateable
  def update(dt: Float, s: State): Score =
    if      (sfl) scoreForLeft
    else if (sfr) scoreForRight
    else          this
}