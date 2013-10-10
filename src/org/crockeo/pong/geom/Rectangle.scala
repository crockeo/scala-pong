package org.crockeo.pong.geom

import org.lwjgl.opengl.GL11.GL_QUADS

import org.crockeo.pong.{Renderable, Renderer, State}

class Rectangle(val pos: Vector, val size: Vector) extends Renderable {
  // Translating the rectangle
  def translate(dir: Vector) =
    new Rectangle(pos + dir, size)
  
  // Resizing the rectangle
  def resize(dir: Vector) =
    new Rectangle(pos, size + dir)
  
  // Getting the edges of a rectangle
  def top:   Float = pos.y
  def bot:   Float = pos.y + size.y
  def left:  Float = pos.x
  def right: Float = pos.x + size.x
  
  // Checking if a rectangle intersects another rectangle
  def intersects(r: Rectangle): Boolean =
    (top < r.bot) && (bot > r.top) && (left < r.right) && (right > r.left)
  
  // Renderable
  def render(s: State): Unit =
    Renderer.renderVertecies(List(new Vector(pos.x         , pos.y         ),
                                  new Vector(pos.x + size.x, pos.y         ),
                                  new Vector(pos.x + size.x, pos.y + size.y),
                                  new Vector(pos.x         , pos.y + size.y)), GL_QUADS)
}