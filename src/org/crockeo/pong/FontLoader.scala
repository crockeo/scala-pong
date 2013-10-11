package org.crockeo.pong

object FontLoader {
  private val fl: FontLoaderRaw = new FontLoaderRaw
  def loadFont(s: String, pnt: Float) = fl.loadFont(s, pnt)
}