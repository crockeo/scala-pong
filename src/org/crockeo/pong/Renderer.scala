package org.crockeo.pong

import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11._

import org.newdawn.slick.{TrueTypeFont, Color}

import org.crockeo.pong.geom.Vector

import java.awt.Font
import java.io.File

trait Renderable {
  def render(s: State): Unit
}

object Renderer {
  // TODO: Get rendering fonts working
  private val renderFont = {
    def loadFont(s: String, pnt: Float): Font =
      Font.createFont(Font.TRUETYPE_FONT, new File(s)).deriveFont(pnt)
    new TrueTypeFont(loadFont("res/fonts/scorefont.ttf", 24), false)
  }
  
  // Checking if the progam wants to quit
  def windowWantsToQuit: Boolean = Display.isCloseRequested
  
  // Flipping the display
  def flip                       = { Display.update; Display.sync(60) }
  
  // Rendering the current state
  def renderState(s: State) = {
    clear
    s.getRenderables.foreach(a => a.render(s))
    flip
  }
  
  // Clearing the color buffer
  def clear =
    glClear(GL_COLOR_BUFFER_BIT)
  
  // Rendering a set of vectors
  def renderVertecies(vs: List[Vector], rt: Int) = {
    glBegin(rt)
      vs.foreach(a => glVertex2f(a.x, a.y))
    glEnd
  }
  
  // Rendering text
  def renderString(pos: Vector, s: String) =
    renderFont.drawString(pos.x - (renderFont.getWidth(s) / 2), pos.y, s)
}