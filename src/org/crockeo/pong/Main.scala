package org.crockeo.pong

import org.lwjgl.opengl.DisplayMode
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11._
import org.lwjgl.input.Keyboard
import org.lwjgl.LWJGLException

object Main extends App {
  def main = {
	// Initializing the videogame
    def initialize(cfg: Config) = {
      // Initializing LWJGL
      def initializeLWJGL = {
        def destroy = {
          if (Display.isCreated) Display.destroy
          if (Keyboard.isCreated) Keyboard.destroy
        }
        
        destroy
        
        try {
          Display.setDisplayMode(new DisplayMode(cfg.w, cfg.h))
          Display.setTitle("Crockeo's Pong")
          Display.create
          
          Keyboard.create
        } catch { case e: Exception => e.printStackTrace }
      }
      
      // Initializing OpenGL
      def initializeOpenGL = {
        glMatrixMode(GL_PROJECTION)
        glLoadIdentity
        
        glOrtho(0, cfg.w, cfg.h, 0, -1, 1)
        
        glMatrixMode(GL_MODELVIEW)
        
        glClearColor(0.f, 0.f, 0.f, 1.f)
        glDisable(GL_DEPTH)
      }
      
      initializeLWJGL
      initializeOpenGL
    }
    
    val cfg = Config.loadConfig(Config.defaultLocation)
    
    initialize(cfg)
    Game.start(cfg)
  }
  
  main
}