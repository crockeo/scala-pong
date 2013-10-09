package org.crockeo.pong

import org.crockeo.pong.objects.Ball
import org.crockeo.pong.geom.Vector

object Game {
  val paddleSpeed = 150.0f
  val ballSpeed = 200.0f
  
  def start(cfg: Config) =
    gameLoop(State.createState(cfg))
  
  private def gameLoop(s: State): Unit = {
    if (s.running && !Renderer.windowWantsToQuit) {
      Renderer.renderState(s)
      gameLoop(s.update(Timer.deltaTime))
    }
  }
}