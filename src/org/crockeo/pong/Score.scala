package org.crockeo.pong

import org.lwjgl.opengl.GL11._

import org.crockeo.pong.geom.Vector

class Score(lScore: Int, rScore: Int) extends Renderable {
  override def toString: String =
    lScore + " | " + rScore
  
  def scoreForLeft: Score =
    new Score(lScore + 1, rScore)
  def scoreForRight: Score =
    new Score(lScore, rScore + 1)
  
  // Renderable
  def render(s: State) =
    Renderer.renderString(new Vector(s.gameSize.x / 2, 25), toString)
}