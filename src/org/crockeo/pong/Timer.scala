package org.crockeo.pong

object Timer {
  private var lastTime: Long = 0
  
  // Getting the delta time
  def deltaTime: Float = {
    val currTime = System.currentTimeMillis
    
    if (lastTime != 0) {
      val dt = currTime - lastTime
      lastTime = currTime
      dt / 1000.f
    } else {
      lastTime = currTime
      0.f
    }
  }
}