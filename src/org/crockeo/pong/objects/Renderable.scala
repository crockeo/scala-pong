package org.crockeo.pong.objects

import org.crockeo.pong.geom.Vector

trait Renderable {
  // Generating a list of points to bind
  def generateRenderPoints: List[Vector]
  
  // The type of object to render
  def renderType: Int
}