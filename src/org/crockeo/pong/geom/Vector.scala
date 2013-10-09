package org.crockeo.pong.geom

import scala.util.Random

class Vector(val x: Float, val y: Float) {
  // Translating a vector
  def +(dir: Vector): Vector =
    new Vector(x + dir.x, y + dir.y)
  
  // Applying a scalar function to a vector
  def *(n: Float): Vector =
    new Vector(x * n, y * n)
  
  // Applying an inverse scalar function to a vector
  def /(n: Float): Vector =
    new Vector(x / n, y / n)
  
  // Reversing the x / y values
  def reverseX: Vector = new Vector(-x,  y)
  def reverseY: Vector = new Vector( x, -y)
  
  // Checking relative directions
  def above(v: Vector)  : Boolean = y < v.y
  def below(v: Vector)  : Boolean = y > v.y
  def leftOf(v: Vector) : Boolean = x < v.x
  def rightOf(v: Vector): Boolean = x > v.x
  
  override def toString: String =
    "(" + x + "," + y + ")"
}

object Vector {
  def randomDirectionVertical: Vector =
    if (Random.nextInt % 2 == 0) up else down
  def randomDirectionLateral: Vector =
    if (Random.nextInt % 2 == 0) left else right
  def randomDirectionPair: Vector =
    randomDirectionVertical + randomDirectionLateral
  
  val up   : Vector = new Vector( 0, -1)
  val down : Vector = new Vector( 0,  1)
  val left : Vector = new Vector(-1,  0)
  val right: Vector = new Vector( 1,  0)
}