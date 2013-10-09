package org.crockeo.pong

import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.Display

import org.crockeo.pong.objects.Renderable

object Renderer {
  // Checking if the progam wants to quit
  def windowWantsToQuit: Boolean = Display.isCloseRequested
  
  // Flipping the display
  def flip                       = Display.update
  
  // Rendering the current state
  def renderState(s: State) = {
    clear
    s.getRenderables.foreach(a => render(a))
    flip
  }
  
  // Clearing the color buffer
  def clear =
    glClear(GL_COLOR_BUFFER_BIT)
  
  // Rendering a renderable object
  def render(r: Renderable) = {
    glBegin(r.renderType)
      r.generateRenderPoints
       .foreach(a => glVertex2f(a.x, a.y))
    glEnd
  }
}