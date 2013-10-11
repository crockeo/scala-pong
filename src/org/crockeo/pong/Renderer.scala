package org.crockeo.pong

import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11._

import org.newdawn.slick.font.effects.ColorEffect
import org.newdawn.slick.{SlickException, UnicodeFont, Color}

import org.crockeo.pong.geom.Vector

import java.awt.Font
import java.io.File

trait Renderable {
  def render(s: State): Unit
}

object Renderer {
  private val renderFont =
    FontLoader.loadFont("res/fonts/scorefont.ttf", 24)
  
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
    glBindTexture(GL_TEXTURE_2D, 0)
    glBegin(rt)
      glColor3f(1.f, 1.f, 1.f)
      vs.foreach(a => glVertex2f(a.x, a.y))
    glEnd
  }
  
  // Rendering text
  def renderString(pos: Vector, s: String) =
    renderFont.drawString(pos.x - (renderFont.getWidth(s) / 2), pos.y - (renderFont.getHeight(s) / 2), s)
}